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
import javax.swing.JPasswordField;

public class RegisterPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
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
	public RegisterPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(157, 10, 156, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(113, 64, 88, 20);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(244, 66, 76, 18);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(113, 111, 88, 20);
		contentPane.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(244, 113, 76, 18);
		contentPane.add(txtPassword);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.getConnection();

					String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);

					ps.setString(1, txtUsername.getText());
					ps.setString(2, new String(txtPassword.getPassword()));

					int i = ps.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Registration Successful");
						new LoginPage().setVisible(true);
						dispose();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(168, 174, 111, 21);
		contentPane.add(btnNewButton);

	}

}
