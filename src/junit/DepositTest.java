package junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.Deposit;
import banksystem.DepositData;
import banksystem.PasswordManager;
import banksystem.Utilities;
import database.Database;

public class DepositTest  {

 Database dataBase = Database.getInstance();

 @Before
 public void setUp() throws Exception {
  Database.setFileName("test.dat");
  dataBase.eraseFile();
  dataBase.load();
  
/*Creating new records for AccountOwner and AccountID, so there is data in the database
	 AccountOwner newAccountOwner = new AccountOwner("Michael Sidaras-Tirrito", "M$09230w" );
	 newAccountOwner.put();
	 Account newAccount = new Account("O1001", "Checking", "50.00");
	 newAccount.put();
	 
	 //Testing to verify the correct fields of data were written to the database for AccountOwner
	 Assert.assertEquals("valid", newAccountOwner.validateOwnerId("O1001"));
	 Assert.assertEquals("Michael Sidaras-Tirrito", AccountOwner.getName());
	 Assert.assertEquals("M$09230w", newAccountOwner.getPassword());
	 
	 //Testing to verify the correct fields of data were written to the database for Account
	 Assert.assertEquals("O1001", newAccount.getOwnerId());
	 Assert.assertEquals("Checking", newAccount.getAccountType());
	 Assert.assertEquals("50.00", newAccount.getBalance());*/
 }

 @After
 public void tearDown() throws Exception {
 }


 //Ann Singh
 @Test
 public void UpdateDeposit(){
	 //Testing deposits will correctly calculate balance. UAT 3.1
	 Deposit newDeposit = new Deposit("O1001", "A1001", "100.00");
	 Account newAccount = new Account("O1001", "Checking", "50.00");
	 newAccount.put();// fixed mistake causing nullexception error
	 AccountOwner newAccountOwner = new AccountOwner("Michael", "M$09230w");// fixed mistake causing nullexception error
	 newAccountOwner.put();// fixed mistake causing nullexception error
	 Assert.assertEquals("Invalid Password", newDeposit.updateBalance("P$2222"));
	 Assert.assertEquals("valid", newDeposit.updateBalance("M$09230w"));
	 Assert.assertEquals("150.00", newAccount.getBalance());
	 newDeposit.put();
	 Assert.assertEquals("valid", newDeposit.updateBalance("M$09230w"));
	 Assert.assertEquals("250.00", newAccount.getBalance());
	 

	 /* Reason for java.lang.nullpointerexception error found at 11PM on 10/17/2014 by MST
	  * In cleaning up the code, I commented out the block of code I added in the @before block
	  * which was originally creating and putting one AccountOwner record and one Account record to the
	  * database test.dat since it was not used by my other team members, and they chose to create
	  * class objects in each test.  However, in this method, I forgot to write the Account object
	  * to the database file after creating it, and I forgot to create and write the AccountOwner
	  * object altogether.  Since the @before no longer creates the object, this resulted in the
	  * AccountOwner.get() method returning a null value, which caused the PasswordManager.getPassword()
	  * method to fail when I tried to pass in the ownerID of an empty object. Fixed by adding the three
	  * missing statements, and it now works again.
	  * 
	  * Sorry for the delay my blunder caused our team this week, gang.
	  */

 }
 
 @Test 
 public void DepositNotNegative() { 
	//Testing deposits do not contain a negative amount. UAT 3.2
	 Deposit deposit = new Deposit();
	 //Assert.assertEquals("valid", deposit.validateDepositAmount("-110"));
	 Assert.assertEquals("Deposit amount cannot be negative", deposit.validateDepositAmount("-110"));
 }


 // Michael Sidaras-Tirrito
 @Test
 public void notSpaces() {
	 //Testing a new deposit for correct values.  No spaces allowed. UAT 3.3
	 Deposit newDeposit = new Deposit("O1001","A1001","    ");
	 Assert.assertEquals("Deposit amount cannot be space(s)", newDeposit.validateDepositAmount(newDeposit.getDepositAmount()));
	 Assert.assertEquals("Deposit amount cannot be space(s)", newDeposit.validateDepositAmount(" ")); 
 }
 

