package junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import database.Database;

public class AccountTest {

	Database database = Database.getInstance();

	@Before
	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAccountId() {

		Account account = new Account("ownerId", "Checking", "100.00");
		String nextAccountId = Account.getNextId();
		Assert.assertEquals("A1001", nextAccountId);

		account.put();
		Database.dump("Write it to the database:");
		account = Account.get(nextAccountId);
		System.out.println(account);
		Assert.assertEquals(nextAccountId, account.getId());

		String afterAddIndex = Account.getNextId();
		Integer startingId = new Integer(nextAccountId.substring(1));
		Integer endingId = new Integer(afterAddIndex.substring(1));
		Assert.assertEquals(startingId.intValue(), endingId.intValue() - 1);
	}

	@Test
	public void checkAccountType() {

		Account account = new Account("O1001", "Checking", "50");
		Assert.assertEquals("valid", account.validateAccountType());
		account = new Account("O1001", "Savings", "50");

		Assert.assertEquals("valid", account.validateAccountType());
		account = new Account("O1001", "Money Market", "50");

		Assert.assertEquals("Account Type invalid",
				account.validateAccountType());
	}

	@Test
	public void checkMoney() {

		Account account = new Account("O1001", "Checking", "50");
		Assert.assertEquals("valid", account.validateBalance());
		Assert.assertEquals("valid", account.validateBalance("50"));
		account = new Account("O1001", "Savings", "x");
		Assert.assertEquals("Balance must be numeric", account.validateBalance());
		Assert.assertEquals("Balance must be numeric", account.validateBalance("x"));

	}

	@Test
	public void addMoney() {
		Account account = new Account("O1001", "Checking", "50");
		Assert.assertEquals("valid",account.add("100"));
		Assert.assertEquals("150.00",account.getBalance());
	}
	
	@Test
	public void subMoney() {
		Account account = new Account("O1001", "Checking", "50");
		Assert.assertEquals("valid",account.subtract("50"));
		Assert.assertEquals("0.00",account.getBalance());
	}
	
	@Test
	public void negativeBalance() {
		String balance = "-50";
		Assert.assertEquals("Balance cannot be negative",Account.validateBalance(balance) );		
	}

	@Test
	public void zeroBalance() {
		String balance = "0";
		Assert.assertEquals("valid",Account.validateBalance(balance) );		
	}
	
	@Test
	public void dollarsAndCents() {
		String balance = "50.25";
		Assert.assertEquals("valid",Account.validateBalance(balance) );		
	}

	

	@Test
	public void accountDoesNotExistMessage() {
		Assert.assertEquals("Account should not exist", "Invalid Account ID", Account.validateAccountExists("A1001"));
	}
	
}
