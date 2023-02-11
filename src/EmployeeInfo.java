import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	private JTextField Idfld;
	private JTextField firstnamefld;
	private JTextField lastnamefld;
	private JTextField agefld;
	private JTextField usernamefld;
	private JPasswordField passwordfld;
	private JTextField searchfld;

	
public void refreshTable() {		
		try {		
			String query = "select ID, firstName, lastName, age from EmployeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);			
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));			
			rs.close();
			pst.close();			
		} catch (Exception e2) {
			// TODO: handle exception		
			System.out.println(e2.getMessage());
		}
	}
public void refreshFields() {
	Idfld.setText(null);
	firstnamefld.setText(null);
	lastnamefld.setText(null);
	usernamefld.setText(null);
	passwordfld.setText(null);
	agefld.setText(null);
}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
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
	public EmployeeInfo() {
		connection = sqliteConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1021, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(377, 162, 598, 274);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row =  table.getSelectedRow();
					String ID = (table.getModel().getValueAt(row, 0)).toString();				
					String query =  "select * from EmployeeInfo where ID='" + ID + "'";	
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();				
					while (rs.next()) {					
						Idfld.setText(rs.getString("ID"));
						firstnamefld.setText(rs.getString("firstName"));
						lastnamefld.setText(rs.getString("lastName"));
						usernamefld.setText(rs.getString("userName"));
						passwordfld.setText(rs.getString("password"));
						agefld.setText(rs.getString("age"));	
					}					
					rs.close();
					pst.close();	
				} catch (Exception e2) {
					// TODO: handle exception			
					System.out.println(e2.getMessage());
				}
			}
		});
		table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setViewportView(table);		
		JButton btnLoad = new JButton("Load Employee Date");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select ID,firstName,lastName,age from EmployeeInfo";
					PreparedStatement pst = connection.prepareStatement(query);				
					ResultSet res = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					res.close();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
				}		
			}
		});
		btnLoad.setBackground(Color.DARK_GRAY);
		btnLoad.setForeground(Color.LIGHT_GRAY);
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLoad.setBounds(780, 103, 205, 35);
		btnLoad.setFocusable(false);
		contentPane.add(btnLoad);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(28, 162, 60, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblFirstname = new JLabel("First Name:");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblFirstname.setBounds(23, 208, 117, 35);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Last Name:");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblLastname.setBounds(23, 254, 117, 35);
		contentPane.add(lblLastname);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAge.setBounds(28, 300, 117, 35);
		contentPane.add(lblAge);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblUserName.setBounds(28, 346, 117, 35);
		contentPane.add(lblUserName);
		
		Idfld = new JTextField();
		Idfld.setFont(new Font("Tahoma", Font.BOLD, 16));
		Idfld.setBounds(165, 162, 185, 25);
		contentPane.add(Idfld);
		Idfld.setColumns(10);
		
		firstnamefld = new JTextField();
		firstnamefld.setFont(new Font("Tahoma", Font.BOLD, 16));
		firstnamefld.setColumns(10);
		firstnamefld.setBounds(165, 211, 185, 25);
		contentPane.add(firstnamefld);
		
		lastnamefld = new JTextField();
		lastnamefld.setFont(new Font("Tahoma", Font.BOLD, 16));
		lastnamefld.setColumns(10);
		lastnamefld.setBounds(165, 259, 185, 25);
		contentPane.add(lastnamefld);
		
		agefld = new JTextField();
		agefld.setFont(new Font("Tahoma", Font.BOLD, 16));
		agefld.setColumns(10);
		agefld.setBounds(165, 305, 185, 25);
		contentPane.add(agefld);
		
		usernamefld = new JTextField();
		usernamefld.setFont(new Font("Tahoma", Font.BOLD, 16));
		usernamefld.setColumns(10);
		usernamefld.setBounds(165, 351, 185, 25);
		contentPane.add(usernamefld);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {			
					String query = "insert into EmployeeInfo (ID, firstName, lastName, userName, password, age) values (?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);			
					pst.setString(1, Idfld.getText());
					pst.setString(2, firstnamefld.getText());
					pst.setString(3, lastnamefld.getText());
					pst.setString(4, usernamefld.getText());
					pst.setString(5, passwordfld.getText());
					pst.setString(6, agefld.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Saved!");
					pst.close();
					
					
				}catch(Exception e3) {			
					System.out.println(e3.getMessage());				
				}			
				refreshTable();
				refreshFields();			
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnSave.setBounds(30, 436, 155, 35);
		btnSave.setFocusable(false);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "update EmployeeInfo set ID='" + Idfld.getText() + "', firstName='" + firstnamefld.getText() + 
							"', lastName='" + lastnamefld.getText() + "', userName='" + usernamefld.getText() + "', password='" +
							passwordfld.getText() + "', age='" + agefld.getText()  + "' where ID='" + Idfld.getText() + "'";		
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();	
					JOptionPane.showMessageDialog(null, "Data Updated!");
					pst.close();
			}catch(Exception e3) {		
					System.out.println(e3.getMessage());
			}
				refreshTable();
				refreshFields();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnUpdate.setBounds(195, 436, 155, 35);
		btnUpdate.setFocusable(false);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Are You sure to delete?", "Delete", JOptionPane.YES_NO_OPTION);
				//yes --> 0
				//no --> 1
				if(action == 0) {
					try {
							String query = "delete from EmployeeInfo where ID='" + Idfld.getText()+ "'";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Data Removed!");
							pst.close();
					}catch(Exception e3) {
							System.out.println(e3.getMessage());	
				}
						refreshTable();
						refreshFields();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnDelete.setBounds(101, 482, 155, 35);
		btnDelete.setFocusable(false);
		contentPane.add(btnDelete);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPassword.setBounds(28, 379, 117, 35);
		contentPane.add(lblPassword);
		
		passwordfld = new JPasswordField();
		passwordfld.setBounds(165, 387, 185, 25);
		contentPane.add(passwordfld);
		
		JComboBox searchBox = new JComboBox();
		searchBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		searchBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "firstName", "lastName"}));
		searchBox.setBounds(377, 106, 139, 30);
		contentPane.add(searchBox);
		
		
		searchfld = new JTextField();
		searchfld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {  
					String selection = searchBox.getSelectedItem().toString();
					String query = "select ID, firstName, lastName, age from EmployeeInfo where " + selection + "=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, searchfld.getText());
					ResultSet res = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					res.close();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
				}
			}
		});
		
		searchfld.setBounds(544, 105, 205, 30);
		contentPane.add(searchfld);
		searchfld.setColumns(10);
		
		refreshTable();
	}
}
