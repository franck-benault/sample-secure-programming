package net.franckbenault.securecoding.sqlinjection.jdbc.h2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.log4j.Logger;

import net.franckbenault.securecoding.sqlinjection.dto.Person;
import net.franckbenault.securecoding.sqlinjection.jdbc.DBServerInterface;

public class H2Server implements DBServerInterface {

	private static final Logger logger = Logger.getLogger(H2Server.class);
	
	protected Connection connection;
	
	public void start() throws ClassNotFoundException, SQLException {
	
        Class.forName("org.h2.Driver");
        connection = DriverManager.
            getConnection("jdbc:h2:~/test", "sa", "");
        
        PreparedStatement ps2 = connection.prepareStatement(
        		"DROP ALL OBJECTS;" );
        ps2.executeUpdate();
        ps2 = connection.prepareStatement(
        		"CREATE TABLE PERSON(FIRSTNAME VARCHAR(40), LASTNAME VARCHAR(40),  CONSTRAINT PK PRIMARY KEY (FIRSTNAME,LASTNAME));;" );
        ps2.executeUpdate();
	}
	
	public Person createPerson(String firstName, String lastName) throws SQLException {
		
		String sqlOrder =
        		"insert into PERSON (FIRSTNAME,LASTNAME) values ('"+firstName+"','"+lastName+"');";

		logger.info("sql order: "+sqlOrder);
		
		PreparedStatement ps2 = connection.prepareStatement(sqlOrder);
        ps2.executeUpdate();
        

		return new Person(firstName, lastName);
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
}
