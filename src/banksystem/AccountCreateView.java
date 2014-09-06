package banksystem;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.Database;

public class AccountCreateView extends JPanel {

	
	private static final long serialVersionUID = 1L;
	Database database = Database.getInstance();

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				JFrame jFrame = new JFrame();
				AccountCreateView newContentPane = new AccountCreateView();
				newContentPane.setPreferredSize(new Dimension(600, 400));
				newContentPane.setOpaque(true);
				jFrame.setContentPane(newContentPane);
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jFrame.pack();
				jFrame.setVisible(true);
				jFrame.setLocationRelativeTo(null);
			}
		});
	}


	private JTextField passwordTextField;
	private JTextField ownerIdTextField;
	private JLabel accountIdLabel;
	private JLabel errorMessageLabel;
	private JTextField accountTypeTextField;

	private JTextField openingBalanceTextField;

	/**
	 * Create the panel.
	 */
	public AccountCreateView() {
		database.load();

		addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
			}
		});

		setLayout(null);

		JLabel labelForAccountId = new JLabel("Account ID");
		labelForAccountId.setName("labelForAccountId");
		labelForAccountId.setBounds(18, 60, 174, 24);
		add(labelForAccountId);
		labelForAccountId.setFont(new Font("Arial", Font.BOLD, 20));

		accountIdLabel = new JLabel();
		accountIdLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		accountIdLabel.setText("12345");
		accountIdLabel.setBounds(204, 60, 61, 29);
		add(accountIdLabel);
		accountIdLabel.setName("accountIdLabel");
		labelForAccountId.setLabelFor(accountIdLabel);
		accountIdLabel.setText(Account.getNextId());

		JLabel accountTypeLabel = new JLabel("Account Type");
		accountTypeLabel.setBounds(18, 151, 191, 24);
		add(accountTypeLabel);
		accountTypeLabel.setFont(new Font("Arial", Font.BOLD, 20));

		ownerIdTextField = new JTextField();
		ownerIdTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		ownerIdTextField.setBounds(204, 96, 61, 29);
		add(ownerIdTextField);
		ownerIdTextField.setName("ownerIdTextField");
		ownerIdTextField.setColumns(5);
		accountTypeLabel.setLabelFor(ownerIdTextField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(277, 96, 94, 24);
		add(passwordLabel);
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));

		passwordTextField = new JTextField();
		passwordTextField.setBounds(381, 96, 63, 29);
		add(passwordTextField);
		passwordTextField.setName("passwordTextField");
		passwordLabel.setLabelFor(passwordTextField);

		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setBounds(87, 6, 324, 36);
		add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));

		JButton submitButton = new JButton("Submit");
		submitButton.setName("submitButton");

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorMessageLabel.setText("");
				errorMessageLabel.setVisible(true);
				Account account = new Account(ownerIdTextField.getText(),
						accountTypeTextField.getText(),
						openingBalanceTextField.getText());
				String result = account.validate();
				Database.dump(result);
				if (result.equals("valid")) {
					result = AccountOwner.authenticate(passwordTextField.getText(), ownerIdTextField.getText());
					if (result.equals("valid")) {
						account.put();
						accountIdLabel.setText(Account.getNextId());
					} else {
					
						errorMessageLabel.setText(result);
						errorMessageLabel.setVisible(true);
					}
				} else {
					errorMessageLabel.setText(result);
					errorMessageLabel.setVisible(true);
				}

			}
		});
		submitButton.setBounds(18, 250, 88, 29);
		add(submitButton);
		submitButton.setHorizontalAlignment(SwingConstants.LEFT);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getErrorMessage().setText("");
				ownerIdTextField.setText("");
				passwordTextField.setText("");
				openingBalanceTextField.setText("");
				accountTypeTextField.setText("");

			}
		});
		clearButton.setBounds(368, 250, 76, 29);
		add(clearButton);
		clearButton.setHorizontalAlignment(SwingConstants.LEFT);
		clearButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		errorMessageLabel = new JLabel("");
		errorMessageLabel.setBounds(18, 222, 413, 16);
		errorMessageLabel.setName("errorMessageLabel");
		add(errorMessageLabel);

		JLabel accountOwnerIdLabel = new JLabel("Account Owner ID");
		accountOwnerIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
		accountOwnerIdLabel.setBounds(18, 102, 191, 24);
		add(accountOwnerIdLabel);

		JLabel openingBalanceLabel = new JLabel("Opening Balance");
		openingBalanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
		openingBalanceLabel.setBounds(18, 187, 191, 24);
		add(openingBalanceLabel);

		accountTypeTextField = new JTextField();
		accountTypeTextField.setBounds(202, 151, 123, 28);
		add(accountTypeTextField);
		accountTypeTextField.setColumns(10);
		accountTypeTextField.setName("accountTypeTextField");

		openingBalanceTextField = new JTextField();
		openingBalanceTextField.setColumns(10);
		openingBalanceTextField.setBounds(202, 187, 123, 28);
		openingBalanceTextField.setName("openingBalanceTextField");
		add(openingBalanceTextField);

	}

	public JLabel getErrorMessage() {
		return errorMessageLabel;
	}

	public JTextField getNameTextField() {
		return ownerIdTextField;
	}

	public JLabel getOwnerIdTextfield() {
		return accountIdLabel;
	}

	public JTextField getPasswordField() {
		return passwordTextField;
	}

	public JTextField getAccountTypeTextfield() {
		return accountTypeTextField;
	}
}
