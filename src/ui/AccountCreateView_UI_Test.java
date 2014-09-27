package ui;

import static org.junit.Assert.assertNotNull;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;

import banksystem.AccountCreateView;
import banksystem.AccountOwner;
import database.Database;

public class AccountCreateView_UI_Test {

	private JFrame jFrame;

	Database database = Database.getInstance();

	@Before
	public void setUp() throws Exception {

		jFrame = new JFrame();
		AccountCreateView newContentPane = new AccountCreateView();
		newContentPane.setPreferredSize(new Dimension(600, 400));
		newContentPane.setOpaque(true);
		jFrame.setContentPane(newContentPane);
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);

		database.setFileName("test.dat");
		database.eraseFile();
		database.load();
	}

	//@Test
	public void testCheckTextFields() {
		String textFields[] = { "passwordTextField", "ownerIdTextField",
				"accountTypeTextField", "openingBalanceTextField" };

		jFrame.setVisible(true);
		for (String textField : textFields) {
			JTextField jTextField = JTextFieldOperator.findJTextField(jFrame,
					new NameComponentChooser(textField));

			assertNotNull("Missing:" + textField, jTextField);
		}

	}

	//@Test
	public void testCheckLabels() {
		String labels[] = { "accountIdLabel", "errorMessageLabel" };

		jFrame.setVisible(true);
		for (String label : labels) {
			JLabel jLabel = JLabelOperator.findJLabel(jFrame,
					new NameComponentChooser(label));
			assertNotNull("Missing:" + label, jLabel);

		}

	}

	//@Test
	public void testVerifyStartingId() {

		jFrame.setVisible(true);

		JLabel accountIdLabel = JLabelOperator.findJLabel(jFrame,
				new NameComponentChooser("accountIdLabel"));
		System.out.println(accountIdLabel.getText());
		Assert.assertEquals("A1001", accountIdLabel.getText());
	}

	/*
	 * Given that Account Owner ID O1002 exists and the Password is P$2222 and
	 * we are creating a new checking account When we enter Account Owner O1002,
	 * Password P$2223, Account Type Checking and Balance 50.00 Then we should
	 * get an error message = Invalid Password displayed in the GUI When we
	 * enter Account Owner O1002, Password P$2222, Account Type Checking and
	 * Balance 50.00 Then there should be nothing displayed in the error field
	 * in the GUI
	 */
	@Test
	public void testValidThenInvalidAccount() {

		
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
		Assert.assertEquals("Invalid Password", errorMessageLabel.getText());

		try {
			Thread.sleep(1 * 1000);
		} catch (Exception e) {
		}

		JTextField passwordTextField = JTextFieldOperator.findJTextField(
				jFrame, new NameComponentChooser("passwordTextField"));
		assertNotNull("Missing:", passwordTextField);

		passwordTextField.setText("P$2222");
		submitButton.doClick();
		try {
			Thread.sleep(1 * 1000);
		} catch (Exception e) {
		}
		JLabel accountIdLabel = JLabelOperator.findJLabel(jFrame,
				new NameComponentChooser("accountIdLabel"));
		Assert.assertEquals("A1002", accountIdLabel.getText());
		
		
		Assert.assertEquals("", errorMessageLabel.getText());
	}
}
