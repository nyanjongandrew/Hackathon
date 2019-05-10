package com.example.sample.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.sample.config.filter.AuthenticationTokenFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// @Autowired
		// private AuthenticationTokenFilter authenticationTokenFilter;

		@Bean
		public AuthenticationTokenFilter getAuthenticationTokenFilter() {
			return new AuthenticationTokenFilter();
		}
//		@Bean
//		public CorsFilter getCorsFilter() {
//			return new CorsFilter();
//		}

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			// http.requiresChannel().anyRequest().requiresInsecure();
			http.authorizeRequests().antMatchers("/api/**").permitAll();
			http.csrf().disable();
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			// to prevent click jacking attacks
			http.headers().frameOptions().sameOrigin();
			http.headers().httpStrictTransportSecurity().disable();
			http.headers().cacheControl();
			// to prevent XSS attacks
			http.headers().xssProtection().block(false);
			// Cntent security Policy
			http.headers().contentSecurityPolicy("script-src 'self' ; object-src ; report-uri");
			// add referrer policy
			// http.headers()
			// .referrerPolicy(ReferrerPolicy.SAME_ORIGIN);
			// to ensure that the browser always uses Https
			// http.headers().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000);
//			http.addFilterBefore(getCorsFilter(), ChannelProcessingFilter.class);

		}

		/* To allow Pre-flight [OPTIONS] request from browser */
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		}

		@Bean
		public FilterRegistrationBean authenticationTokenFilterBean() throws Exception {
			final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
			filterRegistrationBean.setFilter(getAuthenticationTokenFilter());
			final List<String> urls = new ArrayList<>();
			urls.add("/api/movies/*");
			filterRegistrationBean.setUrlPatterns(urls);
			// filterRegistrationBean.setEnabled(false);
			filterRegistrationBean.setOrder(Integer.MIN_VALUE);
			return filterRegistrationBean;
		}
}
