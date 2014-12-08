package net.franckbenault.securecoding.sqlinjection.jdbc;

import java.sql.SQLException;
import java.util.List;

import net.franckbenault.securecoding.sqlinjection.dto.Person;

public interface DBServerInterface {

	void start() throws ClassNotFoundException, SQLException;
	
	Person createPerson(String firstName, String lastName) throws SQLException ;
	
	List<Person> findPersonByFirstNames(List<String> firstNames) throws SQLException;
	
	int countTables() throws SQLException;
		
	void stop() throws SQLException;
}
