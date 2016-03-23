package com.network.handler;

import com.network.DatabaseException;
import com.network.model.FollowModel;

/**
 * This interface includes all the methods for Follower transactions
 * 
 */
public interface FollowerHandler {

	String followProfile(FollowModel followModel) throws DatabaseException;

	String unfollowProfile(FollowModel followModel) throws DatabaseException;

}
