import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calendar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar frame = new Calendar();
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
	public Calendar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(332, 122, 116, 39);
		contentPane.add(dateChooser);
		
		JButton btnDate = new JButton("Show Date");
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(dateChooser.getDate());
				dateChooser.setDateFormatString("yyyy-MM-dd");
				System.out.println(((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
			}
		});
		btnDate.setBounds(310, 236, 110, 39);
		contentPane.add(btnDate);
	}
}
