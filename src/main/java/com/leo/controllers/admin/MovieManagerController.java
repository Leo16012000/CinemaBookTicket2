package com.leo.controllers.admin;

import javax.swing.JOptionPane;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.MoviePopupController;
import com.leo.dao.MovieDao;
import com.leo.models.Movie;
import com.leo.views.popup.MoviePopupView;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

public class MovieManagerController extends ManagerController {
  private MovieDao movieDao;
  private MoviePopupController popupController;

  public MovieManagerController() {
    super();
    movieDao = new MovieDao();
    popupController = new MoviePopupController();
  }

  @Override
  public void actionAdd() {
    popupController.add(new MoviePopupView(), this::updateData, view::showError);
  }

  @Override
  public void actionEdit() {
    try {
      int selectedId = view.getSelectedId();
      if (selectedId < 0) {
        throw new Exception("Chooose the one to edit");
      }
      Movie movie = movieDao.get(selectedId);
      if (movie == null) {
        throw new Exception("Invalid movie selected");
      }
      popupController.edit(new MoviePopupView(), movie, this::updateData, view::showError);

    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionDelete() {
    int selectedIds[] = view.getSelectedIds();
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete movie",
          ERROR_MESSAGE) != YES_OPTION) {
        return;
      }
      for (int i = 0; i < selectedIds.length; i++) {
        movieDao.deleteById(selectedIds[i]);
        updateData();
      }
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void updateData() {
    try {
      ArrayList<Movie> movies = movieDao.getAll();
      ArrayList<Movie> movies2 = MovieDao.getInstance().getAll();
      System.out.println(movies);
      view.setTableData(movies);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      ArrayList<Movie> movies = movieDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
          String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(movies);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
