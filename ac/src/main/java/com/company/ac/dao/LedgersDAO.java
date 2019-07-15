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
import com.company.ac.models.Ledger;

public class LedgersDAO implements QueryNames {
	
	private Logger log = Logger.getLogger(LedgersDAO.class.getName());
		
	public List<Ledger> getLedgerList(long companyId){
		List<Ledger> ledgers = new LinkedList<Ledger>();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		DBUtils dbUtils = DBUtils.getInstance();
		String sql = dbUtils.getQuery(GET_ALL_LEDGERS).replace(":id", String.valueOf(companyId));
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				ledgers.add((Ledger) DBUtils.getInstance().convert(r, new Ledger()));
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return ledgers;
		
	}
}
