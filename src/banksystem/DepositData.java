package banksystem;

import java.io.Serializable;

public class DepositData implements Serializable {

	public String ownerId;
	public String accountId;
	public String depositAmount;
	@Override
	public String toString() {
		return "DepositData [ownerId=" + ownerId + ", accountId=" + accountId + ", depositAmount=" + depositAmount
				+ "]";
	}

}