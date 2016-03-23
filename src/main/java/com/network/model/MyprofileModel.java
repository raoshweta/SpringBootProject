package com.network.model;

import java.util.ArrayList;
import java.util.List;

import com.network.dao.entity.Follower;
import com.network.dao.entity.Post;

public class MyprofileModel {

	String name;
	String birthday;
	String emailId;
	String gender;
	String mobilePhone;
	String password;
	public List<String> getFollow() {
		return follow;
	}

	public void setFollow(List<String> follow) {
		this.follow = follow;
	}

	List<String> post;
	List<String> follow;


	public List<String> getPost() {
		return post;
	}

	public void setPost(List<String> post) {
		this.post = post;
	}
	

	




	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}