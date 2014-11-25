package net.franckbenault.securecoding.sqlinjection.jdbc.h2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.franckbenault.securecoding.sqlinjection.jdbc.DBServerInterface;

public class H2Server implements DBServerInterface {

	private Connection connection;
	
	public void start() throws ClassNotFoundException, SQLException {
	
        Class.forName("org.h2.Driver");
        connection = DriverManager.
            getConnection("jdbc:h2:mem:test", "sa", "");
        
        PreparedStatement ps2 = connection.prepareStatement(
        		"DROP ALL OBJECTS;" );
        ps2.executeUpdate();
        ps2 = connection.prepareStatement(
        		"CREATE TABLE PERSON(ID VARCHAR(2), FIRSTNAME VARCHAR(20), LASTNAME VARCHAR(20),  CONSTRAINT PK PRIMARY KEY (ID));;" );

	}
	
	public int executeQueryUpdate(String query) throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement(
        		query );
        return ps2.executeUpdate();
	}
	
	public int countTables() throws SQLException {
	
		
		DatabaseMetaData md = connection.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = md.getTables(null, null, "%", types);
	
		
        int size = 0;
        while(rs.next()){
            	//System.out.println(rs.getString(3));
                size++;
           }
        

        return size;
	}
	
	public void stop() throws SQLException {
		connection.close();
	}

	public int countConstraints() throws SQLException {
		
		String query ="SELECT CONSTRAINT_NAME, TABLE_NAME FROM INFORMATION_SCHEMA.CONSTRAINTS";
		PreparedStatement ps2 = connection.prepareStatement(
        		query );
		ResultSet rs =  ps2.executeQuery();
      
        int size = 0;
        while(rs.next()){
            	System.out.println("..."+rs.getString(1)+"..."+rs.getString(2) );
                size++;
           }
		return size;
	}

	public String getDBVersion() throws SQLException {
		DatabaseMetaData md = connection.getMetaData();
		return md.getDatabaseProductVersion();
	}
}
