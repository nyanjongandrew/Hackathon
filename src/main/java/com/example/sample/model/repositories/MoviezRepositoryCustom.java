package com.example.sample.model.repositories;

import java.util.List;

import com.example.sample.model.Moviez;
import com.example.sample.pojo.MovieFilter;




public interface MoviezRepositoryCustom {
	Long countServiceMovies(MovieFilter filter) throws Exception;
	List<Moviez> findMovies(MovieFilter filter) throws Exception;
}
