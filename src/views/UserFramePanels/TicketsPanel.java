package views.UserFramePanels;

import dao.AuditoriumDao;
import dao.SeatDao;
import dao.ShowtimeDao;
import models.Auditorium;
import models.Movie;
import models.Seat;
import models.SeatsReservation;
import models.Showtime;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TicketsPanel extends JPanel {
    public JButton back, save;
    private ArrayList<Seat> seats;
    private Showtime showtime;
    private Auditorium auditorium;
    private Movie movie;
    private JLabel film_title, session_title, screen;
    private Map<JButton, Seat> ticketsBtns = new LinkedHashMap<JButton, Seat>();
    private ArrayList<Integer> selected = new ArrayList<Integer>();
    private ArrayList<Integer> isSold = new ArrayList<Integer>();


    public TicketsPanel(int auditoriumId, int showtimeId) throws SQLException {
    	this.seats = SeatDao.getInstance().getByAuditoriumIdAndShowtimeId(auditoriumId, showtimeId);
    	this.auditorium = AuditoriumDao.getInstance().get(auditoriumId);
    	this.showtime = ShowtimeDao.getInstance().get(showtimeId);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setBackground(new Color(255, 178, 102));
        
        for (int i = 0; i < auditorium.getSeatsRowNum(); ++i) {
            JLabel row = new JLabel("Row " + (i + 1));
            row.setBounds(105, i * 50 + 170, 60, 40); //?50
            row.setFont(new Font("Arial", Font.BOLD, 16));
            this.add(row);
            for (int j = 0; j < auditorium.getSeatsColumnNum(); ++j) {
                JButton btn = new JButton();
                btn.setBounds(j * 50 + 165, i * 50 + 170, 40, 40); //?50
                btn.setBackground(new Color(65, 65, 255));
                btn.addActionListener(e -> {
                    if (ticketsBtns.get(btn) == null) { //not sold
                        if (selected.contains(ticketsBtns.get(btn).getId())) {
                            btn.setBackground(new Color(65, 65, 255));
                            selected.remove((Integer) ticketsBtns.get(btn).getId());
                        } else {
                            btn.setBackground(new Color(55, 255, 0));
                            selected.add(ticketsBtns.get(btn).getId());
                        }
                    }
                });
                ticketsBtns.put(btn, null);
                this.add(btn);
            }
        }
        for (int i = 0; i < 10; ++i) {
            JLabel col = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            col.setBounds(i * 50 + 165, 410, 40, 40);
            col.setFont(new Font("Arial", Font.BOLD, 16));
            this.add(col);
        }

        film_title = new JLabel("", SwingConstants.CENTER);
        film_title.setBounds(0, 10, 800, 40);
        film_title.setFont(new Font("Arial", Font.BOLD, 20));

        session_title = new JLabel("", SwingConstants.CENTER);
        session_title.setBounds(0, 40, 800, 40);
        session_title.setFont(new Font("Arial", Font.BOLD, 20));

        back = new JButton("Back");
        back.setBounds(165, 500, 100, 40);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.gray);
        back.setForeground(Color.black);

        save = new JButton("Confirm");
        save.setBounds(505, 500, 150, 40);
        save.setFont(new Font("Arial", Font.BOLD, 16));
        save.setBackground(new Color(170, 65, 255));
        save.setForeground(Color.black);

        screen = new JLabel("Screen", SwingConstants.CENTER);
        screen.setFont(new Font("Arial", Font.BOLD, 16));
        screen.setOpaque(true);
        screen.setBackground(Color.GRAY);
        screen.setBounds(135, 120, 550, 25);

        this.add(film_title);
        this.add(session_title);
        this.add(back);
        this.add(save);
        this.add(screen);
    }

    public void setSeats(ArrayList<Seat> seats) {
//        if (tickets.size() != 50) {
//            System.out.println("The number of tickets is not 50");
//            System.exit(0);
//        }
        selected.clear();
        int idx = 0;
        for (Map.Entry<JButton, Seat> pair : ticketsBtns.entrySet()) {
            pair.setValue(seats.get(idx));
            if (seats.get(idx)!= null) pair.getKey().setBackground(Color.red);
            else pair.getKey().setBackground(new Color(65, 65, 255));
            idx++;
        }
        session_title.setText("Start time: " + showtime.getStartTime() + "       End time: " + showtime.getEndTime());
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        film_title.setText(movie.getTitle() + " (" + movie.getDurationTime() + " toi thieu)");
    }

    public ArrayList<Integer> getSelected() {
        return selected;
    }
}
