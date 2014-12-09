package net.franckbenault.securecoding.sqlinjection.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;

import net.franckbenault.securecoding.sqlinjection.jpa.Person;
import net.franckbenault.securecoding.sqlinjection.jpa.PersonManager;
import net.franckbenault.securecoding.sqlinjection.util.ListUtil;

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
	
	public 	List<Person> findPersonByFirstNames(List<String> firstNames) {
		
		List<String> firstNamesEncoded = new ArrayList<String>();
		Codec codec = new MySQLCodec(MySQLCodec.Mode.ANSI);
		for(String s : firstNames) {
			firstNamesEncoded.add(ESAPI.encoder().encodeForSQL(codec, s));
		}		
		String firstNamesInString = ListUtil.listToString(firstNamesEncoded);
		
		
		String sqlOrder =
        		"select p from Person p where p.firstname in ("+firstNamesInString+")";

		System.out.println(sqlOrder);
		TypedQuery<Person> query = em.createQuery(sqlOrder, Person.class);
		List<Person> persons = query.getResultList();
		

		return persons;
	}
	
}
