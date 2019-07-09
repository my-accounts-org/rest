package com.company.ac.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AccountsDataSource {
	private static Connection c;
	
    public static Connection getMySQLConnection() throws NamingException{
        InitialContext ctx = new InitialContext(); 
       /*
        * Lookup the DataSource, which will be backed by a pool
        * that the application server provides. DataSource instances
        * are also a good candidate for caching as an instance
        * variable, as JNDI lookups can be expensive as well.
        */
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myDB");
        try{
            c=ds.getConnection();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return c;
    }
    
    public static void close(Object... objects){
    	for(Object obj: objects){
    		try {
				close(obj);
			} catch (SQLException e) {
			}
    	}
    }
    
    private static void close(Object obj) throws SQLException{
    	if(obj instanceof Connection){
    		((Connection) obj).close();
    	}else if(obj instanceof ResultSet){
    		((ResultSet) obj).close();
    	}else if(obj instanceof Statement){
    		((Statement) obj).close();
    	}else if(obj instanceof PreparedStatement) {
    		((PreparedStatement) obj).close();
    	}
    }

    public static void closeConnection(Connection c,java.sql.ResultSet r,java.sql.Statement s){
        try{
            if(c!=null) c.close();
        }catch(Exception e){
            c=null;
        }

        try{
            if(r!=null) r.close();
        }catch(Exception e){
            r=null;
        }
        try{
            if(s!=null)s.close();
        }catch(Exception e){
            s=null;
        }
    }

}



