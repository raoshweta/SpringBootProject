package com.network.handler;

import com.network.DatabaseException;
import com.network.model.PostModel;
import com.network.model.RequestWrapper;

/**
 * This interface includes all the methods for Post transactions
 * 
 */
public interface PostsHandler {

	RequestWrapper<String> savePost(PostModel postModel) throws DatabaseException;

	RequestWrapper<String> deletePost(String username) throws DatabaseException;
}
