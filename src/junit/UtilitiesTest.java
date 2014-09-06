package junit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import banksystem.Utilities;

public class UtilitiesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMoneyAmount() {
		Assert.assertEquals(20001, Utilities.toCents("200.01"));
		Assert.assertEquals(-1, Utilities.toCents("200.011"));
		Assert.assertEquals(1, Utilities.toCents(".01"));

	}

	@Test
	public void testValidMoney() {
		Assert.assertTrue(Utilities.isMoney("100.00"));
		Assert.assertTrue(Utilities.isMoney("100"));

		Assert.assertTrue(Utilities.isMoney("1.00"));
		Assert.assertTrue(Utilities.isMoney(".01"));
		Assert.assertTrue(Utilities.isMoney(" 0.01"));
		Assert.assertTrue(Utilities.isMoney("1.40"));

		Assert.assertFalse(Utilities.isMoney("$$1.40"));

		Assert.assertTrue(Utilities.isMoney("0.40"));
		Assert.assertTrue(Utilities.isMoney("1.1"));

		Assert.assertFalse(Utilities.isMoney("$145.8976"));
		Assert.assertFalse(Utilities.isMoney("0.112"));
		Assert.assertTrue(Utilities.isMoney(".23"));
		Assert.assertFalse(Utilities.isMoney("11.2345"));
		Assert.assertFalse(Utilities.isMoney("-.23"));
		Assert.assertFalse(Utilities.isMoney("1,234"));
		Assert.assertFalse(Utilities.isMoney("1OO.00"));

		Assert.assertFalse(Utilities.isMoney("$100.00"));

	
	}

	@Test
	public void testBlankInvalidMoney() {
		Assert.assertFalse(Utilities.isMoney(""));
	}
	
	@Test
	public void testCovertToMoney()
	{
		Assert.assertTrue("100.00".equals(Utilities.toMoney(10000)));
		//System.out.println(Utilities.toMoney(10));
		Assert.assertTrue("0.10".equals(Utilities.toMoney(10)));
		//System.out.println(Utilities.toMoney(100));
		Assert.assertTrue("1.00".equals(Utilities.toMoney(100)));
	}
	
}
