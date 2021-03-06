package com.network.handler.Impl;

import java.awt.List;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.Constants;
import com.network.DatabaseException;
import com.network.dao.PostsDao;
import com.network.dao.UserDao;
import com.network.dao.entity.Post;
import com.network.dao.entity.User;
import com.network.handler.PostsHandler;
import com.network.model.PostModel;
import com.network.model.RequestWrapper;

/**
 * This class implements all Posts handler methods.
 * 
 */
@Component
public class PostsHandlerImpl implements PostsHandler {

	@Autowired
	private PostsDao postsDao;
	@Autowired
	private UserDao userDao;
	Post post = new Post();
	User user = new User();

	/**
	 * /savePost --> save posts corresponding to a userid.
	 * 
	 * @param PostModel
	 * @return A string describing if the post is successfully saved or not.
	 * @throws DatabaseException
	 */
	public RequestWrapper<String> savePost(PostModel postModel)
			throws DatabaseException {
		RequestWrapper<String> requestWrapper = new RequestWrapper<String>();
		java.util.Date date = new java.util.Date();
		post.setTime(new Timestamp(date.getTime()));
		if (postModel.getPost() == null || postModel.getUserId() == 0) {
			requestWrapper.setData("Invalid user parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		if (postModel.getPost().equals("")) {
			requestWrapper.setData("Invalid user parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		user = userDao.findByuserid(postModel.getUserId());
		if (user == null) {
			requestWrapper.setData("Invalid user parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		post.setUser(user);
		post.setPost(postModel.getPost());
		postsDao.save(post);
		requestWrapper
				.setData("Posts is created with userid " + user.getName());
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;
	}

	/**
	 * /deletePost --> delete posts corresponding to a username.
	 * 
	 * @param username
	 * @return A string describing if the post is successfully deleted or not.
	 * @throws DatabaseException
	 */
	public RequestWrapper<String> deletePost(String username)
			throws DatabaseException {
		RequestWrapper<String> requestWrapper = new RequestWrapper<String>();
		User user = userDao.findByname(username);
		if (user == null) {
			requestWrapper.setData("Invalid user parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		if (user.getPosts().size() == 0) {
			requestWrapper.setData("No posts available for user " + username);
			requestWrapper.setResponseMessage("Success");
			requestWrapper.setCodeStatus(Constants.SUCCESS);
			return requestWrapper;
		}
		postsDao.delete(user.getPosts());
		requestWrapper.setData("Post deleted for " + username);
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;
	}
}
