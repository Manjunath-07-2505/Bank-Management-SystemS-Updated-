package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import util.DBConnection;

public class CheckBalance extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtAccNo;
	private JPasswordField txtPassword;
	private JTextField txtBalance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CheckBalance frame = new CheckBalance();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CheckBalance() {
		setTitle("Check Balance");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Title
		JLabel lblTitle = new JLabel("Check Balance");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setBounds(150, 10, 200, 25);
		contentPane.add(lblTitle);

		// Account Number
		JLabel lblAccNo = new JLabel("Account Number");
		lblAccNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccNo.setBounds(90, 70, 120, 20);
		contentPane.add(lblAccNo);

		txtAccNo = new JTextField();
		txtAccNo.setBounds(230, 70, 120, 20);
		contentPane.add(txtAccNo);

		// Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(90, 110, 120, 20);
		contentPane.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(230, 110, 120, 20);
		contentPane.add(txtPassword);

		// Balance
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBalance.setBounds(90, 150, 120, 20);
		contentPane.add(lblBalance);

		txtBalance = new JTextField();
		txtBalance.setBounds(230, 150, 120, 20);
		txtBalance.setEditable(false);
		contentPane.add(txtBalance);

		// Check Button
		JButton btnCheck = new JButton("Check");
		btnCheck.setBackground(Color.CYAN);
		btnCheck.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCheck.setBounds(160, 200, 100, 25);
		contentPane.add(btnCheck);

		// Back Button
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.PINK);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(330, 230, 80, 25);
		contentPane.add(btnBack);

		// Check Balance Logic
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnection.getConnection();

					String sql = "SELECT balance FROM accounts WHERE acc_no=? AND password=?";
					PreparedStatement ps = con.prepareStatement(sql);

					ps.setInt(1, Integer.parseInt(txtAccNo.getText()));
					ps.setString(2, new String(txtPassword.getPassword()));

					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						txtBalance.setText(String.valueOf(rs.getDouble("balance")));
					} else {
						JOptionPane.showMessageDialog(null,
								"Invalid Account Number or Password",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Back Button Logic
		btnBack.addActionListener(e -> {
			new Dashboard().setVisible(true);
			dispose();
		});
	}
}
