package com.network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.network.DatabaseException;
import com.network.handler.LoginHandler;
import com.network.model.LoginModel;
import com.network.model.RequestWrapper;

@Controller
public class LoginController {

	@Autowired
	LoginHandler loginHandler;

	/**
	 * /login --> login using username and password
	 * 
	 * @param loginModel
	 * @return A string describing if the login is successful or not
	 * @throws DatabaseException
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RequestWrapper<String> login(@RequestBody LoginModel loginModel)
			throws DatabaseException {
		return loginHandler.login(loginModel);
	}
}
