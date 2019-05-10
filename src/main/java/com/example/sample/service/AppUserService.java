package com.example.sample.service;

import com.example.sample.pojo.UserInfo;
/**
 * 
 * @author user
 * Holds all user related operations
 * @param <T>
 */
public interface AppUserService<T>{
 T prepareAppUserForInsert(UserInfo filter) throws Exception;
}
