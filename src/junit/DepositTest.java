package junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Deposit;
import banksystem.DepositData;
import banksystem.Utilities;
import database.Database;

public class DepositTest  {

 Database dataBase = Database.getInstance();

 @Before
 public void setUp() throws Exception {
  Database.setFileName("test.dat");
  dataBase.eraseFile();
  dataBase.load();
  
//Creating new records for AccountOwner and AccountID, so there is data in the database
	 AccountOwner newAccountOwner = new AccountOwner("Michael Sidaras-Tirrito", "M$09230w" );
	 newAccountOwner.put();
	 Account newAccount = new Account("O1001", "Checking", "50.00");
	 newAccount.put();
	 
	 //Testing to verify the correct fields of data were written to the database for AccountOwner
	 Assert.assertEquals("valid", AccountOwner.validateOwnerId("O1001"));
	 Assert.assertEquals("Michael Sidaras-Tirrito", AccountOwner.getName());
	 Assert.assertEquals("M$09230w", AccountOwner.getPassword());
	 
	 //Testing to verify the correct fields of data were written to the database for Account
	 Assert.assertEquals("O1001", Account.getOwnerId());
	 Assert.assertEquals("Checking", Account.getAccountType());
	 Assert.assertEquals("50.00", Account.getBalance());
 }

 @After
 public void tearDown() throws Exception {
 }

 
 @Test
 public void notSpaces() {
	 //Testing a new deposit for correct values.  No spaces allowed. UAT 3.3
	 Assert.assertEquals("Deposit amount cannot be space(s)", Deposit.validateDepositAmount("       "));
	 Assert.assertEquals("Deposit amount cannot be space(s)", Deposit.validateDepositAmount(" ")); 
 }
 

 @Test
 public void notEmpty() {
	 //Testing a new deposit for correct values.  No empty values allowed. UAT 3.4
	 Assert.assertEquals("Deposit amount cannot be empty", Deposit.validateDepositAmount("")); 
 }
 
 //Ann Singh
 @Test
 public void UpdateDeposit(){
 Deposit newDeposit = new Deposit("O1001", "A1001", "100.00");
 Assert.assertEquals("Invalid Password", newDeposit.updateBalance("P$2222", "O1001", "A1001", "100.00"));
 Assert.assertEquals("valid", newDeposit.updateBalance("M$09230w", "O1001", "A1001", "100.00"));
 Assert.assertEquals("150.00", Account.getBalance());
 newDeposit.put();
 Assert.assertEquals("valid", newDeposit.updateBalance("M$09230w", "O1001", "A1001", "100.00"));
 Assert.assertEquals("250.00", Account.getBalance());
 }
 
}