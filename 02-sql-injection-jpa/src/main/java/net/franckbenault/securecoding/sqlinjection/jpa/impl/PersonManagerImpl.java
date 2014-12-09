package net.franckbenault.securecoding.sqlinjection.jpa.impl;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import net.franckbenault.securecoding.sqlinjection.jpa.Person;
import net.franckbenault.securecoding.sqlinjection.jpa.PersonManager;
import net.franckbenault.securecoding.sqlinjection.util.ListUtil;

@Stateless
public class PersonManagerImpl implements PersonManager {
	
	@PersistenceContext(unitName = "versie1-unit")
	private EntityManager em;

	public Person createPerson(String firstName, String lastName) {
		
		long id = System.currentTimeMillis();
		String sqlOrder = "Insert into PERSONJPA(ID,FIRSTNAME,LASTNAME) values('"+id+"','"+firstName+"','"+lastName+"')";
		System.out.println(sqlOrder);
		Query query = em.createNativeQuery(sqlOrder);
		query.executeUpdate();
		
		return null;
	}
	
	public List<Person> findAllPersons() {
		
		return em.createQuery(
	            "select s from Person s", Person.class).getResultList();
	}
	
	public 	List<Person> findPersonByFirstNames(List<String> firstNames) {
		
		String firstNamesInString = ListUtil.listToString(firstNames);
		String sqlOrder =
        		"select p from Person p where p.firstname in ("+firstNamesInString+")";

		System.out.println(sqlOrder);
		TypedQuery<Person> query = em.createQuery(sqlOrder, Person.class);
		List<Person> persons = query.getResultList();
		

		return persons;
	}
	

}
