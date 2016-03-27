package com.network.handler.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.Constants;
import com.network.DatabaseException;
import com.network.dao.FollowDao;
import com.network.dao.UserDao;
import com.network.dao.entity.Follower;
import com.network.dao.entity.User;
import com.network.handler.FollowerHandler;
import com.network.model.FollowModel;
import com.network.model.RequestWrapper;

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
	public RequestWrapper<String> followProfile(FollowModel followModel)
			throws DatabaseException {
		RequestWrapper<String> requestWrapper = new RequestWrapper<String>();
		Follower follow = new Follower();
		ArrayList<Follower> followerList = new ArrayList<Follower>();
		User user = new User();
		User follower = new User();
		if ((followModel.getUserName() == null || followModel.getFollowerName() == null)

				|| (followModel.getUserName() == "" || followModel
						.getFollowerName() == "")) {
			requestWrapper.setData("Invalid Parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		user = userDao.findByname(followModel.getUserName());
		follower = userDao.findByname(followModel.getFollowerName());
		followerList = followDao.findByfollowerid(follower.getUserid());
		if (user == null || follower == null) {
			requestWrapper.setData("Invalid Parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		for (int i = 0; i <= followerList.size() - 1; i++) {
			if (followerList.get(i).getUser().getName()
					.equals(followModel.getUserName())) {
				requestWrapper.setData("You are already following user "
						+ follower.getName());
				requestWrapper.setResponseMessage("Success");
				requestWrapper.setCodeStatus(Constants.SUCCESS);
				return requestWrapper;
			}
		}
		follow.setFollowerid(follower.getUserid());
		follow.setUser(user);
		followDao.save(follow);
		requestWrapper.setData("You are now following user "
				+ follower.getName());
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;

	}

	/**
	 * /unfollowProfile --> follow profile
	 * 
	 * @param FollowModel
	 * @return A string describing if the user unfollowed other user
	 *         successfully
	 * @throws DatabaseException
	 */
	public RequestWrapper<String> unfollowProfile(FollowModel followModel)
			throws DatabaseException {
		RequestWrapper<String> requestWrapper = new RequestWrapper<String>();
		User user = new User();
		User follower = new User();
		if ((followModel.getUserName() == null || followModel.getFollowerName() == null)
				|| (followModel.getUserName() == "" || followModel
						.getFollowerName() == "")) {
			requestWrapper.setData("Invalid Parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		user = userDao.findByname(followModel.getUserName());
		follower = userDao.findByname(followModel.getFollowerName());
		if (user == null || follower == null) {
			requestWrapper.setData("Invalid Parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		ArrayList<Follower> followerList = new ArrayList<Follower>();
		followerList = followDao.findByfollowerid(follower.getUserid());
		for (int i = 0; i <= followerList.size() - 1; i++) {
			if (followerList.get(i).getUser().getName()
					.equals(followModel.getUserName())) {
				followDao.delete(followerList.get(i));
				requestWrapper.setData("Unfollowed user "
						+ followModel.getFollowerName());
				requestWrapper.setResponseMessage("Success");
				requestWrapper.setCodeStatus(Constants.SUCCESS);
				return requestWrapper;
			}
		}
		requestWrapper.setData("already not followed "
				+ followModel.getFollowerName());
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;
	}
}
