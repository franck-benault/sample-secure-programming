package net.franckbenault.securecoding.sqlinjection.jpa.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.franckbenault.securecoding.sqlinjection.jpa.Person;
import net.franckbenault.securecoding.sqlinjection.jpa.PersonManager;

@Stateless
public class PersonManagerImpl implements PersonManager {
	
	@PersistenceContext(unitName = "versie1-unit")
	private EntityManager em;

	public Person createPerson(String firstName, String lastName) {
		
		
		return null;
	}
	
	public List<Person> findAllPersons() {
		return null;
	}
}
