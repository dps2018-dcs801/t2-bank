package junit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import database.Database;
import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Deposit;

public class DepositUpdateTest {

	Database dataBase = Database.getInstance();

	@Before
	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		dataBase.eraseFile();
		dataBase.load();
	}

	@Test
	public void UpdateDeposit() {
		Account newAccount = new Account("O1001", "Checking", "50.00");
		newAccount.put();

		AccountOwner newAccountOwner = new AccountOwner("Michael", "M$09230w");
		newAccountOwner.put();

		Deposit newDeposit = new Deposit("O1001", "A1001", "100.00");

		Assert.assertEquals("valid", newDeposit.updateBalance("M$09230w"));
		Assert.assertEquals("150.00", newAccount.getBalance());

		newDeposit.put();

	}

}
