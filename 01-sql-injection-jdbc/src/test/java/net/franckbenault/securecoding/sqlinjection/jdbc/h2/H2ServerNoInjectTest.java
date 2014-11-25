package net.franckbenault.securecoding.sqlinjection.jdbc.h2;

import static org.junit.Assert.*;

import java.sql.SQLException;

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

}
