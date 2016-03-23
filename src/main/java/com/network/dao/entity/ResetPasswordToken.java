package com.network.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the reset_password_token database table.
 * 
 */
@Entity
@Table(name = "reset_password_token")
@NamedQuery(name = "ResetPasswordToken.findAll", query = "SELECT r FROM ResetPasswordToken r")
public class ResetPasswordToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "no_of_attempt")
	private int noOfAttempt;

	private int otp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	public ResetPasswordToken() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoOfAttempt() {
		return this.noOfAttempt;
	}

	public void setNoOfAttempt(int noOfAttempt) {
		this.noOfAttempt = noOfAttempt;
	}

	public int getOtp() {
		return this.otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}