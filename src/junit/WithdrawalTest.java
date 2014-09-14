package junit;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import banksystem.AccountOwner;
import banksystem.Utilities;
import banksystem.Withdrawal;

public class WithdrawalTest {

	@Test
	public void nonExistingWithdrawalId() {
		assertEquals("Invalid Withdrawal ID", Withdrawal.validateWithdrawalId("W1001"));
	}

	@Test
	public void existingWithdrawalId() {
		Withdrawal withdrawalId = new Withdrawal();
		withdrawalId.put();
		assertEquals("W1001", withdrawalId.getId());
		assertEquals("valid", Withdrawal.validateWithdrawalId("W1001"));
		//to test W1001 exists, causing fail assertEquals("Invalid Withdrawal ID", Withdrawal.validateWithdrawalId("W1001"));
		assertEquals("Invalid Withdrawal ID", Withdrawal.validateWithdrawalId("W1002"));
		withdrawalId.put();
		//to test W1001 exists, causing fail assertEquals("Invalid Withdrawal ID", Withdrawal.validateWithdrawalId("W1002"));
		assertEquals("valid", Withdrawal.validateWithdrawalId("W1002"));
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
	 
	
}

