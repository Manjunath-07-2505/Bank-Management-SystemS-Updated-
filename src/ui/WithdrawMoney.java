package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import util.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class WithdrawMoney extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAccNo;
	private JTextField txtAmount;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawMoney frame = new WithdrawMoney();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WithdrawMoney() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Withdraw Money");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(150, 10, 164, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(91, 65, 110, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(111, 132, 107, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Amount");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(111, 97, 81, 20);
		contentPane.add(lblNewLabel_3);
		
		txtAccNo = new JTextField();
		txtAccNo.setBounds(221, 63, 76, 18);
		contentPane.add(txtAccNo);
		txtAccNo.setColumns(10);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(221, 97, 76, 18);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(221, 130, 76, 18);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Withdraw");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.getConnection();

					// Step 1: Get current balance
					String checkSql = "SELECT balance FROM accounts WHERE acc_no=? AND password=?";
					PreparedStatement ps1 = con.prepareStatement(checkSql);

					ps1.setInt(1, Integer.parseInt(txtAccNo.getText()));
					ps1.setString(2, new String(txtPassword.getPassword()));

					ResultSet rs = ps1.executeQuery();

					if (rs.next()) {
						double currentBalance = rs.getDouble("balance");
						double withdrawAmount = Double.parseDouble(txtAmount.getText());

						if (currentBalance >= withdrawAmount) {

							// Step 2: Withdraw amount
							String updateSql = "UPDATE accounts SET balance = balance - ? WHERE acc_no=?";
							PreparedStatement ps2 = con.prepareStatement(updateSql);

							ps2.setDouble(1, withdrawAmount);
							ps2.setInt(2, Integer.parseInt(txtAccNo.getText()));

							ps2.executeUpdate();

							JOptionPane.showMessageDialog(null, "Withdrawal Successful");
							new Dashboard().setVisible(true);
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Insufficient Balance");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Account Number or Password");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(160, 192, 91, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(346, 230, 80, 21);
		contentPane.add(btnNewButton_1);

	}

}
