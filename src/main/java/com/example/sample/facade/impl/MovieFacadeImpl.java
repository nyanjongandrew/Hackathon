package com.example.sample.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.exception.PreconditionFailedException;
import com.example.sample.facade.MovieFacade;
import com.example.sample.model.Moviez;
import com.example.sample.model.repositories.MoviezRepository;
import com.example.sample.pojo.Created;
import com.example.sample.pojo.Edited;
import com.example.sample.pojo.MovieFilter;
import com.example.sample.pojo.MovieInfo;
import com.example.sample.service.MoviezService;
import com.example.sample.util.annotation.Facade;
@Facade
public class MovieFacadeImpl implements MovieFacade {
	@Autowired
	private MoviezService<Moviez> moviezService;
	@Autowired
	private MoviezRepository moviezRepository;
	@Value("${movie.exists}")
	private String movieExists;
	@Value("${movie.notfound}")
	private String movieNotFound;

	@Override
	public ResponseEntity<Created> addMovie(MovieFilter filter) throws Exception {
		Moviez exist = moviezRepository.findByTitle(filter.getTitle());
		if (exist != null) {
			throw new PreconditionFailedException(movieExists);
		}
		Moviez moviez = moviezService.prepareMoviezServiceForInsert(filter);
		Created created = new Created();
		created.setCreated(true);
		return ResponseEntity.ok(created);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public ResponseEntity<Edited> editMovie(MovieFilter filter) throws Exception {
		Moviez moviez = moviezService.prepareMovieServiceForUpdate(filter);
		moviezRepository.save(moviez);
		Edited edited = new Edited();
		edited.setEdited(true);
		return ResponseEntity.ok(edited);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public ResponseEntity<Boolean> removeMovie(String id) throws Exception {
		Moviez movie = moviezRepository.findById(id).orElse(null);
		if (movie == null) {
			throw new PreconditionFailedException(movieNotFound);
		}
		moviezRepository.delete(movie);
		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<List<MovieInfo>> retriveAll() throws Exception {
        List<Moviez> allMovies = moviezRepository.findAll();
        List<MovieInfo> allMovieData = new ArrayList<>();
        allMovies.stream().forEach(movie->{
        	MovieInfo info = mapMoviezToMovieInfo(movie);
        	allMovieData.add(info);
        });
		return ResponseEntity.ok(allMovieData);
	}

	@Override
	public ResponseEntity<MovieInfo> retriveOne(String id) throws Exception {
		Moviez movie = moviezRepository.findById(id).orElse(null);
		if(movie!=null){
			MovieInfo info = mapMoviezToMovieInfo(movie);
			return ResponseEntity.ok(info);
		}
		return null;
	}

	private MovieInfo mapMoviezToMovieInfo(Moviez movie) {
		MovieInfo movieInfo = new MovieInfo();
		movieInfo.setTitle(movie.getTitle());
		// movieInfo.setRating(movie.get);
		movieInfo.setDescription(movie.getDescription());
		movieInfo.setRecommendation(movie.getRecommendation());
		movieInfo.setWatched(movie.getWatched() == 0 ? false : true);
		return movieInfo;
	}

	@Override
	public ResponseEntity<List<MovieInfo>> filteredResults(MovieFilter filter) throws Exception {
		List<Moviez> filteredMovies = moviezRepository.findMovies(filter);
		List<MovieInfo> movieInfo = new ArrayList<>();
		filteredMovies.stream().filter(Objects::nonNull).forEach(movie->{
			MovieInfo info = mapMoviezToMovieInfo(movie);
			movieInfo.add(info);
		});
		return ResponseEntity.ok(movieInfo);
	}

}
