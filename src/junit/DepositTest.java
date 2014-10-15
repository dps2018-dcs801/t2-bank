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
	 Account newAccount = new Account("01001", "Checking", "50.00");
	 Assert.assertEquals("Invalid Password", newDeposit.updateBalance("P$2222"));
	 Assert.assertEquals("valid", newDeposit.updateBalance("M$09230w"));
	 Assert.assertEquals("150.00", newAccount.getBalance());
	 newDeposit.put();
	 Assert.assertEquals("valid", newDeposit.updateBalance("M$09230w"));
	 Assert.assertEquals("250.00", newAccount.getBalance());
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
 
 public void testAccountOwnerId() {
	 AccountOwner accountOwner1 = new AccountOwner("John Doe", "PW1@");
	 Account account1 = new Account("O1001","Checking","1.00");
	 Deposit deposit1 = new Deposit ("O1001","A1004","1.00");
	 Assert.assertEquals("Invalid Account Owner ID", accountOwner1.validateOwnerId("O1001")); // red
	 // Assert.assertEquals("valid", accountOwner1.validateOwnerId("O1001")); // green
	 // Assert.assertEquals("Invalid Account Owner ID", accountOwner1.validateOwnerId("O1002")); // green
	 //Assert.assertEquals("valid", accountOwner1.validateOwnerId("O1002")); // red
 }
/*
 public void testAccountOwnerPassword() {
	 AccountOwner accountOwner1 = new AccountOwner("John Doe", " PW1@ ");
	 Account account1 = new Account("O1002","Checking","1.00");
	 Deposit deposit1 = new Deposit ("O1002","A1004","1.00");
	 // Assert.assertEquals("Invalid Password", accountOwner1.validateOwnerPassword("PW1@")); // red
	 // Assert.assertEquals("Invalid Account Owner ID", accountOwner1. validateOwnerPassword ("P$2222)); // green
	 // Assert.assertEquals("valid", accountOwner1. validateOwnerPassword ("PW1@")); // green
	 Assert.assertEquals("valid", accountOwner1. validateOwnerPassword ("P$2222")); // red
*/
 
}//End DepositTest