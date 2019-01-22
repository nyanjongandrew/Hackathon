package com.example.sample.facade.impl;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.sample.exception.DataIntegrityException;
import com.example.sample.facade.UtilityFacade;


@com.example.sample.util.annotation.Facade
public class UtilityFacadeImpl implements UtilityFacade {
//public static final Logger logger = LoggerFactory.getLogger(UtilityFacadeImpl.class);
	@Override
	public void validateRequest(BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			//logger.error("POJO has errors");
			String errorMessage = "";
			for (final FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getDefaultMessage() + "\n";
			}
			//logger.error(errorMessage);
			throw new DataIntegrityException(errorMessage);
		}

	}
}
