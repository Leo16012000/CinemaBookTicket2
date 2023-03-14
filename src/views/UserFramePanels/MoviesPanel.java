package views.UserFramePanels;

import javax.swing.*;

import models.Movie;

import java.awt.*;

public class MoviesPanel extends JPanel {
    public JList<Movie> filmList;
    public DefaultListModel<Movie> filmsModel = new DefaultListModel<>();

    public MoviesPanel() {
        this.setSize(800, 600);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        filmList = new JList<>();
        filmList.setModel(filmsModel);
        filmList.setBackground(new Color(255, 178, 102));
        filmList.setModel(filmsModel);
        filmList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        filmList.setFixedCellHeight(150);
        filmList.setFixedCellWidth(192);
        filmList.setVisibleRowCount(0);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) filmList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.add(new JScrollPane(filmList));
    }
}
