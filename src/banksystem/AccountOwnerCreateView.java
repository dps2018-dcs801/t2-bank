package banksystem;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.Database;


public class AccountOwnerCreateView extends JPanel {

	private static final long serialVersionUID = 1L;

	Database database = Database.getInstance();

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				JFrame jFrame = new JFrame();
				AccountOwnerCreateView newContentPane = new AccountOwnerCreateView();
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
	private JLabel ownerIdLabel;
	private JLabel errorMessageLabel;
	private JTextField accountOwnerNameTextField;

	/**
	 * Create the panel.
	 */
	public AccountOwnerCreateView() {
		setMinimumSize(new Dimension(600, 400));
		addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
			}
		});
		Database.getInstance().load();
		setLayout(null);

		ownerIdLabel = new JLabel();
		ownerIdLabel.setBounds(211, 55, 63, 29);
		ownerIdLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));

		ownerIdLabel.setText(AccountOwner.getNextId());
		add(ownerIdLabel);
		ownerIdLabel.setName("ownerIdLabel");

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(16, 127, 94, 24);
		add(passwordLabel);
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));

		passwordTextField = new JTextField();
		passwordTextField.setMinimumSize(new Dimension(600, 400));
		passwordTextField.setText("");
		passwordTextField.setColumns(5);
		passwordTextField.setBounds(211, 122, 59, 29);
		passwordTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = passwordTextField.getText();
				String result = PasswordManager.validate(password);
				if (!result.equals("valid")) {
					getErrorMessage().setText(result);
					getErrorMessage().setVisible(true);
				}

			}
		});
		add(passwordTextField);
		passwordTextField.setName("passwordTextField");
		passwordLabel.setLabelFor(passwordTextField);

		JLabel titleLabel = new JLabel("Create Account Owner");
		titleLabel.setBounds(74, 8, 324, 36);
		add(titleLabel);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(16, 175, 88, 29);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = passwordTextField.getText();
				String name = accountOwnerNameTextField.getText();
				AccountOwner accountOwner = new AccountOwner(name,password);
				String result = accountOwner.validate();
				if (result.equals("valid")) {
					getErrorMessage().setText("");
					getErrorMessage().setVisible(false);
					accountOwner.put();
					ownerIdLabel.setText(AccountOwner.getNextId());
				} else {
					getErrorMessage().setText(result);
					getErrorMessage().setVisible(true);
				}

			}
		});
		add(submitButton);
		submitButton.setHorizontalAlignment(SwingConstants.LEFT);

		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(390, 175, 76, 29);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getErrorMessage().setText("");
				passwordTextField.setText("");
				accountOwnerNameTextField.setText("");
			}
		});
		add(clearButton);
		clearButton.setHorizontalAlignment(SwingConstants.LEFT);
		clearButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

		errorMessageLabel = new JLabel("");
		errorMessageLabel.setBounds(16, 155, 324, 16);
		errorMessageLabel.setName("errorMessageLabel");
		add(errorMessageLabel);

		JLabel accountOwnerIdLabel = new JLabel("Account Owner ID");
		accountOwnerIdLabel.setBounds(16, 56, 191, 24);
		accountOwnerIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(accountOwnerIdLabel);

		JLabel accountOwnerNameLabel = new JLabel("Name");
		accountOwnerNameLabel.setBounds(16, 94, 191, 24);
		accountOwnerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		add(accountOwnerNameLabel);

		accountOwnerNameTextField = new JTextField();
		accountOwnerNameTextField.setText("");
		accountOwnerNameTextField.setBounds(211, 89, 255, 29);
		accountOwnerNameTextField.setName("accountOwnerNameTextField");
		accountOwnerNameTextField.setColumns(30);
		add(accountOwnerNameTextField);

	}

	public JLabel getErrorMessage() {
		return errorMessageLabel;
	}

	public JTextField getPasswordField() {
		return passwordTextField;
	}
}
