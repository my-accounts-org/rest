package com.company.ac.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.dao.AuthenticationDAO;
import com.company.ac.models.User;
import com.company.ac.services.impl.AuthenticationImpl;

@Path("login")
public class AuthenticationResource {

	private AuthenticationImpl auth = new AuthenticationImpl();
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(User user){		
		return auth.login(user);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		return new User("vivekanand.pandhare@yahoo.com","password", true);
	}
	
}
