package com.example.sample.exception.controller;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.sample.exception.InsufficientFundsException;
import com.example.sample.exception.MaxAllowableTransferLimitException;
import com.example.sample.exception.ObjectNotFoundException;
import com.example.sample.exception.PreconditionFailedException;
import com.example.sample.exception.WrongPinException;
import com.example.sample.pojo.APIError;
import com.example.sample.service.UtilService;



@RestControllerAdvice
public class ControllerAdvice {
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
	@Value("${object.not.found.error}")
	private String objectNotFound;
	@Autowired
	private UtilService utilService;
	@Autowired
	private Environment env;
    @Value("${generic.error}")
    private String genericError;
	@ResponseBody
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<APIError> objectNotFoundExceptionHandler(ObjectNotFoundException ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();
		// for debugging purposes
		final APIError apiError = new APIError();
		apiError.setMsgDeveloper(
				ex.getMessage() == null || ex.getMessage().isEmpty() ? utilService.getStackTrace(ex) : ex.getMessage());
		apiError.setMessage(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : objectNotFound);
		apiError.setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED));
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(apiError);

	}
	
	@ResponseBody
	@ExceptionHandler(PreconditionFailedException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	ResponseEntity<APIError> preconditionFailedExceptionHandler(PreconditionFailedException ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();
		// for debugging purposes
		final APIError apiError = new APIError();
		apiError.setMsgDeveloper(
				ex.getMessage() == null || ex.getMessage().isEmpty() ? utilService.getStackTrace(ex) : ex.getMessage());
		apiError.setMessage(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : objectNotFound);
		apiError.setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED));
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(apiError);

	}
	@ResponseBody
	@ExceptionHandler(WrongPinException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	ResponseEntity<APIError> preconditionFailedExceptionHandler(WrongPinException ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();
		// for debugging purposes
		final APIError apiError = new APIError();
		apiError.setMsgDeveloper(
				ex.getMessage() == null || ex.getMessage().isEmpty() ? utilService.getStackTrace(ex) : ex.getMessage());
		apiError.setMessage(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : genericError);
		apiError.setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED));
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(apiError);

	}
	@ResponseBody
	@ExceptionHandler(MaxAllowableTransferLimitException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	ResponseEntity<APIError> maxAllowableLimitTransferException(MaxAllowableTransferLimitException ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();
		// for debugging purposes
		final APIError apiError = new APIError();
		apiError.setMsgDeveloper(
				ex.getMessage() == null || ex.getMessage().isEmpty() ? utilService.getStackTrace(ex) : ex.getMessage());
		apiError.setMessage(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : genericError);
		apiError.setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED));
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(apiError);

	}
	@ResponseBody
	@ExceptionHandler(InsufficientFundsException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	ResponseEntity<APIError> insufficientFundsException(InsufficientFundsException ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();
		// for debugging purposes
		final APIError apiError = new APIError();
		apiError.setMsgDeveloper(
				ex.getMessage() == null || ex.getMessage().isEmpty() ? utilService.getStackTrace(ex) : ex.getMessage());
		apiError.setMessage(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : genericError);
		apiError.setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED));
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(apiError);

	}
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ResponseEntity<APIError> exceptionHandler(Exception ex) {
		logger.error(ex.getMessage()); ex.printStackTrace();
		// for debugging purposes
		final APIError apiError = new APIError();
		apiError.setMsgDeveloper(
				ex.getMessage() == null || ex.getMessage().isEmpty() ? utilService.getStackTrace(ex) : ex.getMessage());
		apiError.setMessage(env.getProperty("road.rescue.error"));
		apiError.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);

	}
}
