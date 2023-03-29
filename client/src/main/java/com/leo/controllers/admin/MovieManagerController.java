package com.leo.controllers.admin;

import javax.swing.JOptionPane;

import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.MoviePopupController;
import com.leo.models.Movie;
import com.leo.service.IMovieService;
import com.leo.service.impl.MovieService;
import com.leo.views.popup.MoviePopupView;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.List;

public class MovieManagerController extends ManagerController {
  private IMovieService movieService = MovieService.getInstance();
  private MoviePopupController popupController;

  public MovieManagerController() {
    super();
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
      Movie movie = movieService.get(selectedId);
      if (movie == null) {
        throw new Exception("Invalid movie selected");
      }
      popupController.edit(new MoviePopupView(), movie, this::updateData, view::showError);

    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void actionDelete() {
    List<Integer> selectedIds = view.getSelectedIds();
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete movie",
          ERROR_MESSAGE) != YES_OPTION) {
        return;
      }
      movieService.deleteByIds(selectedIds);
      updateData();
    } catch (Exception e) {
      view.showError(e);
    }
    return;
  }

  @Override
  public void updateData() {
    try {
      List<Movie> movies = movieService.getAll();
      System.out.println(movies);
      view.setTableData(movies);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      List<Movie> movies = movieService.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
          String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(movies);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
