package com.example.sample.service;
/**
 * 
 * @author user
 * Does all movie db related operations
 * @param <T>
 */
public interface MoviezService<T> {
  T prepareMoviezServiceForInsert(Object o) throws Exception;
  
  T prepareMovieServiceForUpdate() throws Exception;
}
