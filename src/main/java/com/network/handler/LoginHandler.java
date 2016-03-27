package com.network.handler;

import com.network.DatabaseException;
import com.network.model.LoginModel;
import com.network.model.RequestWrapper;

public interface LoginHandler {

	public RequestWrapper<String> login(LoginModel loginModel)
			throws DatabaseException;

}
