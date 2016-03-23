package com.network.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userid;

	private String birthday;

	@Column(name = "email_id")
	private String emailId;

	private String gender;

	@Column(name = "mobile_phone")
	private BigInteger mobilePhone;

	private String name;

	private String password;

	// bi-directional many-to-one association to Follower
	@OneToMany(mappedBy = "user")
	private List<Follower> followers;

	// bi-directional many-to-one association to Post
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	// bi-directional many-to-one association to ResetPasswordToken
	@OneToMany(mappedBy = "user")
	private List<ResetPasswordToken> resetPasswordTokens;

	public User() {
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigInteger getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(BigInteger mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Follower> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
	}

	public Follower addFollower(Follower follower) {
		getFollowers().add(follower);
		follower.setUser(this);

		return follower;
	}

	public Follower removeFollower(Follower follower) {
		getFollowers().remove(follower);
		follower.setUser(null);

		return follower;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUser(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUser(null);

		return post;
	}

	public List<ResetPasswordToken> getResetPasswordTokens() {
		return this.resetPasswordTokens;
	}

	public void setResetPasswordTokens(
			List<ResetPasswordToken> resetPasswordTokens) {
		this.resetPasswordTokens = resetPasswordTokens;
	}

	public ResetPasswordToken addResetPasswordToken(
			ResetPasswordToken resetPasswordToken) {
		getResetPasswordTokens().add(resetPasswordToken);
		resetPasswordToken.setUser(this);

		return resetPasswordToken;
	}

	public ResetPasswordToken removeResetPasswordToken(
			ResetPasswordToken resetPasswordToken) {
		getResetPasswordTokens().remove(resetPasswordToken);
		resetPasswordToken.setUser(null);

		return resetPasswordToken;
	}

}