package com.network.model;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.network.dao.entity.User;
import com.network.vo.PostInfo;
import com.network.vo.UserInfo;

/**
 * Model class for Post
 * 
 */
public class PostModel {

	int userId;
	int id;
	String post;
	Date time;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		PostModel postModel = new PostModel();

		UserInfo user = new UserInfo();

		user.setName(new String());
		user.setPassword(new String());
		user.setBirthday(new String());
		user.setEmailId(new String());
		user.setGender(new String());
		user.setMobilePhone(new Integer(10));

		postModel.setTime(new Date());
		postModel.setPost(new String());
		postModel.setUserId(new Integer(10));

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(System.out,
					postModel);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
