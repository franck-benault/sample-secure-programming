package net.franckbenault.securecoding.sqlinjection.jpa.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.franckbenault.securecoding.sqlinjection.jpa.Person;
import net.franckbenault.securecoding.sqlinjection.jpa.PersonManager;

@Stateless
public class PersonManagerNoInjectImpl implements PersonManager {
	
	@PersistenceContext(unitName = "versie1-unit")
	private EntityManager em;

	public Person createPerson(String firstName, String lastName) {
		
		long id = System.currentTimeMillis();
		String sqlOrder = "Insert into PERSONJPA(ID,FIRSTNAME,LASTNAME) values(?,?,?)";
		Query query = em.createNativeQuery(sqlOrder);
		query.setParameter(1, id);
		query.setParameter(2, firstName);
		query.setParameter(3, lastName);

		query.executeUpdate();
		
		return null;
	}
	
	public List<Person> findAllPersons() {
		
		return em.createQuery(
	            "select s from Person s", Person.class).getResultList();
	}
}
