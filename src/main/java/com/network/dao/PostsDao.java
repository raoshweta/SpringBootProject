package com.network.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.network.DatabaseException;
import com.network.dao.entity.Post;
import com.network.dao.entity.User;

/**
 * This interface extends CrudRepository performing database transaction
 */
@Transactional
public interface PostsDao extends CrudRepository<Post, Long> {

	 public ArrayList<Post> findByuser(User user) throws DatabaseException;
	
	 
	 
	 

}
