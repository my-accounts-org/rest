package com.company.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.models.Group;
import com.company.ac.utils.DateUtil;

public class GroupsDAO implements QueryNames {
	
	private Logger log = Logger.getLogger(GroupsDAO.class.getName());
		
	public List<Group> getGroupList(long companyId){
		List<Group> groups = new LinkedList<Group>();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		DBUtils dbUtils = DBUtils.getInstance();
		String sql = dbUtils.getQuery(GET_ALL_GROUPS).replace(":id", String.valueOf(companyId));
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				groups.add((Group) DBUtils.getInstance().convert(r, new Group()));
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return groups;
		
	}

	public long create(Group group) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		long id = 0;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getInstance().getQuery(CREATE_GROUP),  PreparedStatement.RETURN_GENERATED_KEYS);			
			
			s.execute();
			r = s.getGeneratedKeys();
			if(r.next()) {
				id = r.getLong(1);
			}			
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		log.info("Created id = "+id+" | if 0 then company creation failed");
		
		return id;
	}
}
