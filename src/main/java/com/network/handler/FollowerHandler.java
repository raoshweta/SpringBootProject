package com.network.handler;

import com.network.DatabaseException;
import com.network.model.FollowModel;
import com.network.model.RequestWrapper;

/**
 * This interface includes all the methods for Follower transactions
 * 
 */
public interface FollowerHandler {
	RequestWrapper<String> followProfile(FollowModel followModel)
			throws DatabaseException;

	RequestWrapper<String> unfollowProfile(FollowModel followModel)
			throws DatabaseException;

}
