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

public class ChangePassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAccNo;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(154, 10, 146, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(96, 64, 108, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Old Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(96, 92, 94, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(96, 124, 94, 25);
		contentPane.add(lblNewLabel_3);
		
		txtAccNo = new JTextField();
		txtAccNo.setBounds(228, 64, 76, 18);
		contentPane.add(txtAccNo);
		txtAccNo.setColumns(10);
		
		txtOldPassword = new JPasswordField();
		txtOldPassword.setBounds(228, 96, 76, 18);
		contentPane.add(txtOldPassword);
		txtOldPassword.setColumns(10);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(228, 131, 76, 18);
		contentPane.add(txtNewPassword);
		txtNewPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.getConnection();

					String sql = "UPDATE accounts SET password=? WHERE acc_no=? AND password=?";
					PreparedStatement ps = con.prepareStatement(sql);

					ps.setString(1, new String(txtNewPassword.getPassword()));
					ps.setInt(2, Integer.parseInt(txtAccNo.getText()));
					ps.setString(3, new String(txtOldPassword.getPassword()));

					int i = ps.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Password Changed Successfully");
						new Dashboard().setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Account Numberor Old Password");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(170, 183, 80, 21);
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
