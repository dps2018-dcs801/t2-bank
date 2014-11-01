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
import banksystem.Withdrawal;
import database.Database;

public class WithdrawalTest  {

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
 public void UpdateWithdrawal(){
	    //Testing withdrawal amount and update account to new balance. UAT 5.1
	 	AccountOwner newAccountOwner = new AccountOwner("Michael Powell", "P$1111");
	    newAccountOwner.put();
	    AccountOwner newAccountOwner2 = new AccountOwner("Ann Singh", "P$2222");
	    newAccountOwner2.put();
	    
	    
	 	Account newAccount = new Account("O1001", "Checking", "50.00");
	    newAccount.put();
	    Account newAccount2 = new Account("O1001", "Savings", "50.00");
	    newAccount2.put();
	    Account newAccount3 = new Account("O1002", "Savings", "50.00");
	    newAccount3.put();
	    Account newAccount4 = new Account("O1002", "Checking", "100.00");
	    newAccount4.put();
	    
	    
	 	Withdrawal newWithdrawal = new Withdrawal("O1002", "A1004", "50.00");
	    
	    Assert.assertEquals("Invalid Password", newWithdrawal.updateBalance("P@1234"));
	    Assert.assertEquals("valid", newWithdrawal.updateBalance("P$2222"));
	    Assert.assertEquals("50.00", newAccount4.getBalance());
 }

 
 @Test
 public void WithdrawalNotNegative() { 
	    //Testing deposits do not contain a negative amount. UAT 5.2
	    Withdrawal withdrawal = new Withdrawal();
	    //Assert.assertEquals("valid", withdrawal.validateWithdrawalAmount("-100"));
	    Assert.assertEquals("Withdrawal amount cannot be negative", withdrawal.validateWithdrawalAmount("-100"));
 }
 
 @Test 
//5.7
public void testWithdrawalAmountIsNumeric() {
Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","1.00");
Assert.assertEquals("Withdrawal amount must be numeric",  withdrawal1.validateWithdrawalAmount("23w")); 
Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount("23.00")) ; 
}

 @Test 
//5.8
public void testwithdrawalAmountMustBeDollarsAndCents () {
Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","50.00");
Assert.assertEquals("Amount must be dollars and cents", withdrawal1.validateWithdrawalAmount("1.234"));
Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount("1.23")); 
}

 
/*
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
 
 
 @Test 
 public void testDepositAmountCannotBeZero() {
	 //Testing deposit amounts cannot be any variation of 0.  UAT 3.5
	 Deposit deposit1 = new Deposit ("O1002","A1004","1.00");
     //Assert.assertEquals("valid", deposit1.validateDepositAmount("0.00")) ;
	 Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("0.00")) ; 
	 Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("00")) ;  
	 //Assert.assertEquals("valid", deposit1.validateDepositAmount("0")) ;    
	 Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("0")) ;    
	 //Assert.assertEquals("Deposit amount cannot be zero", deposit1.validateDepositAmount("1.00")) ; 
	 Assert.assertEquals("valid", deposit1.validateDepositAmount("1.00")) ; 
}


 @Test
 public void testAccountOwnerId() {
	 AccountOwner accountOwner1 = new AccountOwner("John Doe", "PW1@");
	 accountOwner1.put(); 
	 Account account1 = new Account("O1001","Checking","1.00");
	 account1.put();
	 Deposit deposit1 = new Deposit ("O1001","A1004","1.00");
	 //Assert.assertEquals("Invalid Account Owner ID", accountOwner1.validateOwnerId("O1001")); 
	  Assert.assertEquals("valid", accountOwner1.validateOwnerId("O1001"));
	  Assert.assertEquals("Invalid Account Owner ID", accountOwner1.validateOwnerId("O1002")); 
	 //Assert.assertEquals("valid", accountOwner1.validateOwnerId("O1002")); 
	  }

 @Test
 public void testAccountOwnerPassword() {
	 AccountOwner accountOwner1 = new AccountOwner("John Doe", "PW1@");
	 accountOwner1.put(); 
	 Account account1 = new Account("O1002","Checking","1.00");
	 account1.put(); 
	 Deposit deposit1 = new Deposit ("O1002","A1004","1.00");
	 //Assert.assertEquals("valid", (PasswordManager.authenticate(accountOwner1.getPassword(), "P$2222"))); 
	 Assert.assertEquals("Invalid Password", (PasswordManager.authenticate(accountOwner1.getPassword(), "P$2222")));
	 //Assert.assertEquals("Invalid Password", (PasswordManager.authenticate(accountOwner1.getPassword(), "PW1@")));
	 Assert.assertEquals("valid", (PasswordManager.authenticate(accountOwner1.getPassword(), "PW1@"))); 
	 
 } */
 
}//End DepositTest