package com.network.handler.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.Constants;
import com.network.DatabaseException;
import com.network.Validation;
import com.network.dao.FollowDao;
import com.network.dao.PostsDao;
import com.network.dao.UserDao;
import com.network.dao.entity.Follower;
import com.network.dao.entity.Post;
import com.network.dao.entity.User;
import com.network.handler.UserHandler;
import com.network.model.FollowModel;
import com.network.model.MyprofileModel;
import com.network.model.RequestWrapper;
import com.network.model.UserProfileModel;

/**
 * This class implements all User handler methods.
 * 
 */
@Component
public class UserHandlerImpl implements UserHandler {

	@Autowired
	UserDao userDao;
	@Autowired
	PostsDao postDao;
	@Autowired
	FollowDao followDao;

	/**
	 * /saveUser --> Create a new user and save it in the database.
	 * 
	 * @param User
	 *            object containing user details
	 * @return A string describing if the user is successfully created or not.
	 * @throws DatabaseException
	 */
	public RequestWrapper<String> saveUserDetails(User user)
			throws DatabaseException {

		RequestWrapper<String> requestWrapper = new RequestWrapper<String>();
		String result = null;
		Validation validation = new Validation();

		if ((user.getName() == null || user.getGender() == null
				|| user.getBirthday() == null || user.getEmailId() == null
				|| user.getMobilePhone() == null || user.getPassword() == null)) {

			requestWrapper
					.setData("Invalid user parameters, missing parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;

		}

		if ((user.getName().equals("") || user.getGender().equals("")
				|| user.getBirthday().equals("")
				|| user.getEmailId().equals("")
				|| user.getMobilePhone().equals("") || user.getPassword()
				.equals(""))) {

			requestWrapper.setData("Invalid user parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}

		Boolean mobilevalidation = validation.validatePhoneNumber(user
				.getMobilePhone().toString());

		if ((mobilevalidation = false) || user.getMobilePhone().equals("0")) {

			requestWrapper.setData("Invalid phone number");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}

		User userObj = new User();
		userObj = userDao.findByname(user.getName());

		if (userObj == null) {
			userDao.save(user);
			result = "user created with name " + user.getName();
		} else if (userObj != null) {
			result = "User already exists with name " + user.getName();
		}
		requestWrapper.setData(result);
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;
	}

	/**
	 * /searchByName -->
	 * 
	 * @param name
	 * 
	 * @return set of all users of name
	 * @throws DatabaseException
	 */

	public RequestWrapper<String> searchByName(String name)
			throws DatabaseException {
		RequestWrapper<String> requestWrapper = new RequestWrapper<String>();
		User user = new User();
		user = userDao.findByname(name);

		if (user == null) {

			requestWrapper
					.setData("User not found with name, invalid parameters");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}

		requestWrapper.setData(user.getName());
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;
	}

	/**
	 * /getUserProfile -->
	 * 
	 * @param name
	 * 
	 * @return UserProfileModel
	 * @throws DatabaseException
	 */
	public RequestWrapper<UserProfileModel> getUserProfile(
			FollowModel followModel) throws DatabaseException {

		User user;
		User follow;
		RequestWrapper<UserProfileModel> requestWrapper = new RequestWrapper<UserProfileModel>();
		ArrayList<Post> post = new ArrayList<Post>();
		ArrayList<String> userPosts = new ArrayList<String>();
		int i;

		UserProfileModel userProfileModel = new UserProfileModel();
		if (followModel.getFollowerName() == null
				|| followModel.getUserName() == null) {
			requestWrapper.setData("few parameters are null");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}

		if ((followModel.getFollowerName().equals("") || followModel
				.getFollowerName().equals(""))) {
			requestWrapper.setData(userProfileModel);
			requestWrapper
					.setResponseMessage("Bad Request,few parameters are null");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}

		user = userDao.findByname(followModel.getUserName());
		follow = userDao.findByname(followModel.getFollowerName());
		userProfileModel.setName(follow.getName());
		userProfileModel.setGender(follow.getGender());
		userProfileModel.setEmailId(follow.getEmailId());
		userProfileModel.setBirthday(follow.getBirthday());

		post = postDao.findByuser(follow);

		for (i = 0; i <= post.size() - 1; i++) {
			userPosts.add(post.get(i).getPost());
		}

		userProfileModel.setPost(userPosts);

		ArrayList<Follower> follower = new ArrayList<Follower>();
		follower = followDao.findByfollowerid(user.getFollowers().get(0)
				.getFollowerid());

		for (i = 0; i <= follower.size() - 1; i++) {

			if (follower.get(i).getUser().getName()
					.equals(followModel.getUserName())) {
				userProfileModel.setFollow(true);
			} else {
				userProfileModel.setFollow(false);
			}

		}

		requestWrapper.setData(userProfileModel);
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;
	}

	public RequestWrapper<MyprofileModel> getMyProfile(String name)
			throws DatabaseException {

		MyprofileModel myprofileModel = new MyprofileModel();
		User user;
		int i;
		RequestWrapper<MyprofileModel> requestWrapper = new RequestWrapper<MyprofileModel>();
		ArrayList<Post> post = new ArrayList<Post>();
		ArrayList<String> userPosts = new ArrayList<String>();
		ArrayList<String> followers = new ArrayList<String>();
		ArrayList<Follower> followerList = new ArrayList<Follower>();

		user = userDao.findByname(name);
		post = postDao.findByuser(user);

		if (user == null || post == null) {

			requestWrapper.setData("Invalid Request, few parameters are null");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}

		for (i = 0; i <= post.size() - 1; i++) {
			userPosts.add(post.get(i).getPost());
		}

		followerList = followDao.findByfollowerid(user.getFollowers().get(0)
				.getFollowerid());
		for (i = 0; i <= followerList.size() - 1; i++) {
			followers.add(followerList.get(i).getUser().getName());
		}

		myprofileModel.setName(user.getName());
		myprofileModel.setBirthday(user.getBirthday());
		myprofileModel.setEmailId(user.getEmailId());
		myprofileModel.setPassword(user.getPassword());
		myprofileModel.setGender(user.getGender());
		myprofileModel.setMobilePhone(user.getMobilePhone().toString());
		myprofileModel.setPost(userPosts);

		myprofileModel.setFollow(followers);

		requestWrapper.setData(myprofileModel);
		requestWrapper.setResponseMessage("Success");
		requestWrapper.setCodeStatus(Constants.SUCCESS);
		return requestWrapper;

	}

}
