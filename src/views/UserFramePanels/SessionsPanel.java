package views.UserFramePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import models.Movie;

public class SessionsPanel extends JPanel {
    public JList<Session> sessionList;
    public DefaultListModel<Session> sessionModel = new DefaultListModel<>();
    public JButton back;
    private JLabel film_title;
    private Movie movie;

    public SessionsPanel() {
        this.setSize(800, 600);
        this.setBackground(new Color(255, 178, 102));

        sessionList = new JList<>();
        sessionList.setModel(sessionModel);
        sessionList.setPreferredSize(new Dimension(783, 475));
        sessionList.setBackground(new Color(255, 178, 102));
        sessionList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        sessionList.setFixedCellHeight(150);
        sessionList.setFixedCellWidth(195);
        sessionList.setVisibleRowCount(0);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) sessionList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        film_title = new JLabel("", SwingConstants.CENTER);
        film_title.setBounds(0, 10, 800, 40);
        film_title.setFont(new Font("Arial", Font.BOLD, 20));

        back = new JButton("Back");
        back.setPreferredSize(new Dimension(100, 40));
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.gray);
        back.setForeground(Color.black);

        this.add(film_title);
        this.add(new JScrollPane(sessionList));
        this.add(back);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        film_title.setText(movie.getTitle() + " (" + movie.getDurationTime() + " toi thieu)");
    }

}
