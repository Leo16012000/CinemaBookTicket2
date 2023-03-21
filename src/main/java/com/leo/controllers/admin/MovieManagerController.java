package com.leo.controllers.admin;

import javax.swing.JOptionPane;

import com.leo.controllers.ManagerController;
import com.leo.dao.MovieDao;
import com.leo.models.Movie;
import com.leo.views.admin.MovieManagerView;
import com.leo.views.popup.MoviePopupView;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;
import java.util.List;

public class MovieManagerController extends ManagerController<Movie, MovieManagerView> {
  private MovieDao movieDao;
  private MoviePopupView popup;

  public MovieManagerController(MovieManagerView view) {
    super(view);
    this.movieDao = MovieDao.getInstance();
  }

  @Override
  public void actionAdd() {
    if (popup != null && popup.isVisible()) {
      return;
    }
    this.popup = new MoviePopupView();
    popup.registerErrorHandler(view::showError);
    popup.registerConfirmHandler(it -> view.updateData());
    popup.popup();
  }

  @Override
  public void actionEdit() {
    if (popup != null && popup.isVisible()) {
      return;
    }
    try {
      int selectedId = view.getSelectedId();
      if (selectedId < 0) {
        throw new Exception("Chooose the one to edit");
      }
      Movie movie = movieDao.get(selectedId);
      if (movie == null) {
        throw new Exception("Invalid movie selected");
      }
      this.popup = new MoviePopupView();
      popup.registerErrorHandler(view::showError);
      popup.registerConfirmHandler(view::updateData);
      popup.popup();
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
      }
      view.refresh();
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public List<Movie> getAllData() {
    try {
      return movieDao.getAll();
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }

  @Override
  public List<Movie> search(String key, String word) {
    try {
      return movieDao.searchByKey(key, word);
    } catch (Exception e) {
      view.showError(e);
      return new ArrayList<>();
    }
  }
}
