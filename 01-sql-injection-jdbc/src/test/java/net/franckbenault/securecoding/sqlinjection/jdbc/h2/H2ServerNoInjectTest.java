package net.franckbenault.securecoding.sqlinjection.jdbc.h2;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.franckbenault.securecoding.sqlinjection.dto.Person;
import net.franckbenault.securecoding.sqlinjection.jdbc.DBServerInterface;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class H2ServerNoInjectTest {
	
	private static DBServerInterface server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		server = new H2ServerNoInject();

		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		server.start();
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testCreatePerson() throws SQLException {
		
		Person person =server.createPerson("firstName", "lastName");
		assertNotNull(person);
	}
	
	@Test
	public void testCreatePersonSQLInjection() throws SQLException {
		
		Person person =server.createPerson("firstName',''); drop table person;-- ", "lastName");
		assertNotNull(person);
		//what there is no more table ?
		assertEquals(server.countTables(),1);
	}
	
	@Test
	public void testCountTables() throws SQLException {
		
		assertEquals(server.countTables(),1);

	}
	
	@Test
	public void testFindPersonByFirstNamesSQLInjection() throws SQLException {
		
		server.createPerson("firstName1","lastName1");
		server.createPerson("firstName2","lastName2");
		server.createPerson("firstName3","lastName3");
		
		List<String> firstNames = new ArrayList<String>();
		firstNames.add("firstName1");
		firstNames.add("firstName2");
		firstNames.add("firstName4");
		
		List<Person> persons =server.findPersonByFirstNames(firstNames);
		assertNotNull(persons);
		assertEquals(persons.size(),2);
		
		firstNames.add("firstName1',''); drop table person;-- ");
		try {
			persons =server.findPersonByFirstNames(firstNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//pb table person drop ?
		assertEquals(server.countTables(),1);
	}

}
