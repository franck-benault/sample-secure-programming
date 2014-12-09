package net.franckbenault.securecoding.sqlinjection.jdbc.h2;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;

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
	
	public List<Person> findPersonByFirstNames(List<String> firstNames) throws SQLException {
		
		List<Person> persons = new ArrayList<Person>();

		Codec codec = new MySQLCodec(MySQLCodec.Mode.ANSI);

		
		String firstNamesInString ="(";
		for(String firstName: firstNames) {
			String encoded = ESAPI.encoder().encodeForSQL(codec, firstName);
			if(firstNamesInString.equals("(")) 			
				firstNamesInString +="'"+encoded;
			else
				firstNamesInString +="','"+encoded;
		}
		firstNamesInString +="')";
		logger.info(firstNamesInString);
		
		
		
		String sqlOrder =
        		"select FIRSTNAME,LASTNAME from PERSON where FIRSTNAME in %s;";
		sqlOrder =String.format(sqlOrder, firstNamesInString);
		
		logger.info("sql order: "+sqlOrder);
		
		PreparedStatement ps2 = connection.prepareStatement(sqlOrder);
        ResultSet rs = ps2.executeQuery();
		
        if(rs.first()){
        	 do{
        	  String firstName=rs.getString(1);
        	  String lastName=rs.getString(2);
        	  persons.add(new Person(firstName,lastName));
        	  
        	 }while(rs.next());
        }
		return persons;
	}
}
