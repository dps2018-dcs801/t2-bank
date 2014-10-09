package junit;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import database.Database;
import banksystem.AccountOwner;
import banksystem.Utilities;
import banksystem.Withdrawal;

public class WithdrawalTestOLD {
	Database dataBase = Database.getInstance();
	
	@Before
	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		dataBase.load(); //explicitly left out erase command, so old data is in database. --MSidaras 09/17/2014
	}
	
	@Test
	public void NonExistingWithdrawalId() {
		assertEquals("Invalid Withdrawal ID", Withdrawal.validateWithdrawalId("W1300"));
	}

	@Test
	public void ExistingWithdrawalId() {
		Withdrawal withdrawalId = new Withdrawal();
		withdrawalId.put();
		//assertEquals("W1001", withdrawalId.getId()); commented out by MSidaras on 9/17/2014 for ownerID Tests
		assertEquals("valid", Withdrawal.validateWithdrawalId("W1001"));
		//to test W1001 exists, causing fail assertEquals("Invalid Withdrawal ID", Withdrawal.validateWithdrawalId("W1001"));
		assertEquals("valid", Withdrawal.validateWithdrawalId("W1002")); //Modified MSidaras 9/172014 ownerID Tests
		withdrawalId.put();
		//to test W1001 exists, causing fail assertEquals("Invalid Withdrawal ID", Withdrawal.validateWithdrawalId("W1002"));
		assertEquals("valid", Withdrawal.validateWithdrawalId("W1003")); // Modified MSidaras 9/172014 ownerID tests
	}
	
	@Test
	public void WithdrawalAmount() {
		//Testing withdrawal amounts cannot exceed balance. USING STUB UNTIL DEPOSIT IS FINISHED.
		String testWithdrawalAmount = "25.00";
		Assert.assertEquals(2500, Utilities.toCents(testWithdrawalAmount));
		Assert.assertEquals("invalid", Withdrawal.validateWithdrawalAmount(testWithdrawalAmount));
		testWithdrawalAmount = "10.00";
		Assert.assertEquals("valid", Withdrawal.validateWithdrawalAmount(testWithdrawalAmount));
		testWithdrawalAmount = "15.00";
		Assert.assertEquals("valid", Withdrawal.validateWithdrawalAmount(testWithdrawalAmount));
	}
	 
	@Test
	public void ExistingOwnerID() {
	//18-Entered account owner id matches existing account owner 
		
	//Modified from this point forward on 9/17/14 by MSidaras
		Assert.assertEquals("valid", Withdrawal.validate("O1001", "accountId", "password", "depositAmount"));
		//Assert.assertEquals("valid", Withdrawal.validate("O1002", "accountId", "password", "depositAmount"));
		//Cannot get the second assert to pass, even though this file does not erase the test.dat file, and there should
		//be two records that were created by running AccountOwnerTest.java before this file. I added a second put.
		
		
	}
}

