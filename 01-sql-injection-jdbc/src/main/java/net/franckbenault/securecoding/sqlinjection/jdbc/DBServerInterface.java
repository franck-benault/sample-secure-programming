package net.franckbenault.securecoding.sqlinjection.jdbc;

import java.sql.SQLException;

import net.franckbenault.securecoding.sqlinjection.dto.Person;

public interface DBServerInterface {

	void start() throws ClassNotFoundException, SQLException;
	
	Person createPerson(String firstName, String lastName) throws SQLException ;
	
	int countTables() throws SQLException;
		
	void stop() throws SQLException;
}
