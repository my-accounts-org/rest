package com.company.ac.services.impl;

import com.company.ac.dao.AuthenticationDAO;
import com.company.ac.exceptions.UnAuthorizedUserException;
import com.company.ac.models.User;
import com.company.ac.services.AuthenticationService;

public class AuthenticationImpl implements AuthenticationService{

	private AuthenticationDAO dao = new AuthenticationDAO();
	
	@Override
	public User login(User user) throws UnAuthorizedUserException {
		
		return dao.authenticate(user);
	}

	@Override
	public boolean logout() {
		return false;
	}

}
