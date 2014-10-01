package uat;

import database.Database;
import banksystem.AccountData;
import banksystem.Withdrawal;


public class WithdrawalWithStatus extends Withdrawal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String status;
	public String accountType = "Checking";

	public WithdrawalWithStatus(String id, String accountType, String balance) {
		super(id,accountType,balance);
	}

	public WithdrawalWithStatus() {
	}

	public WithdrawalWithStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAccountBalance(String givenAccountId) {
		AccountData accountData = (AccountData) Database.get(givenAccountId);
		if ( accountData == null ) {
			return ( "SEVERE: Unabel to retrieve " + getAccountId() );
		}
		System.out.println ( "Account data:" + accountData );
		return (accountData.balance);
	}
}
