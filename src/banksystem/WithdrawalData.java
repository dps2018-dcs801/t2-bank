package banksystem;

import java.io.Serializable;

public class WithdrawalData implements Serializable {
	// This was generated with a toString() from the Source menu in Eclipse

	@Override
	public String toString() {
		return "DepositData [ownerId=" + ownerId + ", accountId=" + accountId + ", withdrawalAmount=" + withdrawalAmount
				+ "]";
	}
	private static final long serialVersionUID = 1L;

	public String ownerId;
	public String accountId;
	public String withdrawalAmount;
}