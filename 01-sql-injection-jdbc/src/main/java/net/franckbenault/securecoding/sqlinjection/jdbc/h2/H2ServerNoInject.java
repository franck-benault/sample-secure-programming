package net.franckbenault.securecoding.sqlinjection.jdbc.h2;


import java.sql.PreparedStatement;
import java.sql.SQLException;



import org.apache.log4j.Logger;

import net.franckbenault.securecoding.sqlinjection.dto.Person;

public class H2ServerNoInject extends H2Server {

	private static final Logger logger = Logger.getLogger(H2ServerNoInject.class);
		
	public Person createPerson(String firstName, String lastName) throws SQLException {
		
		String sqlOrder =
        		"insert into PERSON (FIRSTNAME,LASTNAME) values (?,?);";

		logger.info("sql order: "+sqlOrder);
		
		PreparedStatement ps2 = connection.prepareStatement(sqlOrder);
		ps2.setString(1, firstName);
		ps2.setString(2, lastName);
        ps2.executeUpdate();
        

		return new Person(firstName, lastName);
	}
}