 @Test
 public void notEmpty() {
	 //Testing a new deposit for correct values.  No empty values allowed. UAT 3.4
	 Deposit newDeposit = new Deposit("O1001","A1001","");
	 Assert.assertEquals("Deposit amount cannot be empty", newDeposit.validateDepositAmount(newDeposit.getDepositAmount())); 
 }
 
 
 // Pedro Vasseur
 @Test 
 public void testDepositAmountCannotBeZero() {
	 //Testing deposit amounts cannot be any variation of 0.  UAT 3.5
	 /* NOT SURE WHY THESE ARE HERE OR NEEDED.  ASSUMING THEY WERE COMMENTED OUT AND NOT DELETED. WILL BLOCK
	  * COMMENT THEM ALL OUT AT THIS POINT.  PEDRO, PLEASE CONFIRM YOUR APPROVAL TO DELETE THEM OR EXPLAIN 
	  * WHY YOU FEEL WE NEED THEM, SO I CAN UNDERSTAND THE LOGIC. -- MST
	  
	AccountOwner accountOwner1 = new AccountOwner("John Doe", "PW1@");
	Account account1 = new Account("O1002","A1004","1.00");
	AccountCreateView accountView1 = new AccountCreateView();
	AccountOwnerCreateView accountOwnerView1 = new AccountOwnerCreateView();
	DepositCreateView depositGUI = new DepositCreateView();
	Account account1 = new Account("O1002","A1004","1.00");
	*/
	 
	 Deposit deposit1 = new Deposit ("O1002","A1004","1.00");
     //Assert.assertEquals("valid", deposit1.validateDepositAmount("0.00")) ; // red
	 Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("0.00")) ; // green
	 Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("00")) ;   // green
	 //Assert.assertEquals("valid", deposit1.validateDepositAmount("0")) ;    // red
	 Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("0")) ;    // green
	 //Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("1.00")) ; // red
	 Assert.assertEquals("valid", deposit1.validateDepositAmount("1.00")) ; // green
}

 
 @Test 
 public void testDepositAmountIsNumeric() {
	 //Testing to make sure only numeric input is allowed, and not any string input. UAT 3.6
	 
	 /* NOT SURE WHY THESE ARE HERE OR NEEDED.  ASSUMING THEY WERE COMMENTED OUT AND NOT DELETED. WILL BLOCK
	  * COMMENT THEM ALL OUT AT THIS POINT.  PEDRO, PLEASE CONFIRM YOUR APPROVAL TO DELETE THEM OR EXPLAIN 
	  * WHY YOU FEEL WE NEED THEM, SO I CAN UNDERSTAND THE LOGIC. -- MST
	 
	// AccountOwner accountOwner1 = new AccountOwner("John Doe", "PW1@");
      // Account account1 = new Account("O1002","A1004","1.00");
       
       */
	   
	 Deposit deposit1 = new Deposit ("O1002","A1004","1.00");
	 //Assert.assertEquals("valid", deposit1.validateDepositAmount("23w")) ; // red
	 Assert.assertEquals("Deposit amount must be numeric", deposit1.validateDepositAmount("23w")); // green
	 //Assert.assertEquals("Deposit amount must be numeric", deposit1.validateDepositAmount("23.00")); // red
 	 Assert.assertEquals("valid", deposit1.validateDepositAmount("23.00")) ; // green
 }
 
 @Test 
 public void testDepositAmountMustBeDollarsAndCents () {
	 //Testing to make sure numeric data fits precision of dollar values, to two decimals. UAT 3.9
	 
	 /* NOT SURE WHY THESE ARE HERE OR NEEDED.  PEDRO, PLEASE CONFIRM YOUR APPROVAL TO DELETE THEM OR EXPLAIN 
	  * WHY YOU FEEL WE NEED THEM, SO I CAN UNDERSTAND THE LOGIC. -- MST

	 AccountOwner accountOwner1 = new AccountOwner("John Doe", "P$2222");
	 Account account1 = new Account("O1002","A1004","1.00");
	 */
	 Deposit deposit1 = new Deposit ("O1002","A1004","50.00");
	//Assert.assertEquals("valid", deposit1.validateDepositAmount("1.234")); // red
	 Assert.assertEquals("Amount must be dollars and cents", deposit1.validateDepositAmount("1.234")); // green
	 //Assert.assertEquals("Amount must be dollars and cents", deposit1.validateDepositAmount("1.23")); // red
      Assert.assertEquals("valid", deposit1.validateDepositAmount("1.23")); // green
}
 
//Michael Powell
 @Test
 public void testAccountOwnerId() {
	 AccountOwner accountOwner1 = new AccountOwner("John Doe", "PW1@");
	 accountOwner1.put(); //added by MST
	 Account account1 = new Account("O1001","Checking","1.00");
	 account1.put();
	 Deposit deposit1 = new Deposit ("O1001","A1004","1.00");
	 //Assert.assertEquals("Invalid Account Owner ID", accountOwner1.validateOwnerId("O1001")); // red
	  Assert.assertEquals("valid", accountOwner1.validateOwnerId("O1001")); // green
	  Assert.assertEquals("Invalid Account Owner ID", accountOwner1.validateOwnerId("O1002")); // green
	 //Assert.assertEquals("valid", accountOwner1.validateOwnerId("O1002")); // red
 }

 @Test
 public void testAccountOwnerPassword() {
	 /* Logic Errors Found at 11:28PM on Friday Oct. 17th, 2014 by MST
	  * There are problems throughout MP's interpretation of this test and the usage of the 
	  * AccountOwner.validatePassword function in this code.  I will fix it.
	  * 1. The function he is looking for is called validatePassword(), not validateOwnerPassword()
	  * 2. The function he is using is the wrong function to test that the entered password is the
	  * correct password for this account owner that is being given a deposit.
	  * 3. We are not supposed to test to see if an entered password fits the acceptable standards for
	  * passwords.
	  * 4. AccountOwner.validatePassword() does just that which we are not supposed to check.  It tests
	  * to see if the entered password fits the acceptable standard for passwords.
	  * 5.  We are supposed to test to see if an entered password matches the stored password for the
	  * account owner that is being given the deposit.
	  * 6. The function to test that is authenticate(), not validatePassword()
	  * 7. We do not need to call upon AccountOwner.authenticate() since it just passes the buck by
	  * calling PasswordManager.authenticate() anyway.  We might just as well directly call
	  * PasswordManager.authenticate() statically.
	  */ 
	 AccountOwner accountOwner1 = new AccountOwner("John Doe", "PW1@");
	 accountOwner1.put(); //added by MST
	 Account account1 = new Account("O1002","Checking","1.00");
	 account1.put(); //added by MST
	 Deposit deposit1 = new Deposit ("O1002","A1004","1.00");
	 //Assert.assertEquals("valid", (PasswordManager.authenticate(accountOwner1.getPassword(), "P$2222"))); // red
	 Assert.assertEquals("Invalid Password", (PasswordManager.authenticate(accountOwner1.getPassword(), "P$2222"))); // green
	 //Assert.assertEquals("Invalid Password", (PasswordManager.authenticate(accountOwner1.getPassword(), "PW1@"))); // red
	 Assert.assertEquals("valid", (PasswordManager.authenticate(accountOwner1.getPassword(), "PW1@"))); // green
	 
 }
}//End DepositTest