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
  
  Assert.assertEquals("A1004", Account.get("A1004").getId());
  Assert.assertEquals("O1002", AccountOwner.get("O1002").getId());  
 }

 @After
 public void tearDown() throws Exception {
 }

 @Test
 public void UpdateWithdrawal(){
	    //Testing proper password and withdrawal amount updates account to correct new balance. UAT 5.1
	 	
	    
	    
	 	Withdrawal newWithdrawal = new Withdrawal("O1002", "A1004", "50.00");
	    
	    Assert.assertEquals("Invalid Password", newWithdrawal.updateBalance("P@1234"));
	    Assert.assertEquals("valid", newWithdrawal.updateBalance("P$2222"));
	    Account newAccount4 = Account.get(newWithdrawal.getAccountId());
	    Assert.assertEquals("50.00", newAccount4.getBalance());
 }

5.3    Given that Account Owner ID O1002 exists and the Password is P$2222 and account ID A1004 exists and the balance is 100 and we are making a withdrawal
  When we enter Account Owner O1002, Password P$2222, Account ID A1004 and withdrawal amount 100
  Then the account should be – Account Owner ID = O1002, Account ID = A1004, Account Type = Checking, Balance = 0.00


@Test public void UpdateWithdrawal()
{          
//Testing withdrawal amount and update account to new balance. UAT 5.3
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
Withdrawal newWithdrawal = new Withdrawal("O1002", "A1004", "100.00");
Assert.assertEquals("Invalid Password", newWithdrawal.updateBalance("P@1234"));
Assert.assertEquals("valid", newWithdrawal.updateBalance("P$2222"));
Assert.assertEquals("0.00", newAccount4.getBalance());
}


5.4    Given that Account Owner ID O1002 exists and the Password is P$2222 and account ID A1004 exists and the balance is 100 and we are making a withdrawal
  When we enter Account Owner O1002, Password P$2222, Account ID A1004 and withdrawal amount 50.25
  Then the account should be – Account Owner ID = O1002, Account ID = A1004, Account Type = Checking, Balance = 49.75



@Test public void UpdateWithdrawal()
{          
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
Withdrawal newWithdrawal = new Withdrawal("O1002", "A1004", "50.25");
Assert.assertEquals("Invalid Password", newWithdrawal.updateBalance("P@1234"));
Assert.assertEquals("valid", newWithdrawal.updateBalance("P$2222"));
Assert.assertEquals("49.75", newAccount4.getBalance());

 
 @Test
 public void WithdrawalNotNegative() { 
	 //Testing withdrawals do not contain a negative amount. UAT 5.2
	 Withdrawal withdrawal = new Withdrawal();
	 //Assert.assertEquals("valid", withdrawal.validateWithdrawalAmount("-100"));
	 Assert.assertEquals("Withdrawal amount cannot be negative", withdrawal.validateWithdrawalAmount("-100"));
 }
 
 
 @Test
 public void amountNotGreaterThanBalance() {
	//Testing withdrawal amounts are not greater than balances, so there cannot be negative balances after withdrawing. UAT 5.5
	
	 Withdrawal newWithdrawal = new Withdrawal("O1002", "A1004", "250.00");
	 System.out.println ("The account id for newWithdrawal is " +  newWithdrawal.getAccountId() );
	 Assert.assertEquals("Withdrawal amount cannot be greater than balance", newWithdrawal.validateWithdrawalAmount("250.00")); 
 }
 
 
 @Test
 public void notEmpty() {
	 //Testing withdrawals do not contain empty values. UAT 5.6
 	 Withdrawal newWithdrawal = new Withdrawal("O1001","A1001","");
	 Assert.assertEquals("Withdrawal amount cannot be empty", newWithdrawal.validateWithdrawalAmount(""));
 }
 
 @Test 
public void testWithdrawalAmountIsNumeric() {
	//Testing withdrawals do not contain any char or string data. UAT 5.7
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","1.00");
	 Assert.assertEquals("Withdrawal amount must be numeric",  withdrawal1.validateWithdrawalAmount("23w")); 
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount("23.00")) ; 
}

 @Test 
public void testwithdrawalAmountMustBeDollarsAndCents () {
	//Testing withdrawals do not contain more than two decimal places. UAT 5.8
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","50.00");
	 Assert.assertEquals("Amount must be dollars and cents", withdrawal1.validateWithdrawalAmount("1.234"));
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount("1.23")); 
}
 
 @Test
public void TestWithdrawalAmountCannotBeBlank() {
	 //Testing withdrawals do not contain spaces (blanks). UAT 5.9
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","50.00");
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount("1.00"));
	 Assert.assertEquals("Withdrawal amount cannot be blank", withdrawal1.validateWithdrawalAmount(" "));
}
	
  @Test
 	public void TestWithdrawalAmountCannotBeZero() {
	 //Testing withdrawals do not contain a zero amount. UAT 5.10
	 Withdrawal withdrawal1 = new Withdrawal ("O1002","A1004","50.00");
	 Assert.assertEquals("valid", withdrawal1.validateWithdrawalAmount("1.00"));
     Assert.assertEquals("Withdrawal amount cannot be zero", withdrawal1.validateWithdrawalAmount("0.00"));
}
  
 
}//End DepositTest