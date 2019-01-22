package com.example.sample.facade;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.validation.BindingResult;

public interface UtilityFacade {
	void validateRequest(BindingResult bindingResult) throws Exception;
	
}
