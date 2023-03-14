package views;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MovieDao;
import dao.ShowtimeDao;
import models.Movie;
import models.Showtime;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminDahsboardFramebuffer extends JFrame {

	private JTextField textField;
	private JTable movieTable;
	private JTable reservationTable;
	private JPanel contentPane;
	public JPanel getContentPane() {
		return contentPane;
	}


	private HeaderPanel mainPanel;


	private MoviePanel moviePanel;
	private ShowtimePanel showtimePanel;

	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminDahsboardFramebuffer() throws SQLException {
		mainPanel = new HeaderPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 1024);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mainPanel.setBounds(1100, -16, 133, 120);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
//		JButton btnLogin = new JButton("Login");
//		btnLogin.setBounds(45, 25, 40, 25);
//		AccountPanel.add(btnLogin);
//		
//		JLabel lblName = new JLabel("Name");
//		lblName.setBounds(0, 30, 70, 15);
//		AccountPanel.add(lblName);
		
//		JPanel ReservationPanel = new JPanel();
//		ReservationPanel.setBounds(24, 32, 686, 445);
//		contentPane.add(ReservationPanel);
//		ReservationPanel.setLayout(null);
//		
//		ReservationTable = new JTable();
//		ReservationTable.setBounds(155, 54, 375, 64);
//		ReservationTable.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column"
//			}
//		));
//		ReservationPanel.add(ReservationTable);
//		

//		
		displayMoviePanel();
//		
//		JPanel SeatPanel = new JPanel();
//		SeatPanel.setBounds(157, 32, 442, 445);
//		contentPane.add(SeatPanel);
//		SeatPanel.setLayout(null);
//		
//		JButton btnNewButton_1 = new JButton("Screen");
//		btnNewButton_1.setBounds(38, 27, 351, 32);
//		SeatPanel.add(btnNewButton_1);
//		
//		JButton btnBack = new JButton("Back");
//		btnBack.setBounds(12, 0, 117, 25);
//		contentPane.add(btnBack);
	}
	public void displayMoviePanel() throws SQLException {
		ArrayList<Movie> movies = new ArrayList<>(MovieDao.getInstance().getAll());
		moviePanel = new MoviePanel(movies, contentPane);
		moviePanel.setBounds(194, 32, 432, 379);
		contentPane.add(moviePanel);
		moviePanel.setLayout(null);
//        // Add the table to a JScrollPane
//        JScrollPane scrollPane = new JScrollPane(moviePanel.getMovieTable());
//
//        // Add the JScrollPane to the JFrame
//        contentPane.add(scrollPane);
		moviePanel.getBtnSearch().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Movie> movies;
				try {
					contentPane.remove(moviePanel);
					movies = new ArrayList<>(
							MovieDao.getInstance().searchByKey("title", moviePanel.getTextField().getText()));
					moviePanel.updatePanel(movies, contentPane);
					moviePanel.setBounds(194, 32, 432, 379);
					contentPane.add(moviePanel);
					moviePanel.setLayout(null);
					SwingUtilities.updateComponentTreeUI(contentPane);
					System.out.println("trigger search " + moviePanel.getTextField().getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
//		
//		MovieTable = new JTable();
//		MovieTable.setBounds(30, 212, 375, 112);
//		MovieTable.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},        view.setVisible(true);

//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column"
//			}
//		));
//		MoviePanel.add(MovieTable);
//		
//		textField = new JTextField();
//		textField.setBounds(98, 125, 114, 19);
//		MoviePanel.add(textField);
//		textField.setColumns(10);
//		
//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.setBounds(217, 122, 117, 25);
//		MoviePanel.add(btnNewButton);
	}
	
	public void displayShowtimePanel(String movieName, int movieId) throws SQLException {
		ArrayList<Showtime> showtimes = ShowtimeDao.getInstance().searchByKey("movie_id", Integer.toString(movieId));
		showtimePanel = new ShowtimePanel(showtimes);
		showtimePanel.setBounds(114, 32, 553, 471);
//		contentPane.remove(moviePanel);
		contentPane.add(showtimePanel);
		showtimePanel.setLayout(null);
		
		JLabel lblMovieName = new JLabel(movieName);
		lblMovieName.setBounds(211, 28, 154, 15);
		showtimePanel.add(lblMovieName);
	}

	
    public void setPanel(JPanel panel) {
        panel.setVisible(true);
    }
	public HeaderPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(HeaderPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public MoviePanel getMoviePanel() {
		// TODO Auto-generated method stub
		return moviePanel;
	}
}
