package com.example.sample.config.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sample.exception.UnAuthorisedUserException;
import com.example.sample.pojo.APIError;
import com.example.sample.service.UserAuthenticationService;
import com.example.sample.service.UtilService;
import com.example.sample.util.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	@Value("${token.header}")
	private String tokenHeader;

	@Value("${source.header.value}")
	private String src;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserAuthenticationService userService;

	@Autowired
	private UtilService utilService;
	@Autowired
	private Environment env;
	private final Logger log = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		try {
			final HttpServletRequest httpRequest = request;
			if (!isAsyncStarted(request) && !isAsyncDispatch(request)
					&& !httpRequest.getMethod().equals(String.valueOf(HttpMethod.OPTIONS))) {
				final Optional<String> authToken = Optional.ofNullable(httpRequest.getHeader("origin-api"));
//				Enumeration headerNames = request.getHeaderNames();
//				while (headerNames.hasMoreElements()) {
//					String key = (String) headerNames.nextElement();
//					String value = request.getHeader(key);
//					System.out.println(key + " "+value);
//				}

				 System.out.println("token: " + authToken.orElse(null));
				final String username = tokenUtils.getUsernameFromToken(authToken.orElse(null));
				if (username == null) {
					throw new UnAuthorisedUserException(env.getProperty("invalid.token"));
				}

				final HttpServletResponse httpResponse = response;
				//httpResponse.setHeader("Authorization", authToken.orElse(null));
				httpResponse.setHeader("ORIGIN-API", authToken.orElse(null));
				// if (SecurityContextHolder.getContext().getAuthentication() ==
				// null) {
				tokenUtils.loadUser(username, authToken, httpRequest);
				// }
			}
			chain.doFilter(request, response);
		} catch (final UnAuthorisedUserException ex) {
			log.error(ex.getMessage());

			final ObjectMapper map = new ObjectMapper();
			final APIError apiError = new APIError();
			apiError.setMsgDeveloper(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage()
					: utilService.getStackTrace(ex));
			apiError.setMessage(ex.getMessage());
			apiError.setCode(String.valueOf(HttpStatus.UNAUTHORIZED));
			final HttpServletResponse response2 = utilService.addCORSHeaders(response);
			response2.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
			//response2.setStatus(Integer.valueOf(String.valueOf(HttpStatus.UNAUTHORIZED)));
			response2.setStatus(Integer.valueOf(401));
			response2.getWriter().write(map.writeValueAsString(apiError));

		} catch (final Exception ex) {
			log.error(ex.getMessage());
			final ObjectMapper map = new ObjectMapper();
			final APIError apiError = new APIError();
			apiError.setMsgDeveloper(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage()
					: utilService.getStackTrace(ex));
			apiError.setMessage(env.getProperty("webportal.error.message"));
			apiError.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
			final HttpServletResponse response2 = response;
			response2.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
			//response2.setStatus(Integer.valueOf(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)));
			response2.setStatus(Integer.valueOf(500));
			response2.getWriter().write(map.writeValueAsString(apiError));

		}
	}

	@Override
	public void destroy() {
		// do nothing

	}

}
