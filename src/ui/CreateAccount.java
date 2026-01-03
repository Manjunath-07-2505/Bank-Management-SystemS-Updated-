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

public class CreateAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAccNo;
	private JTextField txtName;
	private JTextField txtBalance;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
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
	public CreateAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Bank Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(137, 10, 188, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(89, 61, 116, 18);
		contentPane.add(lblNewLabel_1);
		
		txtAccNo = new JTextField();
		txtAccNo.setBounds(249, 59, 76, 18);
		contentPane.add(txtAccNo);
		txtAccNo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Customer Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(89, 100, 112, 18);
		contentPane.add(lblNewLabel_2);
		
		txtName = new JTextField();
		txtName.setBounds(249, 100, 76, 18);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Initial Balance");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(89, 141, 116, 16);
		contentPane.add(lblNewLabel_3);
		
		txtBalance = new JTextField();
		txtBalance.setBounds(249, 139, 76, 18);
		contentPane.add(txtBalance);
		txtBalance.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(89, 178, 116, 12);
		contentPane.add(lblNewLabel_4);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(249, 176, 76, 18);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.getConnection();

					String sql = "INSERT INTO accounts(acc_no, name, balance, password) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);

					ps.setInt(1, Integer.parseInt(txtAccNo.getText()));
					ps.setString(2, txtName.getText());
					ps.setDouble(3, Double.parseDouble(txtBalance.getText()));
					ps.setString(4, new String(txtPassword.getPassword()));

					int i = ps.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Account Created Successfully");
						new Dashboard().setVisible(true);
						dispose();
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(171, 217, 80, 21);
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
