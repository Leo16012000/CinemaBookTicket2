package com.leo.controllers.popup;

import com.leo.dao.MovieDao;
import com.leo.dao.ShowtimeDao;
import com.leo.models.Showtime;
import com.leo.views.popup.ShowtimePopupView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import static java.util.Arrays.asList;

public class ShowtimePopupController {
  ShowtimeDao showtimeDao = new ShowtimeDao();
  MovieDao movieDao = new MovieDao();
  JFrame previousView;

  public void add(ShowtimePopupView view, SuccessCallback sc, ErrorCallback ec) throws SQLException {
    if (previousView != null && previousView.isDisplayable()) {
      previousView.requestFocus();
      return;
    }
    previousView = view;
    view.setVisible(true);
    view.getCboMovieName().setModel(new DefaultComboBoxModel(movieDao.getAll());
//    view.getTxtColumnsNum().setText(Integer.toString(showtime.getSeatsColumnNum()));
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
//    showtime.setShowtimeNum(Integer.valueOf(view.getTxtNumber().getText()));
//    showtime.setSeatsRowNum(Integer.valueOf(view.getTxtRowsNum().getText()));
//    showtime.setSeatsColumnNum(Integer.valueOf(view.getTxtColumnsNum().getText()));
    System.out.println(showtime);
    showtimeDao.save(showtime);
    return;
  }

  public void edit(ShowtimePopupView view, Showtime showtime, SuccessCallback sc, ErrorCallback ec) {
    if (previousView != null && previousView.isDisplayable()) {
      previousView.requestFocus();
      return;
    }
    previousView = view;
    view.setVisible(true);
    view.getBtnCancel().addActionListener(evt -> view.dispose());
//    view.getTxtNumber().setText(Integer.toString(showtime.getShowtimeNum()));
//    view.getTxtRowsNum().setText(Integer.toString(showtime.getSeatsRowNum()));
//    view.getTxtColumnsNum().setText(Integer.toString(showtime.getSeatsColumnNum()));
    view.getBtnOK().setText("Cập nhật");
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
//    Integer number = Integer.valueOf(view.getTxtNumber().getText()),
//        rowsNum = Integer.valueOf(view.getTxtRowsNum().getText()),
//        columnsNum = Integer.valueOf(view.getTxtColumnsNum().getText());
//    if (number == null || rowsNum == null || columnsNum == null) {
//      throw new Exception("Please complete all infomation!");
//    }

//    showtime.setShowtimeNum(number);
//    showtime.setSeatsRowNum(rowsNum);
//    showtime.setSeatsColumnNum(columnsNum);
    showtimeDao.update(showtime);
    return true;
  }
}
