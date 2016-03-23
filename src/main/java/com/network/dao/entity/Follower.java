package com.network.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the followers database table.
 * 
 */
@Entity
@Table(name = "followers")
@NamedQuery(name = "Follower.findAll", query = "SELECT f FROM Follower f")
public class Follower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int followerid;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	public Follower() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFollowerid() {
		return this.followerid;
	}

	public void setFollowerid(int followerid) {
		this.followerid = followerid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}