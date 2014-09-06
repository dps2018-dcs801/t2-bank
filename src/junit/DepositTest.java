package junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import database.Database;
import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Deposit;

public class DepositTest {

	Database dataBase = Database.getInstance();

	@Before
	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		dataBase.eraseFile();
		dataBase.load();
	}
	
	@Test
	public void negativeDepositAmount() {
		String depositAmount = "-50";
		Assert.assertEquals("Deposit amount cannot be negative",
				Deposit.validateDepositAmount(depositAmount));
	}

	@Test
	public void zeroDepositAmount() {
		String depositAmount = "0";
		Assert.assertEquals("Deposit amount cannot be zero",
				Deposit.validateDepositAmount(depositAmount));
	}

	@Test
	public void dollarsAndCents() {
		String depositAmount = "50.25";
		Assert.assertEquals("valid",
				Deposit.validateDepositAmount(depositAmount));
	}


	@Test
	public void validDepositAmount() {
		String password = "P!";
		AccountOwner owner = new AccountOwner("firstName lastName", "P!");
		owner.put();
		Account account = new Account(owner.getId(), "Checking", "100.00");
		account.put();
		Deposit deposit = new Deposit(owner.getId(), account.getId(), "50.00");
		String result = deposit.updateBalance(password);
		Assert.assertEquals("valid", result);

		Assert.assertEquals("150.00", account.getBalance());
		Database.dump("End of valid Deposit Amount");
	}
	
	@Test
	public void createDepositAndAddToDatabase() {
		Deposit deposit = new Deposit("O1001", "A1001", "50.00");
		deposit.put();
		Assert.assertEquals("D1001", deposit.getId());
	}
	
}
