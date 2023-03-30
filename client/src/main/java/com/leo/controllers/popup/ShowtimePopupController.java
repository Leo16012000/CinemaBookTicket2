package com.leo.controllers.popup;

import com.leo.models.Auditorium;
import com.leo.models.Movie;
import com.leo.models.Showtime;
import com.leo.service.IAuditoriumService;
import com.leo.service.IMovieService;
import com.leo.service.IShowtimeService;
import com.leo.service.impl.AuditoriumService;
import com.leo.service.impl.MovieService;
import com.leo.service.impl.ShowtimeService;
import com.leo.utils.TimeHandle;
import com.leo.views.popup.ShowtimePopupView;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class ShowtimePopupController {
  JFrame previousView;
  private IShowtimeService showtimeService = ShowtimeService.getInstance();
  private IMovieService movieService = MovieService.getInstance();
  private IAuditoriumService auditoriumService = AuditoriumService.getInstance();
  private Logger logger = LogManager.getLogger(ShowtimePopupController.class);

  public void add(ShowtimePopupView view, SuccessCallback sc, ErrorCallback ec) throws IOException {
    if (previousView != null && previousView.isDisplayable()) {
      previousView.requestFocus();
      return;
    }
    previousView = view;
    view.setVisible(true);
    view.getCboMovieName().setModel(new DefaultComboBoxModel(movieService.getAll().stream().toArray()));
    view.getCboAuditoriumNumber().setModel(new DefaultComboBoxModel(auditoriumService.getAll().stream().toArray()));
    view.getBtnCancel().addActionListener(evt -> view.dispose());
    view.getBtnOK().addActionListener(evt -> {
      try {
        addShowtime(view);
        view.dispose();
        view.showMessage("Added showtime successfully!");
        sc.onSuccess();
      } catch (Exception ex) {
        ec.onError(ex);
      }
    });

  }

  public void addShowtime(ShowtimePopupView view) throws Exception {
    Showtime showtime = new Showtime();
    String startTime = view.getStartTimeSpinner().getValue().toString();
    Timestamp timestamp = TimeHandle.dateStimestampConversion(startTime);
    Date date = view.getjDate().getDate();
    Timestamp saveTime = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), timestamp.getHours(),
        timestamp.getMinutes(), 0, 0);
    showtime.setStartTime(saveTime);
    String endTime = view.getEndTimeSpinner().getValue().toString();
    timestamp = TimeHandle.dateStimestampConversion(endTime);
    saveTime = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), timestamp.getHours(),
        timestamp.getMinutes(), 0, 0);
    showtime.setEndTime(saveTime);
    logger.debug(view.getStartTimeSpinner().getValue().toString() + "|"
        + view.getEndTimeSpinner().getValue().toString() + "|" + view.getjDate().getDate().toString());
    Movie m = (Movie) (view.getCboMovieName().getSelectedItem());
    showtime.setMovieId(m.getId());
    Auditorium a = (Auditorium) (view.getCboAuditoriumNumber().getSelectedItem());
    showtime.setAuditoriumId(a.getId());
    showtimeService.save(showtime);
  }

  public void edit(ShowtimePopupView view, Showtime showtime, SuccessCallback sc, ErrorCallback ec) throws IOException {
    if (previousView != null && previousView.isDisplayable()) {
      previousView.requestFocus();
      return;
    }
    previousView = view;
    view.setVisible(true);
    view.getBtnCancel().addActionListener(evt -> view.dispose());
    view.getStartTimeSpinner().setValue(showtime.getStartTime());
    view.getEndTimeSpinner().setValue(showtime.getEndTime());
    view.getjDate().setDate(showtime.getStartTime());
    List<Movie> movies = movieService.getAll();
    List<Auditorium> auditoriums = auditoriumService.getAll();
    view.getCboMovieName().setModel(new DefaultComboBoxModel(movies.stream().toArray()));
    view.getCboAuditoriumNumber().setModel(new DefaultComboBoxModel(auditoriums.stream().toArray()));
    view.getCboMovieName().getModel().setSelectedItem(showtime.getMovie());
    view.getCboAuditoriumNumber().getModel().setSelectedItem(showtime.getAuditorium());
    view.getLbTitle().setText("Update showtime");
    view.getBtnOK().setText("Update");
    view.getBtnOK().addActionListener(evt -> {
      try {
        editShowtime(view, showtime);
        view.dispose();
        view.showMessage("Edited showtime successfully!");
        sc.onSuccess();
      } catch (Exception ex) {
        ec.onError(ex);
      }
    });
  }

  public boolean editShowtime(ShowtimePopupView view, Showtime showtime) throws Exception {
    String startTime = view.getStartTimeSpinner().getValue().toString();
    Timestamp timestamp = TimeHandle.dateStimestampConversion(startTime);
    Date date = view.getjDate().getDate();
    Timestamp saveTime = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), timestamp.getHours(),
        timestamp.getMinutes(), 0, 0);
    showtime.setStartTime(saveTime);
    String endTime = view.getEndTimeSpinner().getValue().toString();
    timestamp = TimeHandle.dateStimestampConversion(endTime);
    saveTime = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), timestamp.getHours(),
        timestamp.getMinutes(), 0, 0);
    showtime.setEndTime(saveTime);
    Movie m = (Movie) (view.getCboMovieName().getSelectedItem());
    showtime.setMovieId(m.getId());
    Auditorium a = (Auditorium) (view.getCboAuditoriumNumber().getSelectedItem());
    showtime.setAuditoriumId(a.getId());
    showtimeService.update(showtime);
    return true;
  }
}
