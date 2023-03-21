package com.leo.controllers.popup;

import com.leo.dao.ShowtimeDao;
import com.leo.models.Showtime;

import javax.swing.*;
import java.util.*;

public class ShowtimePopupController {
  private ShowtimeDao showtimeDao = ShowtimeDao.getInstance();
  // JFrame previousView;

  // public void add(ShowtimePopupView view, SuccessCallback sc, ErrorCallback ec)
  // throws SQLException {
  // if (previousView != null && previousView.isDisplayable()) {
  // previousView.requestFocus();
  // return;
  // }
  // previousView = view;
  // view.setVisible(true);
  // view.getCboMovieName().setModel(new
  // DefaultComboBoxModel(MovieDao.getInstance().getAll().stream().toArray()));
  // view.getCboAuditoriumNumber()
  // .setModel(new
  // DefaultComboBoxModel(AuditoriumDao.getInstance().getAll().stream().toArray()));
  // view.getBtnCancel().addActionListener(evt -> view.dispose());
  // view.getBtnOK().addActionListener(evt -> {
  // try {
  // addShowtime(view);
  // view.dispose();
  // view.showMessage("Added showtime successfully!");
  // sc.onSuccess();
  // } catch (Exception ex) {
  // ec.onError(ex);
  // }
  // });

  // }

  public Showtime addShowtime(Showtime model) throws Exception {
    return showtimeDao.get(showtimeDao.save(model));
  }

  // public void edit(ShowtimePopupView view, Showtime showtime, SuccessCallback
  // sc, ErrorCallback ec)
  // throws SQLException {
  // if (previousView != null && previousView.isDisplayable()) {
  // previousView.requestFocus();
  // return;
  // }
  // previousView = view;
  // view.setVisible(true);
  // view.getBtnCancel().addActionListener(evt -> view.dispose());
  // view.getStartTimeSpinner().setValue(showtime.getStartTime());
  // view.getEndTimeSpinner().setValue(showtime.getEndTime());
  // view.getjDate().setDate(showtime.getStartTime());
  // List<Movie> movies = MovieDao.getInstance().getAll();
  // List<Auditorium> auditoriums = AuditoriumDao.getInstance().getAll();
  // view.getCboMovieName().setModel(new
  // DefaultComboBoxModel(movies.stream().toArray()));
  // view.getCboAuditoriumNumber().setModel(new
  // DefaultComboBoxModel(auditoriums.stream().toArray()));
  // view.getCboMovieName().getModel().setSelectedItem(showtime.getMovie());
  // view.getCboAuditoriumNumber().getModel().setSelectedItem(showtime.getAuditorium());
  // view.getLbTitle().setText("Update showtime");
  // view.getBtnOK().setText("Update");
  // view.getBtnOK().addActionListener(evt -> {
  // try {
  // editShowtime(view, showtime);
  // view.dispose();
  // view.showMessage("Edited showtime successfully!");
  // sc.onSuccess();
  // } catch (Exception ex) {
  // ec.onError(ex);
  // }
  // });
  // }

  public Showtime editShowtime(Showtime showtime) throws Exception {
    showtimeDao.update(showtime);
    return showtimeDao.get(showtime.getId());
  }
}
