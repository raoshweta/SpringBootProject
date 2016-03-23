package com.network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.network.DatabaseException;
import com.network.dao.FollowDao;
import com.network.dao.UserDao;
import com.network.handler.FollowerHandler;
import com.network.model.FollowModel;

@Controller
public class FollowerController {
	@Autowired
	FollowDao followDao;
	@Autowired
	UserDao userDao;
	@Autowired
	private FollowerHandler followerHandler;

	/**
	 * /followProfile --> follow profile
	 * 
	 * @param FollowModel
	 * @return A string describing if the user can follow other user
	 *         successfully
	 * @throws DatabaseException 
	 */

	@RequestMapping(value = "/followProfile", method = RequestMethod.POST)
	@ResponseBody
	public String followProfile(@RequestBody FollowModel followModel) throws DatabaseException {

		return followerHandler.followProfile(followModel);
	}

	/**
	 * /unfollowProfile --> follow profile
	 * 
	 * @param FollowModel
	 * @return A string describing if the user unfollowed other user
	 *         successfully
	 * @throws DatabaseException 
	 */

	@RequestMapping(value = "/unfollowProfile", method = RequestMethod.POST)
	@ResponseBody
	public String unfollowProfile(@RequestBody FollowModel followModel) throws DatabaseException {
		return followerHandler.unfollowProfile(followModel);
	}

}
