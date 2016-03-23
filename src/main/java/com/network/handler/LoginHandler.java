package com.network.handler;

import com.network.DatabaseException;
import com.network.model.LoginModel;

public interface LoginHandler {
	
	public String login(LoginModel loginModel)throws DatabaseException;

}
