package com.network.handler.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.Constants;
import com.network.DatabaseException;
import com.network.dao.UserDao;
import com.network.dao.entity.User;
import com.network.handler.LoginHandler;
import com.network.model.LoginModel;
import com.network.model.RequestWrapper;

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

	public RequestWrapper<String> login(LoginModel loginModel)
			throws DatabaseException {
		User user = new User();
		RequestWrapper<String> requestWrapper = new RequestWrapper<String>();
		user = userDao.findByname(loginModel.getUserName());
		if (user == null) {
			requestWrapper.setData("Invalid Username or Password");
			requestWrapper.setResponseMessage("Bad Request");
			requestWrapper.setCodeStatus(Constants.NULL);
			return requestWrapper;
		}
		if ((user.getName() != null && user.getPassword() != null)
				|| (user.getName() != "" && user.getPassword() != "")) {
			if (user.getName().equals(loginModel.getUserName())
					&& user.getPassword().equals(loginModel.getPassword())) {
				requestWrapper.setData("Valid Username & Password");
				requestWrapper.setResponseMessage("Success");
				requestWrapper.setCodeStatus(Constants.SUCCESS);
				return requestWrapper;
			}
		}
		requestWrapper.setData("Invalid Username & Password");
		requestWrapper.setResponseMessage("Unauthorized");
		requestWrapper.setCodeStatus(Constants.UNAUTHORIZED);
		return requestWrapper;

	}
}
