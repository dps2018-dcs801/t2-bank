package junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.PasswordManager;
import banksystem.Utilities;
import banksystem.Withdrawal;
import banksystem.WithdrawalData;
import database.Database;

public class WithdrawalTest  {

 Database dataBase = Database.getInstance();

 @Before
 public void setUp() throws Exception {
  Database.setFileName("test.dat");
  dataBase.eraseFile();
  dataBase.load();
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
  Account newAccount4 = new Account("O1002", "Checking", "250.00");
  newAccount4.put();
  
  Assert.assertEquals("A1004", Account.get("A1004").getId());
  Assert.assertEquals("O1002", AccountOwner.get("O1002").getId());  
 }

 @After
 public void tearDown() throws Exception {
 }

 @Test
 public void UpdateWithdrawal(){
	    //Testing proper password and withdrawal amount updates account to correct new balance. UAT 5.1, 5.3 & 5.4
	 		    
	 	Withdrawal newWithdrawal = new Withdrawal("O1002", "A1004", "50.00");
	    Assert.assertEquals("Invalid Password", newWithdrawal.updateBalance("P@1234"));
	    Assert.assertEquals("valid", newWithdrawal.updateBalance("P$2222"));
	    Account newAccount4 = Account.get(newWithdrawal.getAccountId());
	    Assert.assertEquals("200.00", newAccount4.getBalance());
	    newWithdrawal.put();
	    
	    
	    newWithdrawal = new Withdrawal("O1002", "A1004", "100.00");
	    Assert.assertEquals("valid", newWithdrawal.updateBalance("P$2222"));
	    newAccount4 = Account.get(newWithdrawal.getAccountId());
	    Assert.assertEquals("100.00", newAccount4.getBalance());
	    
	    
	    newWithdrawal = new Withdrawal("O1002", "A1004", "50.25");
	    Assert.assertEquals("valid", newWithdrawal.updateBalance("P$2222"));
	    newAccount4 = Account.get(newWithdrawal.getAccountId());
	    Assert.assertEquals("49.75", newAccount4.getBalance());
	    
 }

/*
 @Test
 public void WithdrawalNotNegative() { 
	 //Testing withdrawals do not contain a negative amount. UAT 5.2
	 Withdrawal withdrawal = new Withdrawal("01002", "A1004", "-100");
	 //Assert.assertEquals("valid", withdrawal.validateWithdrawalAmount("-100"));
	 Assert.assertEquals("Withdrawal amount cannot be negative", withdrawal.validateWithdrawalAmount(withdrawal.getWithdrawalAmount()));
 }
 
 
 @Test
 public void amountNotGreaterThanBalance() {
	//Testing withdrawal amounts are not greater than balances, so there cannot be negative balances after withdrawing. UAT 5.5
	
	 Withdrawal newWithdrawal = new Withdrawal("O1002", "A1004", "350.00");
	 Assert.assertEquals("Withdrawal amount cannot be greater than balance", newWithdrawal.validateWithdrawalAmount(newWithdrawal.getWithdrawalAmount())); 
 }
 
 
 @Test
 public void notEmpty() {
	 //Testing withdrawals do not contain empty values. UAT 5.6
 	 Withdrawal newWithdrawal = new Withdrawal("O1001","A1001","");
	 Assert.assertEquals("Withdrawal amount cannot be empty", newWithdrawal.validateWithdrawalAmount(newWithdrawal.getWithdrawalAmount()));
 }
 
 @Test 
 public void testWithdrawalAmountIsNumeric() {
	//Testing withdrawals do not contain any char or string data. UAT 5.7
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","23w");
	 Assert.assertEquals("Withdrawal amount must be numeric",  withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount())); 
	 withdrawal1 = new Withdrawal ("O1002","A1004","23.00");
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount())); 
}

 @Test 
 public void testwithdrawalAmountMustBeDollarsAndCents () {
	//Testing withdrawals do not contain more than two decimal places. UAT 5.8
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","1.234");
	 Assert.assertEquals("Amount must be dollars and cents", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount()));
	 withdrawal1 = new Withdrawal ("O1002","A1004","1.23");
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount())); 
}
 
 @Test
 public void TestWithdrawalAmountCannotBeBlank() {
	 //Testing withdrawals do not contain spaces (blanks). UAT 5.9
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004"," ");
	 Assert.assertEquals("Withdrawal amount cannot be blank", withdrawal1.validateWithdrawalAmount(" "));
	 withdrawal1 = new Withdrawal ("O1002","A1004","      ");
	 Assert.assertEquals("Withdrawal amount cannot be blank", withdrawal1.validateWithdrawalAmount(" "));
	 withdrawal1 = new Withdrawal ("O1002","A1004","50.00");
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount()));
}
	
 @Test
 public void TestWithdrawalAmountCannotBeZero() {
	 //Testing withdrawals do not contain a zero amount. UAT 5.10
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","0.00");
	 Assert.assertEquals("Withdrawal amount cannot be zero", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount()));
	 withdrawal1 = new Withdrawal ("O1002","A1004","0.0");
	 Assert.assertEquals("Withdrawal amount cannot be zero", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount()));
	 withdrawal1 = new Withdrawal ("O1002","A1004","0");
	 Assert.assertEquals("Withdrawal amount cannot be zero", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount()));
	 withdrawal1 = new Withdrawal ("O1002","A1004",".00");
	 Assert.assertEquals("Withdrawal amount cannot be zero", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount()));
	 withdrawal1 = new Withdrawal ("O1002","A1004","1.00");
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount(withdrawal1.getWithdrawalAmount()));
}
  
 */
}//End DepositTest