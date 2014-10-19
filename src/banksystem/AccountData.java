package banksystem;

import java.io.Serializable;

public class AccountData implements Serializable {

	private static final long serialVersionUID = 1L;

	public String accountType;
	public String balance;
	public String ownerId;

	@Override
	public String toString() {
		return "AccountData [accountType=" + accountType + ", balance=" + balance + ", ownerId=" + ownerId + "]";
	}
}