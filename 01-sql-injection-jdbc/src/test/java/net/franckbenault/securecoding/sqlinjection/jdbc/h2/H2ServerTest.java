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

public class H2ServerTest {
	
	private static DBServerInterface server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		server = new H2Server();
		server.start();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		server.stop();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreatePerson() throws SQLException {
		
		Person person =server.createPerson("firstName", "lastName");
		assertNotNull(person);
	}
	
	@Test
	public void testCountTables() throws SQLException {
		
		assertEquals(server.countTables(),1);

	}

}
