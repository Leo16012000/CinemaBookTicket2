package views;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.MovieDao;
import models.Movie;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MoviesPanel extends JPanel {
    public ArrayList<Movie> movies = new ArrayList<Movie>(MovieDao.getInstance().getAll());;
    private JTextField textField;
    private JTable table_1;
    
	public MoviesPanel() throws SQLException {
		// Create a JTable with a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        // Add columns to the table
        model.addColumn("Title");
        model.addColumn("Country");
        model.addColumn("Duration time");
        model.addColumn("Ticket price");

        // Add rows to the table
        for (Movie obj : movies) {
            String[] row = {obj.getTitle(), obj.getCountry(), Integer.toString(obj.getDurationTime()), Integer.toString(obj.getPrice())};
            model.addRow(row);
        }
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(218, 28, 117, 25);
        add(btnSearch);
        
        textField = new JTextField();
        textField.setBounds(68, 31, 114, 19);
        add(textField);
        textField.setColumns(10);
        
        table.setBounds(31, 73, 328, 204);
        add(table);
        setVisible(true);
    }
	
//	public void ArrayListTableExample(ArrayList<Movie> movies) {
//
//        // Create a JTable with a DefaultTableModel
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//
//        // Add columns to the table
//        model.addColumn("Title");
//        model.addColumn("Country");
//        model.addColumn("Duration time");
//        model.addColumn("Ticket price");
//
//        // Add rows to the table
//        for (Movie obj : movies) {
//            String[] row = {obj.getTitle(), obj.getCountry(), Integer.toString(obj.getDurationTime()), Integer.toString(obj.getPrice())};
//            model.addRow(row);
//        }
//
//        // Set the size of the JFrame and make it visible
//        setSize(400, 300);
//        setVisible(true);
//    }
}