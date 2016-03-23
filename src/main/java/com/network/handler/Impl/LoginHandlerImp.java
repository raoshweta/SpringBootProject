package com.network.handler.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.DatabaseException;
import com.network.dao.UserDao;
import com.network.dao.entity.User;
import com.network.handler.LoginHandler;
import com.network.model.LoginModel;

@Component
public class LoginHandlerImp implements LoginHandler {

	@Autowired
	UserDao userDao;

	/**
	 * /login --> login using username and password
	 * 
	 * @param loginModel
	 * @return A string describing if the login is successful or not
	 * @throws DatabaseException 
	 */

	public String login(LoginModel loginModel) throws DatabaseException {
		User user = new User();
		user = userDao.findByname(loginModel.getUserName());
		if (user == null) {
			return "Invalid login parameters";
		}
		if (user.getName() != null && user.getPassword() != null) {
			if (user.getName().equals(loginModel.getUserName())
					&& user.getPassword().equals(loginModel.getPassword())) {
				return "Login Success";
			}
		}
		return "Invalid login parameters";
	}
}
