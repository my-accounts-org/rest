package com.company.ac.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.models.Group;
import com.company.ac.models.company.Company;
import com.company.ac.services.GroupService;
import com.company.ac.services.impl.GroupServiceImpl;

@Path("groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {
	
	private GroupService groupService = new GroupServiceImpl();
	
	@POST
	public List<Group> getAllGroups(Company company) {
		return groupService.getGroupList(company.getId());
	}
	
	public Group create(Group group) {
		return groupService.create(group);
	}
}
