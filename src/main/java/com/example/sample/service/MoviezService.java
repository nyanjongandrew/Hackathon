package com.example.sample.service;

import com.example.sample.pojo.MovieFilter;

/**
 * 
 * @author user
 * Does all movie db related operations
 * @param <T>
 */
public interface MoviezService<T> {
  T prepareMoviezServiceForInsert(MovieFilter o) throws Exception;
  
  T prepareMovieServiceForUpdate(MovieFilter filter) throws Exception;
}
