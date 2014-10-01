package banksystem;

import java.io.Serializable;
import java.util.Date;

public class TransactionData  implements Serializable {

	private static final long serialVersionUID = 1L;

	public Date transDate;
	public String transactionType;
	public String transactionId;
	public String transactionAmount;
	public String accountOwnerId;
	public String accountId;
}
