package com.company.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.models.User;

public class AuthenticationDAO implements QueryNames {
	
	private Logger log = Logger.getLogger(AuthenticationDAO.class.getName());
	
	public User authenticate(User user) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		
		User found = null;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getInstance().getQuery(AUTH));
			s.setString(1, user.getEmail());
			s.setString(2, user.getPassword());
			r = s.executeQuery();
			if(r.next()) {
				found = new User(r.getString(1), r.getString(2), r.getInt(3) == 0? false: true);
			}
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		log.info("User = "+found);
		
		return found;
	}

}
