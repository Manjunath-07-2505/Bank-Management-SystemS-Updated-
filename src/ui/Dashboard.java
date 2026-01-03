package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bank Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(154, 10, 149, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateAccount().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(53, 62, 149, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Check Balance");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CheckBalance().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setBounds(53, 111, 149, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Deposit Money");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DepositMoney().setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.BLUE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBounds(53, 163, 149, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Withdraw Money");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WithdrawMoney().setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(Color.BLUE);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBounds(242, 63, 149, 21);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Transfer Money");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TransferMoney().setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(Color.BLUE);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4.setBounds(242, 112, 149, 21);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Change Password");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword().setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setBackground(Color.BLUE);
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5.setBounds(242, 164, 149, 21);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Logout");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginPage().setVisible(true); // open login page
				dispose();
			}
		});
		btnNewButton_6.setBackground(Color.CYAN);
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_6.setBounds(174, 218, 80, 21);
		contentPane.add(btnNewButton_6);

	}

}
