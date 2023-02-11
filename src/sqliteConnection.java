import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqliteConnection {
	
	public static Connection dbConnection() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Employeedb.db");
			JOptionPane.showMessageDialog(null, "Connection Successfully Established ....");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
//			JOptionPane.showMessageDialog(null, e.getMessage());
			JOptionPane.showMessageDialog(null, "Error! No Connection ....");
			return null;
		}
	}
}
