package com.network.handler.Impl;

import java.awt.List;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.DatabaseException;
import com.network.dao.PostsDao;
import com.network.dao.UserDao;
import com.network.dao.entity.Post;
import com.network.dao.entity.User;
import com.network.handler.PostsHandler;
import com.network.model.PostModel;

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
	public String savePost(PostModel postModel) throws DatabaseException {

		java.util.Date date = new java.util.Date();
		post.setTime(new Timestamp(date.getTime()));
		
		if(postModel.getPost()==null || postModel.getUserId()==0){
			
			return "Invalid Parameters";
		}
		
		if(postModel.getPost().equals("")){
			
			return "Invalid Parameters";
		}
		
		
		user = userDao.findByuserid(postModel.getUserId());

		post.setUser(user);
		post.setPost(postModel.getPost());
		postsDao.save(post);

		return "Posts is created with userid " + user.getName();
	}

	/**
	 * /deletePost --> delete posts corresponding to a username.
	 * 
	 * @param username
	 * @return A string describing if the post is successfully deleted or not.
	 * @throws DatabaseException 
	 */
	public String deletePost(String username) throws DatabaseException {
		
		User user = userDao.findByname(username);
		
		if(user==null){
			
			return "Invalid user parameter";
			
		}
		ArrayList<Post> posts = new ArrayList<Post>();

		if (user.getPosts().size() == 0) {

			return "No posts available for user " + username;
		}

		postsDao.delete(user.getPosts());

		return "success";
	}

}
