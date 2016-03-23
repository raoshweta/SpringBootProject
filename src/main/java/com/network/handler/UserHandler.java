package com.network.handler;

import org.springframework.web.bind.annotation.RequestBody;

import com.network.DatabaseException;
import com.network.dao.entity.User;
import com.network.model.FollowModel;
import com.network.model.MyprofileModel;
import com.network.model.RequestWrapper;
import com.network.model.UserProfileModel;

/**
 * This interface includes all the methods for User transactions
 * 
 */
public interface UserHandler {

	String saveUserDetails(User user) throws DatabaseException;
	String searchByName(String name) throws DatabaseException;

	RequestWrapper<UserProfileModel> getUserProfile(FollowModel followModel) throws DatabaseException;
	MyprofileModel getMyProfile(@RequestBody String name) throws DatabaseException;

}
