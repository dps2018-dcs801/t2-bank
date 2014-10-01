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

public class WithdrawalCreateView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Database database = Database.getInstance();

	private JTextField accountOwnerIdTextField;
	private JLabel withdrawalIdTextField;
	private JLabel errorMessageLabel;
	private JTextField accountIdTextField;
	private JTextField withdrawalAmountTextField;
	private JTextField passwordTextField;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				JFrame jFrame = new JFrame();
				WithdrawalCreateView newContentPane = new WithdrawalCreateView();
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

	/**
	 * Create the panel.
	 */
	public WithdrawalCreateView() {
		database.load();
		Database.dump();
		addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
			}
		});
		Database.getInstance().load();
		setLayout(null);

		JLabel labelForWithdrawlId = new JLabel("Withdrawal ID");
		labelForWithdrawlId.setName("transactionIdLabel");
		labelForWithdrawlId.setBounds(18, 60, 174, 24);
		add(labelForWithdrawlId);
		labelForWithdrawlId.setFont(new Font("Arial", Font.BOLD, 20));

		withdrawalIdTextField = new JLabel();
		withdrawalIdTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		withdrawalIdTextField.setText("12345");
		withdrawalIdTextField.setBounds(215, 58, 63, 29);
		add(withdrawalIdTextField);
		withdrawalIdTextField.setName("labelForWithdrawlId");
		labelForWithdrawlId.setLabelFor(withdrawalIdTextField);
		withdrawalIdTextField.setText(Withdrawal.getNextId());

		JLabel labelForAccountId = new JLabel("Account ID");
		labelForAccountId.setBounds(18, 137, 146, 24);
		add(labelForAccountId);
		labelForAccountId.setFont(new Font("Arial", Font.BOLD, 20));

		accountOwnerIdTextField = new JTextField();
		accountOwnerIdTextField.setText("");
		accountOwnerIdTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		accountOwnerIdTextField.setBounds(215, 99, 61, 29);
		add(accountOwnerIdTextField);
		accountOwnerIdTextField.setName("ownerName");
		accountOwnerIdTextField.setColumns(5);
		labelForAccountId.setLabelFor(accountOwnerIdTextField);

		JLabel titleLabel = new JLabel("Withdrawal");
		titleLabel.setBounds(161, 12, 185, 36);
		add(titleLabel);

		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorMessageLabel.setText("");
				String ownerId = accountOwnerIdTextField.getText();
				String accountId = accountIdTextField.getText();
				String password = passwordTextField.getText();
				String withdrawalAmount = withdrawalAmountTextField.getText();

				try {
					Withdrawal withdrawal = new Withdrawal(ownerId, accountId, withdrawalAmount);
					String result = withdrawal.updateBalance (password);
					if (result.equals("valid")) {
						withdrawal.put();
						withdrawalIdTextField.setText(Withdrawal.getNextId());
					} else {
						errorMessageLabel.setText(result);
						errorMessageLabel.setVisible(true);
					}
				} catch (Exception e) {
					errorMessageLabel.setText("Withdrawal amount is beyond the system limit  " + e.getMessage());
				}
			}
		});
		submitButton.setBounds(86, 250, 88, 29);
		add(submitButton);
		submitButton.setHorizontalAlignment(SwingConstants.LEFT);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getErrorMessage().setText("");
				accountIdTextField.setText("");
				withdrawalAmountTextField.setText("");
				passwordTextField.setText("");
				accountOwnerIdTextField.setText("");
			}
		});
		clearButton.setBounds(393, 250, 76, 29);
		add(clearButton);
		clearButton.setHorizontalAlignment(SwingConstants.LEFT);
		clearButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		errorMessageLabel = new JLabel("");
		errorMessageLabel.setBounds(18, 222, 408, 16);
		add(errorMessageLabel);

		JLabel labelForAccountOwnerId = new JLabel("Account Owner ID");
		labelForAccountOwnerId.setName("errorMessageLabel");
		labelForAccountOwnerId.setFont(new Font("Arial", Font.BOLD, 20));
		labelForAccountOwnerId.setBounds(18, 102, 191, 24);
		add(labelForAccountOwnerId);

		JLabel labelForWithdrawalAmount = new JLabel("Withdrawal Amount");
		labelForWithdrawalAmount.setName("withdrawalAmountLabel");
		labelForWithdrawalAmount.setFont(new Font("Arial", Font.BOLD, 20));
		labelForWithdrawalAmount.setBounds(18, 187, 191, 24);
		add(labelForWithdrawalAmount);

		accountIdTextField = new JTextField();
		accountIdTextField.setName("labelForWithdrawalAmount");
		accountIdTextField.setBounds(215, 137, 63, 28);
		add(accountIdTextField);
		accountIdTextField.setColumns(10);

		withdrawalAmountTextField = new JTextField();
		withdrawalAmountTextField.setName("accountIdTextField");
		withdrawalAmountTextField.setColumns(10);
		withdrawalAmountTextField.setBounds(215, 182, 88, 28);
		add(withdrawalAmountTextField);

		passwordTextField = new JTextField();
		passwordTextField.setName("withdrawalAmountTextField");
		passwordTextField.setBounds(393, 99, 63, 28);
		add(passwordTextField);
		passwordTextField.setColumns(10);

		JLabel labelForPassword = new JLabel("Password");
		labelForPassword.setName("passwordTextField");
		labelForPassword.setFont(new Font("Arial", Font.BOLD, 20));
		labelForPassword.setBounds(290, 96, 104, 24);
		add(labelForPassword);

	}

	public JTextField getAccountOwnerIdTextField() {
		return accountOwnerIdTextField;
	}

	public void setAccountOwnerIdTextField(JTextField accountOwnerIdTextField) {
		this.accountOwnerIdTextField = accountOwnerIdTextField;
	}

	public JLabel getWithdrawalIdTextField() {
		return withdrawalIdTextField;
	}

	public void setWithdrawalIdTextField(JLabel withdrawalIdTextField) {
		this.withdrawalIdTextField = withdrawalIdTextField;
	}

	public JLabel getErrorMessageLabel() {
		return errorMessageLabel;
	}

	public void setErrorMessageLabel(JLabel errorMessageLabel) {
		this.errorMessageLabel = errorMessageLabel;
	}

	public JTextField getAccountIdTextField() {
		return accountIdTextField;
	}

	public void setAccountIdTextField(JTextField accountIdTextField) {
		this.accountIdTextField = accountIdTextField;
	}

	public JTextField getWithdrawalAmountTextField() {
		return withdrawalAmountTextField;
	}

	public void setWithdrawalAmountTextField(JTextField withdrawalAmountTextField) {
		this.withdrawalAmountTextField = withdrawalAmountTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(JTextField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	public JTextField getNameTextField() {
		return accountOwnerIdTextField;
	}

	public JLabel getErrorMessage() {
		return errorMessageLabel;
	}
}
