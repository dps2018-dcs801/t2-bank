package banksystem;

import java.io.Serializable;

public class TransactionData  implements Serializable {

	private static final long serialVersionUID = 1L;

	public String transactionDate;
	public String transactionType;
	public String transactionId;
	public String transactionAmount;
	public String accountOwnerId;
	public String accountId;

}
