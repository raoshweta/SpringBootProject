package com.network.handler.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.DatabaseException;
import com.network.dao.FollowDao;
import com.network.dao.UserDao;
import com.network.dao.entity.Follower;
import com.network.dao.entity.User;
import com.network.handler.FollowerHandler;
import com.network.model.FollowModel;

/**
 * This class implements all Follower handler methods.
 * 
 */
@Component
public class FollowerHandlerImpl implements FollowerHandler {

	@Autowired
	FollowDao followDao;
	@Autowired
	UserDao userDao;

	/**
	 * /followProfile --> follow profile
	 * 
	 * @param FollowModel
	 * @return A string describing if the user can follow other user
	 *         successfully
	 * @throws DatabaseException 
	 */
	public String followProfile(FollowModel followModel) throws DatabaseException {
		Follower follow = new Follower();
		ArrayList<Follower> followerList = new ArrayList<Follower>();
		User user = new User();
		User follower = new User();
		user = userDao.findByname(followModel.getUserName());
		follower = userDao.findByname(followModel.getFollowerName());

		followerList = followDao.findByfollowerid(follower.getUserid());

		for (int i = 0; i <= followerList.size() - 1; i++) {

			if (followerList.get(i).getUser().getName()
					.equals(followModel.getUserName())) {
				return "You are already following user " + follower.getName();
			}
		}
		follow.setFollowerid(follower.getUserid());
		follow.setUser(user);
		followDao.save(follow);
		return "You are following user " + follower.getName();
	}

	/**
	 * /unfollowProfile --> follow profile
	 * 
	 * @param FollowModel
	 * @return A string describing if the user unfollowed other user
	 *         successfully
	 * @throws DatabaseException 
	 */
	public String unfollowProfile(FollowModel followModel) throws DatabaseException {
		User user = new User();
		User follower = new User();
		user = userDao.findByname(followModel.getUserName());
		follower = userDao.findByname(followModel.getFollowerName());
		ArrayList<Follower> followerList = new ArrayList<Follower>();
		followerList = followDao.findByfollowerid(follower.getUserid());
		for (int i = 0; i <= followerList.size() - 1; i++) {
			if (followerList.get(i).getUser().getName()
					.equals(followModel.getUserName())) {
				followDao.delete(followerList.get(i));
				return "Unfollowed user " + followModel.getFollowerName();
			}
		}
		return followModel.getFollowerName() + " already not followed";

	}
}
