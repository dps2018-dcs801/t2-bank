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

public class TransferCreateView extends JPanel {
	/**
	 * 
	 */
	Database database = Database.getInstance();

	private static final long serialVersionUID = 1L;
	private JTextField passwordField;
	private JTextField ownerIdTextField;
	private JTextField transferIdTextField;
	private JLabel errorMessage;
	private JTextField fromAccountId;
	private JTextField transferFromTextField;
	private JTextField toAccountId;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				JFrame jFrame = new JFrame();
				TransferCreateView newContentPane = new TransferCreateView();
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
	public TransferCreateView() {
		addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
			}
		});

		Database.getInstance().load();

		setLayout(null);

		JLabel lblReportId = new JLabel("Transfer ID");
		lblReportId.setName("transactionIdLabel");
		lblReportId.setBounds(18, 59, 113, 24);
		add(lblReportId);
		lblReportId.setFont(new Font("Arial", Font.BOLD, 20));

		transferIdTextField = new JTextField();
		transferIdTextField.setText("12345");
		transferIdTextField.setBounds(278, 59, 63, 29);
		add(transferIdTextField);
		transferIdTextField.setVisible(true);
		transferIdTextField.setEditable(false);
		transferIdTextField.setName("transactionIdField");
		transferIdTextField.setColumns(5);
		lblReportId.setLabelFor(transferIdTextField);
		transferIdTextField.setText(Transfer.getNextId());

		JLabel fromName = new JLabel("Transfer From Account ID");
		fromName.setBounds(18, 178, 249, 24);
		add(fromName);
		fromName.setFont(new Font("Arial", Font.BOLD, 20));

		ownerIdTextField = new JTextField();
		ownerIdTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		ownerIdTextField.setBounds(280, 96, 61, 29);
		add(ownerIdTextField);
		ownerIdTextField.setName("ownerName");
		ownerIdTextField.setColumns(5);
		fromName.setLabelFor(ownerIdTextField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(361, 96, 94, 24);
		add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));

		passwordField = new JTextField();
		passwordField.setBounds(467, 96, 63, 29);
		add(passwordField);
		passwordField.setName("password");
		lblPassword.setLabelFor(passwordField);

		JLabel lblNewLabel = new JLabel("Transfer");
		lblNewLabel.setBounds(219, 6, 136, 36);
		add(lblNewLabel);

		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));

		JButton btnSave = new JButton("Submit");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorMessage.setText("");
				String ownerId = ownerIdTextField.getText();
				String fromAccountIdString = fromAccountId.getText();
				String toAccountIdString = toAccountId.getText();
				String password = passwordField.getText();
				String transferAmount = transferFromTextField.getText();

				Transfer transfer = new Transfer(ownerId, fromAccountIdString,
						toAccountIdString, transferAmount);
				String result = transfer.transfer(password);
				if (result.equals("valid")) {
					transfer.put(); //added by MSidaras because it was missing by RDiGiorgio
					transferIdTextField.setText(Transfer.getNextId());
				} else {
					errorMessage.setText(result);
				}

			}
		});
		btnSave.setBounds(86, 279, 88, 29);
		add(btnSave);
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getErrorMessage().setText("");
				ownerIdTextField.setText("");
				passwordField.setText("");
				fromAccountId.setText("");
				toAccountId.setText("");
				transferFromTextField.setText("");

			}
		});
		btnClear.setBounds(393, 279, 76, 29);
		add(btnClear);
		btnClear.setHorizontalAlignment(SwingConstants.LEFT);
		btnClear.setAlignmentX(Component.RIGHT_ALIGNMENT);

		errorMessage = new JLabel("");
		errorMessage.setBounds(18, 250, 512, 16);
		add(errorMessage);

		JLabel label = new JLabel("Account Owner ID");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(18, 102, 191, 24);
		add(label);

		JLabel lblTransferAmount = new JLabel("Transfer From Amount");
		lblTransferAmount.setName("transferAmountLabel");
		lblTransferAmount.setFont(new Font("Arial", Font.BOLD, 20));
		lblTransferAmount.setBounds(18, 214, 236, 24);
		add(lblTransferAmount);

		fromAccountId = new JTextField();
		fromAccountId.setName("fromAccountIdField");
		fromAccountId.setBounds(278, 178, 54, 28);
		add(fromAccountId);
		fromAccountId.setColumns(10);

		transferFromTextField = new JTextField();
		transferFromTextField.setColumns(10);
		transferFromTextField.setBounds(278, 214, 123, 28);
		add(transferFromTextField);

		JLabel toName = new JLabel("Transfer To Account ID");
		toName.setFont(new Font("Arial", Font.BOLD, 20));
		toName.setBounds(18, 142, 240, 24);
		add(toName);

		toAccountId = new JTextField();
		toAccountId.setName("toAccountIdField");
		toAccountId.setColumns(10);
		toAccountId.setBounds(278, 137, 54, 28);
		add(toAccountId);

	}

	public JTextField getOwnerIdTextfield() {
		return transferIdTextField;
	}

	public JTextField getNameTextField() {
		return ownerIdTextField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public JLabel getErrorMessage() {
		return errorMessage;
	}

	public JTextField getToAccountId() {
		return toAccountId;
	}

	public JTextField getFromAccountId() {
		return fromAccountId;
	}

	public JTextField getTransferFromTextField() {
		return transferFromTextField;
	}
}
