package com.network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.network.DatabaseException;
import com.network.dao.PostsDao;
import com.network.dao.UserDao;
import com.network.dao.entity.User;
import com.network.handler.PostsHandler;
import com.network.model.PostModel;

@Controller
public class PostsController {

	@Autowired
	private PostsHandler postsHandler;

	/**
	 * /savePost --> save posts corresponding to a userid.
	 * 
	 * @param PostModel
	 * @return A string describing if the post is successfully saved or not.
	 * @throws DatabaseException 
	 */

	@RequestMapping(value = "/savePost", method = RequestMethod.POST)
	@ResponseBody
	public String savePost(@RequestBody PostModel postModel) throws DatabaseException {

		String result = postsHandler.savePost(postModel);
		return result;

	}

	/**
	 * /deletePost --> delete posts corresponding to a username.
	 * 
	 * @param username
	 * @return A string describing if the post is successfully deleted or not.
	 * @throws DatabaseException
	 */
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	@ResponseBody
	public String deletePost(@RequestBody String username) throws DatabaseException {

		return postsHandler.deletePost(username);

	}

}
