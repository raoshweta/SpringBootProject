package com.network.model;

import java.util.ArrayList;

/**
 * Model class for user
 * 
 */
public class UserProfileModel {

	String name;
	String birthday;
	String emailId;
	String gender;
	ArrayList<String> post;
	Boolean follow;
	

	public ArrayList<String> getPost() {
		return post;
	}

	public void setPost(ArrayList<String> post) {
		this.post = post;
	}

	public Boolean getFollow() {
		return follow;
	}

	public void setFollow(Boolean follow) {
		this.follow = follow;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
