package com.example.sample.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.sample.exception.ObjectNotFoundException;
import com.example.sample.model.Moviez;
import com.example.sample.model.repositories.MoviezRepository;
import com.example.sample.pojo.MovieFilter;
import com.example.sample.service.MoviezService;
import com.example.sample.service.UtilService;
@Service
public class MoviezServiceImpl implements MoviezService<Moviez> {
	@Autowired
	private UtilService utilityService;
    @Value("${token.header}")
    private String headerKey;
    @Value("${movie.notfound}")
    private String movieNotFound;
    @Autowired
    private MoviezRepository moviezRepository;
	@Override
	public Moviez prepareMoviezServiceForInsert(MovieFilter filter) throws Exception {
		Moviez movies = new Moviez();
		movies.setTitle(filter.getTitle());
		movies.setDescription(filter.getDescription());
		movies.setId(UUID.randomUUID().toString());
		movies.setLastUpdated(utilityService.getCurrentTimeStamp());
		movies.setRegDate(utilityService.getCurrentTimeStamp());
		movies.setRecommendation(filter.getRecommendation() != null ? filter.getRecommendation() : null);
		movies.setWatched(filter.getWatched() != null && filter.getWatched() != true ? 0 : 1);
		movies.setCreatedBy(utilityService.getCurrentRequest().getHeader(headerKey)!=null?utilityService.getCurrentRequest().getHeader(headerKey):"ADMIN" );
		movies.setUpdatedBy(utilityService.getCurrentRequest().getHeader(headerKey)!=null?utilityService.getCurrentRequest().getHeader(headerKey):"ADMIN" );
		movies.setRating(filter.getRating());
		return movies;
	}

	@Override
	public Moviez prepareMovieServiceForUpdate(MovieFilter filter) throws Exception {
		Moviez movie = moviezRepository.findById(filter.getId()).orElse(null);
		if(movie==null){
			throw new ObjectNotFoundException(movieNotFound);
		}
		movie.setTitle(filter.getTitle());
		movie.setRecommendation(filter.getRecommendation());
		movie.setWatched(filter.getWatched() != null && filter.getWatched() != true ? 0 : 1);
		movie.setLastUpdated(utilityService.getCurrentTimeStamp());
		return movie;
	}

}
