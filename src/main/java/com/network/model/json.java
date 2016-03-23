package com.network.model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.network.dao.entity.User;
import com.network.vo.UserInfo;

/**
 * json generation method
 * 
 */
public class json {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		User user = new User();

		user.setName(new String());
		user.setPassword(new String());
		user.setBirthday(new String());
		user.setEmailId(new String());
		user.setGender(new String());
		// user.setMobilePhone(new BigInteger(new Byte(20)));

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(System.out, user);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
