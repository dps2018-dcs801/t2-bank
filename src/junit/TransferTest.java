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
import banksystem.Transfer;
import banksystem.Utilities;
import banksystem.Withdrawal;
import database.Database;

public class TransferTest  {

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
 public void UpdateTransfer(){
	    //Testing proper password and transfer amount updates accounts to correct new balance. UAT 6.1, 6.3
	 		    
	 	Transfer newTransfer = new Transfer("O1001", "A1001",  "A1002", "25.00");
	    Assert.assertEquals("Invalid Password", newTransfer.validate("P@1234"));
	    Assert.assertEquals("valid", newTransfer.validate("P$1111"));
	    Assert.assertEquals("valid", newTransfer.transfer("P$1111"));
	    Assert.assertEquals("75.00", Account.get(newTransfer.getToAccountId()).getBalance());
	    Assert.assertEquals("25.00", Account.get(newTransfer.getFromAccountId()).getBalance());
	    
	    newTransfer = new Transfer("O1001", "A1001",  "A1002", "25.00");
	    Assert.assertEquals("valid", newTransfer.transfer("P$1111"));
	    Assert.assertEquals("100.00", Account.get(newTransfer.getToAccountId()).getBalance());
	    Assert.assertEquals("0.00", Account.get(newTransfer.getFromAccountId()).getBalance());
 }
 
 @Test
 public void amountNotGreaterThanBalance() {
	//Testing transfer amounts are not greater than the from account balance, so there cannot be negative
    //from account balance after transferring. UAT 6.2
	
	 Transfer newTransfer = new Transfer("O1001", "A1001",  "A1002", "225.00");
	 Assert.assertEquals("Transfer amount cannot be greater than the From Account balance", newTransfer.validateTransferAmount(newTransfer.getTransferAmount())); 
 }
 
 
 @Test
 public void toAccountDoesNotExist() {
	//Testing transfer to a non-existent account will be denied. UAT 6.4
	
	 Transfer newTransfer = new Transfer("O1001", "A1001",  "A1012", "25.00");
	 Assert.assertEquals("Transfer to account does not exist", Transfer.validate(newTransfer.getOwnerId(), newTransfer.getToAccountId(), newTransfer.getFromAccountId(), "P$1111", newTransfer.getTransferAmount()));
	 newTransfer = new Transfer("O1001", "A1001",  "A1002", "25.00");
	 Assert.assertEquals("valid", Transfer.validate(newTransfer.getOwnerId(), newTransfer.getToAccountId(), newTransfer.getFromAccountId(), "P$1111", newTransfer.getTransferAmount()));
 }
 
 
 @Test
 public void toAndFromAccountsNotSame() {
	//Testing transfer to account is different from the transfer from account. UAT 6.5
	
	 Transfer newTransfer = new Transfer("O1001", "A1001",  "A1001", "25.00");
	 Assert.assertEquals("Transfer to and from accounts must be different", Transfer.validate(newTransfer.getOwnerId(), newTransfer.getToAccountId(), newTransfer.getFromAccountId(), "P$1111", newTransfer.getTransferAmount()));
	 newTransfer = new Transfer("O1001", "A1001",  "A1002", "25.00");
	 Assert.assertEquals("valid", Transfer.validate(newTransfer.getOwnerId(), newTransfer.getToAccountId(), newTransfer.getFromAccountId(), "P$1111", newTransfer.getTransferAmount()));
 }

 
 @Test
 public void fromAccountDoesNotExist() {
	//Testing transfer from a non-existent account will be denied. UAT 6.6
	
	 Transfer newTransfer = new Transfer("O1001", "A1011",  "A1002", "25.00");
	 Assert.assertEquals("Transfer from account does not exist", Transfer.validate(newTransfer.getOwnerId(), newTransfer.getToAccountId(), newTransfer.getFromAccountId(), "P$1111", newTransfer.getTransferAmount()));
	 newTransfer = new Transfer("O1001", "A1001",  "A1002", "25.00");
	 Assert.assertEquals("valid", Transfer.validate(newTransfer.getOwnerId(), newTransfer.getToAccountId(), newTransfer.getFromAccountId(), "P$1111", newTransfer.getTransferAmount()));
 }
 
 
 @Test
 public void TransferNotNegative() { 
	 //Testing transfer amounts do not contain a negative amount. UAT 6.7
	 //ASK CUSTOMER FOR TYPO CLARIFICATION ON MESSAGE SPECIFIED IN UAT
	 Transfer transfer = new Transfer("O1001", "A1001",  "A1002", "-25.00");
	 Assert.assertEquals("Transfer amount cannot negative", transfer.validateTransferAmount(transfer.getTransferAmount()));
 }
 
 
 @Test 
 public void testTransferAmountMustBeDollarsAndCents () {
	//Testing transfers do not contain more than two decimal places. UAT 6.8
	 Transfer transfer1 = new Transfer("O1001", "A1001",  "A1002", "25.1234");
	 Assert.assertEquals("Transfer amount must be dollars and cents", transfer1.validateTransferAmount(transfer1.getTransferAmount()));
	 transfer1 = new Transfer("O1001", "A1001",  "A1002", "25.12");
	 Assert.assertEquals("valid", transfer1.validateTransferAmount(transfer1.getTransferAmount())); 
}
 
 
 @Test 
 public void testTransferAmountIsNumeric() {
	//Testing transfers do not contain any char or string data. UAT 6.9
	 Transfer transfer1 = new Transfer("O1001", "A1001",  "A1002", "25w");
	 Assert.assertEquals("Transfer amount must be numeric",  transfer1.validateTransferAmount(transfer1.getTransferAmount())); 
	 transfer1 = new Transfer ("O1001", "A1001",  "A1002", "25.00");
	 Assert.assertEquals("valid", transfer1.validateTransferAmount(transfer1.getTransferAmount())); 
}

 
}//End TransferTest