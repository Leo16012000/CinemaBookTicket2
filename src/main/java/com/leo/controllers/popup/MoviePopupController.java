package com.leo.controllers.popup;

import javax.swing.JFrame;

import com.leo.dao.MovieDao;
import com.leo.models.Movie;
import com.leo.views.popup.MoviePopupView;

public class MoviePopupController {
  MovieDao movieDao = new MovieDao();
  JFrame previousView;

  public void add(MoviePopupView view, SuccessCallback sc, ErrorCallback ec) {
    if (previousView != null && previousView.isDisplayable()) {
      previousView.requestFocus();
      return;
    }
    previousView = view;
    view.setVisible(true);
    view.getBtnCancel().addActionListener(evt -> view.dispose());
    view.getBtnOK().addActionListener(evt -> {
      try {
        addMovie(view);
        view.dispose();
        view.showMessage("Added movie successfully!");
        sc.onSuccess();
      } catch (Exception ex) {
        ec.onError(ex);
      }
    });

  }

  public void addMovie(MoviePopupView view) throws Exception {
    Movie movie = new Movie();
    movie.setTitle(view.getTxtTitle().getText());
    movie.setCountry(view.getTxtCountry().getText());
    movie.setDurationTime(Integer.valueOf(view.getTxtDurationTime().getText()));
    movie.setPrice(Integer.valueOf(view.getTxtPrice().getText()));
    System.out.println(movie);
    movieDao.save(movie);
    return;
  }

  public void edit(MoviePopupView view, Movie movie, SuccessCallback sc, ErrorCallback ec) {
    if (previousView != null && previousView.isDisplayable()) {
      previousView.requestFocus();
      return;
    }
    previousView = view;
    view.setVisible(true);
    view.getBtnCancel().addActionListener(evt -> view.dispose());
    view.getTxtTitle().setText(movie.getTitle());
    view.getTxtCountry().setText(movie.getCountry());
    view.getTxtDurationTime().setText(Integer.toString(movie.getDurationTime()));
    view.getTxtPrice().setText(Integer.toString(movie.getPrice()));
    view.getBtnOK().setText("Cập nhật");
    view.getBtnOK().addActionListener(evt -> {
      try {
        editMovie(view, movie);
        view.dispose();
        view.showMessage("Edited movie successfully!");
        sc.onSuccess();
      } catch (Exception ex) {
        ec.onError(ex);
      }
    });
  }

  public boolean editMovie(MoviePopupView view, Movie movie) throws Exception {
    String title = view.getTxtTitle().getText(),
        country = view.getTxtCountry().getText();
    Integer durationTime = Integer.parseInt(view.getTxtDurationTime().getText()),
        price = Integer.parseInt(view.getTxtPrice().getText());
    if (title.isEmpty() || country.isEmpty()) {
      throw new Exception("Please complete all infomation!");
    }

    movie.setTitle(title);
    movie.setCountry(country);
    movie.setDurationTime(durationTime);
    movie.setPrice(price);
    movieDao.update(movie);
    return true;
  }
}
