package net.franckbenault.securecoding.sqlinjection.jpa;

import static org.junit.Assert.*;

import javax.ejb.embeddable.EJBContainer;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonManagerTest {

	private static EJBContainer container;
	private static PersonManager personManager;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	       final Properties props = new Properties();
	        props.setProperty("openejb.embedded.remotable", "true");
	        //props.setProperty(EJBContainer.PROVIDER, "tomee-embedded");
	        props.setProperty("openejb.configuration", 
	        		"./src/main/tomcat/conf/tomee.xml");
	        container = EJBContainer.createEJBContainer(props);
	        
	        final Context context = container.getContext();
	        personManager = (PersonManager) context.lookup("java:global/02-sql-injection-jpa/PersonManagerImpl");

	 
	}
	


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreatePerson() {
		
		personManager.createPerson("firstName", "lastName");
		List<Person> persons =personManager.findAllPersons();
		
		//assertEquals(persons.size(),1);
		

	}

}
