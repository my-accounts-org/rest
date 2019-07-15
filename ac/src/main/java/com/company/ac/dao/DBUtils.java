package com.company.ac.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.models.Group;
import com.company.ac.models.Ledger;
import com.company.ac.models.company.Company;
import com.company.ac.utils.DateUtil;

public class DBUtils {
		
	private static final DBUtils dbUtils = new DBUtils();
	
	private Logger log = Logger.getLogger(AuthenticationDAO.class.getName());
	
	private static final Map<String, String> cachedQuery = new HashMap<String, String>();
	
	private DBUtils() {}
	
	public static DBUtils getInstance() {
		return dbUtils;
	}

	public static String getSQLQuery(String name) {
		return dbUtils.getQuery(name);
	}

	public String getQuery(String name) {
		String query = cachedQuery.get(name);
		if(query == null) 
			return loadQuery(name);
		log.info("query found in cache! "+query);
		return query;
	}
	
	private String loadQuery(String name) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		String query = null, sql = "select query from sql_queries where query_name = ?";
	
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(sql);
			s.setString(1, name);
			r = s.executeQuery();
			if(r.next()) {
				cachedQuery.put(name, query = r.getString(1));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			AccountsDataSource.close(c, s, r);
		}
		
		log.info("fetched from DB: "+query);
		
		return query;
		
	}
	
	public List<Long> insertAndReturnKeys(String sql) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		List<Long> keys = new ArrayList<Long>();
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			s.executeUpdate();			
			
			r = s.getGeneratedKeys();
			
			while(r.next()) {
				keys.add(r.getLong(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return keys;
	}
	
	public boolean insertQueries(List<String> queries) {
		Connection c = null;
		Statement s = null;
		boolean result = false;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			for(String sql: queries) {				
				s.addBatch(sql);					
			}			
			log.info("queries => "+queries);
			result = s.executeBatch().length > -1 ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return result;
	}
	
	
	public boolean delete(long id, String tableName) {
		Connection c = null;
		Statement s = null;
		
		
		String sql ="delete from "+tableName+" where config_id = "+id;
		log.info("sql => "+sql);
		boolean result = false;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			result = s.execute(sql);
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		return result;
		
	}

	public boolean insert(String sql) {
		Connection c = null;
		Statement s = null;
		boolean result = false;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			result = s.execute(sql);
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return result;
	}
	
	public int update(String sql) {
		Connection c = null;
		Statement s = null;
		int count = 0;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			count = s.executeUpdate(sql);
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return count; 
	}

	public Company select(String sql) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		Company company = null;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			company = convert(r, new Company());
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return company;
	}
	
	public Object convert(ResultSet r, Object type) throws SQLException {
		
		if(type instanceof Group) {
			type = convert(r, (Group) type);			
		} else if(type instanceof Company) {
			type = convert(r, (Company) type);
		}
		else if(type instanceof Ledger) {
			type = convert(r, (Ledger) type);
		}
		
		return type;
	}
	
	private Company convert(ResultSet r, Company company) throws SQLException {
		int index = 0;			
		company.setId(r.getLong(++index));
		company.setName(r.getString(++index));
		company.setMailingName(r.getString(++index));
		company.setMailingAddress(r.getString(++index));
		company.setFinancialYear(DateUtil.format(r.getDate(++index), "M/dd/yyyy"));
		company.setBooksBeginingFrom(DateUtil.format(r.getDate(++index), "M/dd/yyyy"));
		company.setPasswordProtected(r.getInt(++index) == 1);
		company.setPassword(r.getString(++index));
		company.setStatus(r.getInt(++index));
		company.setIsDefault(r.getInt(++index));		
		return company;
	}
	
	private Group convert(ResultSet r, Group group) throws SQLException {
		int index = 0;
		group.setId(r.getInt(++index));
		group.setName(r.getString(++index));
		group.setUnder(r.getLong(++index));
		group.setNature(r.getString(++index));
		group.setGrossAffected(r.getInt(++index) == 1);
		++index; //config
		group.setDefault(r.getInt(++index) == 1);		
		++index; //accountType
		group.setNameOfGroupUnder(r.getString(++index));
		return group;
		
	}
	
	private Ledger convert(ResultSet r, Ledger ledger) throws SQLException {
		int index = 0;
		ledger.setId(r.getLong(++index));
		ledger.setName(r.getString(++index));
		ledger.setUnder(r.getLong(++index));
		ledger.setOpeningBalance(r.getLong(++index));
		ledger.setLedgerUnderGroupName(r.getString(10));
		return ledger;
		
	}
	
	
}
