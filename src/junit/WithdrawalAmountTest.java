package junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Deposit;
import banksystem.Withdrawal;
import database.Database;

public class WithdrawalAmountTest{
	Database dataBase = Database.getInstance();

	@Before
	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		dataBase.eraseFile();
		dataBase.load();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void withdrawalAmountCannotBeGreater() {		
		AccountOwner owner = new AccountOwner("First Last", "P$2222");
		owner.setId("O1002");
		owner.put(); 		

		Account account = new Account(owner.getId(), "Checking", "50.00");
		account.setId("A1004");
		account.put (); 

		Withdrawal withdrawal = new Withdrawal(owner.getId(), account.getId(), "100");
		assertEquals("Withdrawal amount cannot be greater than balance", withdrawal.updateBalance(owner.getPassword()));
	}
}
	
	
