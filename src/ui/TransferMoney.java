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

public class TransferMoney extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFromAcc;
	private JTextField txtToAcc;
	private JTextField txtAmount;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferMoney frame = new TransferMoney();
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
	public TransferMoney() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transfer Money");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(157, 10, 160, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("From Account");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(80, 68, 100, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To Account");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(80, 100, 89, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Amount");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(87, 139, 82, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(87, 174, 63, 18);
		contentPane.add(lblNewLabel_4);
		
		txtFromAcc = new JTextField();
		txtFromAcc.setBounds(198, 68, 76, 16);
		contentPane.add(txtFromAcc);
		txtFromAcc.setColumns(10);
		
		txtToAcc = new JTextField();
		txtToAcc.setBounds(198, 100, 76, 18);
		contentPane.add(txtToAcc);
		txtToAcc.setColumns(10);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(198, 139, 76, 18);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(198, 174, 76, 18);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Transfor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = DBConnection.getConnection();
					con.setAutoCommit(false); // START TRANSACTION

					int fromAcc = Integer.parseInt(txtFromAcc.getText());
					int toAcc = Integer.parseInt(txtToAcc.getText());
					double amount = Double.parseDouble(txtAmount.getText());
					String password = new String(txtPassword.getPassword());

					// Step 1: Check sender balance & password
					String checkSql = "SELECT balance FROM accounts WHERE acc_no=? AND password=?";
					PreparedStatement ps1 = con.prepareStatement(checkSql);
					ps1.setInt(1, fromAcc);
					ps1.setString(2, password);

					ResultSet rs = ps1.executeQuery();

					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "Invalid From Account or Password");
						return;
					}

					double balance = rs.getDouble("balance");
					if (balance < amount) {
						JOptionPane.showMessageDialog(null, "Insufficient Balance");
						return;
					}

					// Step 2: Debit from sender
					String debitSql = "UPDATE accounts SET balance = balance - ? WHERE acc_no=?";
					PreparedStatement ps2 = con.prepareStatement(debitSql);
					ps2.setDouble(1, amount);
					ps2.setInt(2, fromAcc);
					ps2.executeUpdate();

					// Step 3: Credit to receiver
					String creditSql = "UPDATE accounts SET balance = balance + ? WHERE acc_no=?";
					PreparedStatement ps3 = con.prepareStatement(creditSql);
					ps3.setDouble(1, amount);
					ps3.setInt(2, toAcc);
					int creditCount = ps3.executeUpdate();

					if (creditCount == 0) {
						con.rollback();
						JOptionPane.showMessageDialog(null, "Invalid To Account Number");
						return;
					}

					con.commit(); // SUCCESS
					JOptionPane.showMessageDialog(null, "Transfer Successful");
					new Dashboard().setVisible(true);
					dispose();

				} catch (Exception ex) {
					try {
						if (con != null) con.rollback();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(142, 219, 89, 21);
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
