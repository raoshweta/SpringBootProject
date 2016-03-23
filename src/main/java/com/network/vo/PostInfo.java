package com.network.vo;

import java.util.Date;

/**
 * Post model class
 * 
 */
public class PostInfo {

	int id;
	String post;
	Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
