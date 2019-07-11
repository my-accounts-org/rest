package com.company.ac.services.impl;

import java.util.List;

import com.company.ac.dao.GroupsDAO;
import com.company.ac.models.Group;
import com.company.ac.services.GroupService;

public class GroupServiceImpl implements GroupService {

	private GroupsDAO dao = new GroupsDAO();
	
	@Override
	public List<Group> getGroupList(long companyId) {		
		return dao.getGroupList(companyId);
	}

	@Override
	public Group create(Group group) {
		return null;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

}
