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

public class DepositCreateView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Database database = Database.getInstance();

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				JFrame jFrame = new JFrame();
				DepositCreateView newContentPane = new DepositCreateView();
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


	private JTextField passwordLabel;
	private JTextField ownerIdTextField;
	private JLabel depositIdLabel;
	private JLabel errorMessageLabel;

	private JTextField depositAmountTextField;
	private JTextField accountIdTextField;

	/**
	 * Create the panel.
	 */
	public DepositCreateView() {
		addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
			}
		});

		setLayout(null);

		JLabel labelForDepositId = new JLabel("Deposit ID");
		labelForDepositId.setName("depositIdLabel");
		labelForDepositId.setBounds(18, 60, 174, 24);
		add(labelForDepositId);
		labelForDepositId.setFont(new Font("Arial", Font.BOLD, 20));

		depositIdLabel = new JLabel();
		
		depositIdLabel.setText(Deposit.getNextId());
		depositIdLabel.setBounds(222, 60, 63, 29);
		add(depositIdLabel);
		depositIdLabel.setName("accountIdField");
		labelForDepositId.setLabelFor(depositIdLabel);

		ownerIdTextField = new JTextField();
		ownerIdTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		ownerIdTextField.setBounds(222, 101, 61, 29);
		add(ownerIdTextField);
		ownerIdTextField.setName("ownerName");
		ownerIdTextField.setColumns(5);

		JLabel passwordLabelForField = new JLabel("Password");
		passwordLabelForField.setBounds(295, 101, 94, 24);
		add(passwordLabelForField);
		passwordLabelForField.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabelForField.setFont(new Font("Arial", Font.BOLD, 20));

		passwordLabel = new JTextField();
		passwordLabel.setBounds(392, 101, 63, 29);
		add(passwordLabel);
		passwordLabel.setName("password");
		passwordLabelForField.setLabelFor(passwordLabel);

		JLabel titleLabel = new JLabel("Deposit");
		titleLabel.setBounds(162, 6, 123, 36);
		add(titleLabel);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

		JButton saveButton = new JButton("Submit");
		saveButton.setName("submitButton");

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorMessageLabel.setText("");
				String ownerId = ownerIdTextField.getText();
				String accountId = accountIdTextField.getText();
				String password = passwordLabel.getText();
				String depositAmount = depositAmountTextField.getText();
				try {
					Deposit deposit = new Deposit(ownerId,accountId, depositAmount);
					String result = deposit.updateBalance (password);
					if (result.equals("valid")) {
						depositIdLabel.setText(Deposit.getNextId());
					} else {
						errorMessageLabel.setText(result);
					}
				} catch (Exception e) {
					errorMessageLabel.setText("Fail:" + e.getMessage());
				}
			}

		});
		saveButton.setBounds(18, 250, 88, 29);
		add(saveButton);
		saveButton.setHorizontalAlignment(SwingConstants.LEFT);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getErrorMessage().setText("");
				ownerIdTextField.setText("");
				passwordLabel.setText("");
				accountIdTextField.setText("");
				depositAmountTextField.setText("");
			}
		});
		clearButton.setBounds(435, 250, 76, 29);
		add(clearButton);
		clearButton.setHorizontalAlignment(SwingConstants.LEFT);
		clearButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		errorMessageLabel = new JLabel("");
		errorMessageLabel.setBounds(18, 222, 437, 16);
		add(errorMessageLabel);

		JLabel accountOwnerIdLabelFor = new JLabel("Account Owner ID");
		accountOwnerIdLabelFor.setFont(new Font("Arial", Font.BOLD, 20));
		accountOwnerIdLabelFor.setBounds(18, 102, 191, 24);
		add(accountOwnerIdLabelFor);

		JLabel depositAmountLabelFor = new JLabel("Deposit Amount");
		depositAmountLabelFor.setFont(new Font("Arial", Font.BOLD, 20));
		depositAmountLabelFor.setBounds(18, 174, 191, 24);
		add(depositAmountLabelFor);

		depositAmountTextField = new JTextField();
		depositAmountTextField.setColumns(10);
		depositAmountTextField.setBounds(222, 174, 123, 28);
		add(depositAmountTextField);

		JLabel accountIdLabelFor = new JLabel("Account ID");
		accountIdLabelFor.setName("depositIdLabel");
		accountIdLabelFor.setFont(new Font("Arial", Font.BOLD, 20));
		accountIdLabelFor.setBounds(18, 138, 174, 24);
		add(accountIdLabelFor);

		accountIdTextField = new JTextField();
		accountIdTextField.setName("ownerName");
		accountIdTextField.setColumns(5);
		accountIdTextField.setBounds(224, 137, 61, 29);
		add(accountIdTextField);

	}

	public JLabel getErrorMessage() {
		return errorMessageLabel;
	}

	public JTextField getNameTextField() {
		return ownerIdTextField;
	}

	public JLabel getOwnerIdTextfield() {
		return depositIdLabel;
	}

	public JTextField getPasswordField() {
		return passwordLabel;
	}
}
