package com.network.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.network.DatabaseException;
import com.network.dao.entity.User;

/**
 * This interface extends CrudRepository performing database transaction
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

	public User findByuserid(int userid) throws DatabaseException;

	public User findByname(String name) throws DatabaseException;

}
