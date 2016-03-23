package com.network.handler;

import com.network.DatabaseException;
import com.network.model.PostModel;

/**
 * This interface includes all the methods for Post transactions
 * 
 */
public interface PostsHandler {

	String savePost(PostModel postModel) throws DatabaseException;

	String deletePost(String username) throws DatabaseException;
}
