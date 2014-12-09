package net.franckbenault.securecoding.sqlinjection.jpa;

import static org.junit.Assert.*;

import javax.ejb.embeddable.EJBContainer;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonManagerNoInjectTest {

	private static EJBContainer container;
	private static PersonManager personManager;
	

	@Before
	public void setUp() throws Exception {
	       final Properties props = new Properties();
	        props.setProperty("openejb.embedded.remotable", "true");
	        //props.setProperty(EJBContainer.PROVIDER, "tomee-embedded");
	        props.setProperty("openejb.configuration", 
	        		"./src/main/tomcat/conf/tomee.xml");
	        container = EJBContainer.createEJBContainer(props);
	        
	        final Context context = container.getContext();
	        personManager = (PersonManager) context.lookup("java:global/02-sql-injection-jpa/PersonManagerImpl");

	
	}

	@After
	public void tearDown() throws Exception {
		container.close();
	}

	@Test
	public void testCreatePerson() {
		
		//personManager.createPerson("firstName", "lastName");
		List<Person> persons =personManager.findAllPersons();
		
		assertEquals(persons.size(),0);
		
		personManager.createPerson("firstName", "lastName");
		persons =personManager.findAllPersons();
		
		assertEquals(persons.size(),1);
		
		personManager.createPerson("firstName',''); drop table personjpa;-- ", "lastName");


		try {
			persons =personManager.findAllPersons();
			fail("exception expected");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

	
		
		
	}

}
