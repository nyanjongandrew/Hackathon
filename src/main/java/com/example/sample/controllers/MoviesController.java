package com.example.sample.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.facade.MovieFacade;
import com.example.sample.facade.UtilityFacade;
import com.example.sample.pojo.Created;
import com.example.sample.pojo.Edited;
import com.example.sample.pojo.MovieFilter;
import com.example.sample.pojo.MovieInfo;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {
	@Autowired
	private UtilityFacade utilityFacade;

	@Autowired
	private MovieFacade movieFacade;

	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public ResponseEntity<MovieFilter> filter() throws Exception {

		return new ResponseEntity<>(new MovieFilter(), HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Created> registerUser(@Valid @RequestBody MovieFilter filter, BindingResult bindingResult)
			throws Exception {
		utilityFacade.validateRequest(bindingResult);
		return movieFacade.addMovie(filter);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<Edited> editMovie(@Valid @RequestBody MovieFilter filter, BindingResult bindingResult)
			throws Exception {
		utilityFacade.validateRequest(bindingResult);
		return movieFacade.editMovie(filter);
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> removeMovie(@PathVariable(name = "id") String id) throws Exception {

		return movieFacade.removeMovie(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MovieInfo>> fetchAllMovies() throws Exception {

		return movieFacade.retriveAll();
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public ResponseEntity<List<MovieInfo>> fetchFiltered(@Valid @RequestBody MovieFilter filter, BindingResult bindingResult)
			throws Exception {
		utilityFacade.validateRequest(bindingResult);
		return movieFacade.filteredResults(filter);
	}
}
