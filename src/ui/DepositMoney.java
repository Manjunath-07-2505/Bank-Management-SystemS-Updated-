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
import java.awt.event.ActionEvent;

public class DepositMoney extends JFrame {

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
					DepositMoney frame = new DepositMoney();
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
	public DepositMoney() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Deposit Money");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(160, 10, 144, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(96, 61, 108, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(96, 98, 85, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(96, 130, 85, 20);
		contentPane.add(lblNewLabel_3);
		
		txtAccNo = new JTextField();
		txtAccNo.setBounds(238, 59, 76, 18);
		contentPane.add(txtAccNo);
		txtAccNo.setColumns(10);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(238, 96, 76, 18);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(238, 128, 76, 18);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Deposit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.getConnection();

					String sql = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ? AND password = ?";
					PreparedStatement ps = con.prepareStatement(sql);

					ps.setDouble(1, Double.parseDouble(txtAmount.getText()));
					ps.setInt(2, Integer.parseInt(txtAccNo.getText()));
					ps.setString(3, new String(txtPassword.getPassword()));

					int i = ps.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Amount Deposited Successfully");
						new Dashboard().setVisible(true);
						dispose();
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
		btnNewButton.setBounds(160, 183, 80, 21);
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
