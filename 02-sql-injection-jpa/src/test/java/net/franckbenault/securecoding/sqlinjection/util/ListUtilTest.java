package net.franckbenault.securecoding.sqlinjection.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testListToString() {
		
		List<String> input = new ArrayList<String>();
		
		String output =ListUtil.listToString(input);
		assertEquals(output, "''");
		
		input.add("aa");
		output =ListUtil.listToString(input);
		assertEquals(output, "'aa'");
		
		input.add("bb");		
		output =ListUtil.listToString(input);
		assertEquals(output, "'aa','bb'");
	}

}
