package uat;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.concordion.integration.junit3.ConcordionTestCase;
import org.junit.Assert;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

import banksystem.AccountCreateView;
import banksystem.Account;
import banksystem.AccountOwner;
import banksystem.PasswordManager;
import database.Database;

public class CreateAccountUATVerboseTest extends ConcordionTestCase {

	Database database = Database.getInstance();

	public void setUp() throws Exception {
		System.out.println("Setup");
	}

	public AccountWithStatus addAccountToOwner(String presentedPassword,
			String expectedPassword, String accountType, String balance,
			String givenOwnerId, String givenAccountId) {

		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();

		database.setIndex(givenOwnerId);
		AccountOwner owner = new AccountOwner("firstName lastName",
				expectedPassword);
		owner.put();

		database.setIndex(givenAccountId);
		String result = PasswordManager.authenticate(presentedPassword,
				expectedPassword);
		if (!result.equals("valid")) {
			AccountWithStatus account = new AccountWithStatus();
			account.status = result;
			return (account);
		}

		AccountWithStatus account = new AccountWithStatus(owner.getId(), accountType, balance);
		account.put();

		return (account);
	}

	public String accountDoesNotExist(String ownerid) {

		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();

		return ( AccountOwner.validateOwnerId(ownerid) );

	}

	public String accountBalanceValidation(String balance) {
		return (AccountWithStatus.validateBalance(balance));
	}

	public String accountType(String accountType) {
		return (AccountWithStatus.validateAccountType(accountType));
	}

	public List<String> validThenInvalidAccount() {
		List<String> list = new ArrayList<String>();
		JFrame jFrame = new JFrame();
		AccountCreateView newContentPane = new AccountCreateView();
		newContentPane.setPreferredSize(new Dimension(600, 400));
		newContentPane.setOpaque(true);
		jFrame.setContentPane(newContentPane);
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);

		Database.setFileName("test.dat");
		database.eraseFile();
		database.load();

		AccountOwner accountOwner = new AccountOwner("Rinaldo", "P$2222");
		accountOwner.put();
		String validValues[] = { "passwordTextField:P$2223",
				"ownerIdTextField:O1001", "accountTypeTextField:Checking",
				"openingBalanceTextField:50.00" };

		jFrame.setVisible(true);
		for (String textField : validValues) {

			String fields[] = textField.split(":");

			JTextField jTextField = JTextFieldOperator.findJTextField(jFrame,
					new NameComponentChooser(fields[0]));
			assertNotNull("Missing:" + textField, jTextField);

			jTextField.setText(fields[1]);

		}

		JButton submitButton = JButtonOperator.findJButton(jFrame,
				new NameComponentChooser("submitButton"));
		assertNotNull(submitButton);

		submitButton.doClick();
		JLabel errorMessageLabel = JLabelOperator.findJLabel(jFrame,
				new NameComponentChooser("errorMessageLabel"));
		list.add(errorMessageLabel.getText());

		JTextField passwordTextField = JTextFieldOperator.findJTextField(
				jFrame, new NameComponentChooser("passwordTextField"));
		assertNotNull("Missing:", passwordTextField);

		passwordTextField.setText("P$2222");
		submitButton.doClick();

		JLabel accountIdLabel = JLabelOperator.findJLabel(jFrame,
				new NameComponentChooser("accountIdLabel"));
		Assert.assertEquals("A1002", accountIdLabel.getText());

		list.add(errorMessageLabel.getText());
		System.out.println(list);
		return (list);
	}

}
