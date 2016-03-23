package com.network.dao;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.network.DatabaseException;
import com.network.dao.entity.Follower;
import com.network.dao.entity.User;

public interface FollowDao extends CrudRepository<Follower, Long> {

	
	public ArrayList<Follower> findByfollowerid(int followerid) throws DatabaseException;
	public ArrayList<Follower> findByuser(User user) throws DatabaseException;
}
