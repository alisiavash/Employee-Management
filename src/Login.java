import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	Connection connection = null;
	private JTextField usernamefld;
	private JPasswordField passwordfld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = sqliteConnection.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 165, 0));
		frame.setBounds(100, 100, 479, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(83, 191, 158, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPassword.setBounds(83, 236, 158, 34);
		frame.getContentPane().add(lblPassword);
		
		usernamefld = new JTextField();
		usernamefld.setFont(new Font("Tahoma", Font.BOLD, 18));
		usernamefld.setBackground(Color.LIGHT_GRAY);
		usernamefld.setBounds(278, 194, 149, 30);
		frame.getContentPane().add(usernamefld);
		usernamefld.setColumns(10);
		
		passwordfld = new JPasswordField();
		passwordfld.setBackground(Color.LIGHT_GRAY);
		passwordfld.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordfld.setBounds(278, 239, 149, 30);
		frame.getContentPane().add(passwordfld);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from EmployeeInfo where userName=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, usernamefld.getText());
					pst.setString(2, passwordfld.getText());
					
					ResultSet res = pst.executeQuery();
					int counter = 0;
					while(res.next()) {
						counter++;
					}
					if(counter==1) {
						JOptionPane.showMessageDialog(null, "Username and Password is correct.");
						frame.dispose();
						EmployeeInfo empInfo = new EmployeeInfo();
						empInfo.setVisible(true);
					}else if(counter > 1){
						JOptionPane.showMessageDialog(null, "Dublicated Username and Password.");
					}else {
						JOptionPane.showMessageDialog(null, "Wrong Username and Password.");
					}
					
					pst.close();
					res.close();
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLogin.setBounds(197, 309, 137, 30);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\alikh\\Downloads\\Java_db\\EmployeeManagment\\img\\login.png"));
		lblNewLabel_1.setBounds(29, 31, 212, 149);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
