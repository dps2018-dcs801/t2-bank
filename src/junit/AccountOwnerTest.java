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
	public void authenticateOwner() {
		AccountOwner accountOwner = new AccountOwner("Goat","1234$");
		accountOwner.put();
		assertEquals("Valid Password failed Authentication","valid", accountOwner.authenticate("1234$"));
		assertEquals("Incorrect failure message first failed authentication","Invalid Password", accountOwner.authenticate("1235$"));
		assertEquals("Valid Password failed Authentication","valid", accountOwner.authenticate("1234$"));
		assertEquals("Incorrect failure message second failed authentication","Two failed login attempts - contact bank for password reset", accountOwner.authenticate("1236$"));
		Assert.assertNotEquals("Valid Password did not fail after two failed attempts","valid", accountOwner.authenticate("1234$"));
		assertEquals("Incorrect failure message third failed authentication","Contact bank for password reset", accountOwner.authenticate("1237$"));
		Assert.assertNotEquals("Valid Password did not fail after three failed attempts","valid", accountOwner.authenticate("1234$"));		
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
	//Use advanced goat recognition tech to detect goats
	public void isAccountOwnerAGoat()
	{
		String name = "Goat";
		Assert.assertEquals("valid", AccountOwner.validateName(name));
	}
	
	@Test
	//Use advanced zombie recognition tech to detect goats
	public void isAccountOwnerAZombie()
	{
		String name = "Zombie";
		Assert.assertEquals("valid", AccountOwner.validateName(name));
	}
	
	@Test
	public void isAccountOwnerAnAlien()
	{
		String name = "Alien";
		Assert.assertEquals("valid", AccountOwner.validateName(name));
	}
	
	@Test
	public void isAccountOwnerNameHaveMultipleEmptySpaces()
	{
		String name = "  ";
		Assert.assertEquals("Name must not contain two consecutive spaces",
				AccountOwner.validateName(name));
	}
	
	@Test
	public void isAccountOwnerNameStartWithEmptySpace()
	{
		String name = " John";
		Assert.assertEquals("Name must not start with a space",
				AccountOwner.validateName(name));
	}
	
	@Test
	public void isAccountOwnerNameEndWithEmptySpace()
	{
		String name = "John ";
		Assert.assertEquals("Name must not end with a space",
				AccountOwner.validateName(name));
	}
	public void isAccountOwnerNameContainsNumber()
	{
		String name = "9";
		Assert.assertEquals("Name must not contain a number",
				AccountOwner.validateName(name));
	}
}
