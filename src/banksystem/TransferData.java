package banksystem;

import java.io.Serializable;

public class TransferData implements Serializable {
	public String ownerId;
	public String fromAccountId;
	public String toAccountId;
	public String transferAmount;
}