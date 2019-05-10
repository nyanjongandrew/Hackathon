package com.example.sample.facade;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.example.sample.pojo.Created;
import com.example.sample.pojo.Edited;
import com.example.sample.pojo.MovieFilter;
import com.example.sample.pojo.MovieInfo;

/**
 * Handles movie related activities
 * @author user
 *
 */
public interface MovieFacade {
	ResponseEntity<Created> addMovie(MovieFilter filter) throws Exception;
	
	ResponseEntity<Edited> editMovie(MovieFilter filter) throws Exception;

	ResponseEntity<Boolean> removeMovie(String id) throws Exception;
	
	ResponseEntity<List<MovieInfo>> retriveAll() throws Exception;
	
	ResponseEntity<MovieInfo> retriveOne(String id) throws Exception;

	ResponseEntity<List<MovieInfo>> filteredResults(MovieFilter filter) throws Exception;
	
}
