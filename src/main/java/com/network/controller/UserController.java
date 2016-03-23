package com.network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.network.DatabaseException;
import com.network.Validation;
import com.network.dao.PostsDao;
import com.network.dao.UserDao;
import com.network.dao.entity.User;
import com.network.handler.UserHandler;
import com.network.model.FollowModel;
import com.network.model.MyprofileModel;
import com.network.model.RequestWrapper;
import com.network.model.UserProfileModel;

@Controller
public class UserController {

	Validation validation = new Validation();

	@Autowired
	private UserHandler userHandler;

	/**
	 * /saveUser --> Create a new user and save it in the database.
	 * 
	 * @param User
	 *            object containing user details
	 * @return A string describing if the user is successfully created or not.
	 * @throws DatabaseException 
	 */

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	@ResponseBody
	public String create(@RequestBody User user) throws DatabaseException {
		return userHandler.saveUserDetails(user);
	}

	/**
	 * /searchByName -->
	 * 
	 * @param name
	 * 
	 * @return USER of name
	 * @throws DatabaseException 
	 */
	@RequestMapping("/searchByName")
	@ResponseBody
	public String searchByName(@RequestBody String name) throws DatabaseException {
		return userHandler.searchByName(name);
	}

	/**
	 * /getUserProfile -->
	 * 
	 * @param name
	 * 
	 * @return UserProfileModel
	 * @throws DatabaseException 
	 */
	@RequestMapping("/getUserProfile")
	@ResponseBody
	public RequestWrapper<UserProfileModel> getUserProfile(@RequestBody FollowModel followModel) throws DatabaseException {
		return userHandler.getUserProfile(followModel);
	}
	
	/**
	 * /getUserProfile -->
	 * 
	 * @param name
	 * 
	 * @return UserProfileModel
	 * @throws DatabaseException 
	 */
	@RequestMapping("/getMyProfile")
	@ResponseBody
	public MyprofileModel getUserProfile(@RequestBody String name) throws DatabaseException {
		return userHandler.getMyProfile(name);
	}

}
