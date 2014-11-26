package net.franckbenault.securecoding.sqlinjection.jpa;

import java.util.List;

import javax.ejb.Local;

@Local
public interface PersonManager {

	Person createPerson(String firstName, String lastName);
	
	List<Person> findAllPersons();
	
}
