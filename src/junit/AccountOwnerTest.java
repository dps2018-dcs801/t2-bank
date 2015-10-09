package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import banksystem.AccountOwner;
import database.Database;


public class AccountOwnerTest {
	Database dataBase = Database.getInstance();

	@Before
	public void setUp() throws Exception {
		Database.setFileName("test.dat");
		dataBase.eraseFile();
		dataBase.load();
	}

	@Test
	public void nonExistingOwner() {
		assertEquals("Invalid Account Owner ID", AccountOwner.validateOwnerId("O1001"));
	}
	
	@Test
	public void existingOwner() {
		AccountOwner accountOwner = new AccountOwner();
		accountOwner.put();
		assertEquals("O1001", accountOwner.getId());
		assertEquals("valid", AccountOwner.validateOwnerId("O1001"));
		assertEquals("Invalid Account Owner ID", AccountOwner.validateOwnerId("O1002"));
	}
	
	@Test
	public void linkedAccount() {
		AccountOwner accountOwner = new AccountOwner();
		accountOwner.put();		
		Account account = new Account(accountOwner.getId(), "Checking", "100.00");
		account.put ();
		assertEquals("O1001", account.getOwnerId());
		Database.dump();
	}
	
	
	@Test
	public void validName() {
		String name = "First Last";
		Assert.assertEquals("valid", AccountOwner.validateName(name));
	}

	@Test
	public void minLength() {
		String name = "A";
		Assert.assertEquals("Name must be greater than 1 character",
				AccountOwner.validateName(name));
	}

	@Test
	public void maxLength() {
		String name = "012345678901234567890123456789N";
		Assert.assertEquals("Name must be less than 30 characters",
				AccountOwner.validateName(name));
	}
	
	@Test
	public void writeOwnerToDatabase() {
		AccountOwner owner = new AccountOwner("owner", "J$");
		owner.put();

		String ownerId = owner.getId();
		AccountOwner ownerWrittenToDatabase = AccountOwner.get(ownerId);

		Assert.assertEquals(ownerId, ownerWrittenToDatabase.getId());
		Assert.assertEquals("owner", ownerWrittenToDatabase.data.name);
		Assert.assertEquals("J$", ownerWrittenToDatabase.data.password);

	}
	

	@Test
	//Use advanced goat recongnition tech to detect goats
	public void isAccountOwnerAGoat()
	{
		String name = "Goat";
		Assert.assertEquals("valid", AccountOwner.validateName(name));
	}
	
	@Test
	//Use advanced zombie recongnition tech to detect goats
	public void isAccountOwnerAZombie()
	{
		String name = "Zombie";
		Assert.assertEquals("valid", AccountOwner.validateName(name));
	}
	
}
