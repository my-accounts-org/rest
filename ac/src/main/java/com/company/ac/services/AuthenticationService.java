package com.company.ac.services;

import com.company.ac.exceptions.UnAuthorizedUserException;
import com.company.ac.models.User;

public interface AuthenticationService {
	
	User login(User user) throws UnAuthorizedUserException;
	boolean logout();
}
