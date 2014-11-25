package net.franckbenault.securecoding.sqlinjection.jpa;

import java.util.List;


public interface PersonManager {

	Person createPerson(String firstName, String lastName);
	
	List<Person> findAllPersons();
	
}
