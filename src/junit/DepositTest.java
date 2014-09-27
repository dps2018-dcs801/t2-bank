package junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksystem.Account;
import banksystem.Deposit;
import banksystem.DepositData;
import banksystem.Utilities;
import database.Database;

public class DepositTest  {

 Database database = Database.getInstance();

 @Before
 public void setUp() throws Exception {
  Database.setFileName("test.dat");
  database.load();
 }

 @After
 public void tearDown() throws Exception {
 }

 /* @Test
 public void testDepositId() {
     Deposit deposit1 = new Deposit();
  // Testing  the correct starting deposit id and successive deposit ids 
   Assert.assertEquals("D1001",deposit1.getNextId());
   deposit1.put();
   Assert.assertEquals("D1002",deposit1.getNextId()); 
   
} */
 
 @Test // Testing entered account owner id matches existing account owner id
 public void ExistingOwnerId() {
  Assert.assertEquals("valid", Deposit.validate("O1001","accountid","password","depositamount"));
  Assert.assertEquals("valid", Deposit.validate("O1002","accountid","password","depositamount"));
 }

 /*
 @Test // Testing entered account id matches existing account id    
 public void ExistingAccountId() {
  Assert.assertEquals("valid", Deposit.validate("A1001","accountid","password","depositamount"));
  Assert.assertEquals("Invalid Account Owner ID", Deposit.validate("A1002","accountid","password","depositamount"));
  
 }
 @Test  // Testing if an account exist in the database already
 //public void accountDoesNotExistMessage() {
 // Assert.assertEquals("Account should not exist", "Invalid Account ID", Account.validateAccountExists("A1001"));
 //}
 public void accountDoesNotExistMessage() {
  Assert.assertEquals("valid", Deposit.validate("A1001","accountid","password","depositamount"));
  Assert.assertEquals("valid", Account.validateAccountExists("A1001")); // green
  Assert.assertEquals("Invalid Account ID", Account.validateAccountExists("A1002")); // green
 // Assert.assertEquals("valid", Account.validateAccountExists("A1002")); //              red
 // Assert.assertEquals("Invalid Account ID", Account.validateAccountExists("A1001")); // red
  //}
 }
 @Test  // Testing if an entered deposit amount is negative
  public void depositIsNegative() {
  Assert.assertEquals("valid", Deposit.validate("A1001","accountid","password","depositamount"));
 // Deposit deposit1 = new Deposit("A1001","accountid","password","depositamount"); 
 // Assert.assertEquals("Deposit cannot be negative", Deposit.isNegative("10.00")); // red
  Assert.assertEquals("Deposit cannot be negative", Deposit.isNegative("-10.00")); // green
 
 }
 @Test  // Testing if an entered deposit amount is equal to zero
 public void depositIsNotZero() {
 Assert.assertEquals("valid", Deposit.validate("A1001","accountid","password","depositamount"));
 // Deposit deposit1 = new Deposit("A1001","accountid","password","depositamount"); 
    // Assert.assertEquals("Deposit cannot be zero", Deposit.isZero("0.00"); // red
     Assert.assertEquals("valid", Deposit.isZero("10.00")); // green

}
 @Test  // Testing if an entered deposit amount'd decimal point does not exceed two positions
 public void depositHasTwoDecimals() {
 Assert.assertEquals("valid", Deposit.validate("A1001","accountid","password","depositamount"));
 // Deposit deposit1 = new Deposit("A1001","accountid","password","depositamount"); 
    // Assert.assertEquals("Deposit cannot be zero", Deposit.isZero("0.00"); // red
     Assert.assertEquals("valid", Utilities.isMoney("10.00")); // green

}
 
 @Test  // Testing if amount is valid (numeric, not empty, not blanks, not negative)
 public void depositAmountIsValid() {
 Assert.assertEquals("valid", Deposit.validate("A1001","accountid","password","depositamount"));
 // Assert.assertEquals("valid", Deposit.validateDepositAmount("-10.00")); // red - OK
 // Assert.assertEquals("valid", Deposit.validateDepositAmount("   ")); // red - OK
 // Assert.assertEquals("valid", Deposit.validateDepositAmount("")); //  red -OK
    Assert.assertEquals("valid", Deposit.validateDepositAmount("10 10")); //  red -OK
 // Assert.assertEquals("valid", Deposit.validateDepositAmount("10000")); // green ?
 // Assert.assertEquals("valid", Deposit.validateDepositAmount("AAA")); // red non-numeric
 // Assert.assertEquals("valid", Deposit.validateDepositAmount("10.00")); // green - good
}*/
}