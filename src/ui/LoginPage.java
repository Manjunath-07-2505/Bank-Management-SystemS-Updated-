package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bank Login");
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(176, 10, 117, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(96, 72, 88, 21);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(238, 72, 100, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel txtPassword = new JLabel("Password: ");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setBounds(96, 123, 88, 21);
		contentPane.add(txtPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(238, 123, 100, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Connection con = DBConnection.getConnection();

				String sql = "SELECT * FROM users WHERE username=? AND password=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, textField.getText());
				ps.setString(2, textField_1.getText());


				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
				    JOptionPane.showMessageDialog(null, "Login Successful");

				    new Dashboard().setVisible(true);  // 👈 OPEN DASHBOARD
				    dispose();                         // 👈 CLOSE LOGIN PAGE

				} else {
				    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
				}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(130, 181, 80, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterPage().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(238, 182, 88, 21);
		contentPane.add(btnNewButton_1);

	}
}
