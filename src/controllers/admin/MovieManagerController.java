package controllers.admin;

import javax.swing.JOptionPane;

import controllers.ManagerController;
import controllers.popup.MoviePopupController;
import controllers.popup.UserPopupController;
import dao.MovieDao;
import dao.UserDao;
import models.Movie;
import models.User;
import utils.UserPermission;
import views.popup.MoviePopupView;
import views.popup.UserPopupView;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;
import java.util.Arrays;

public class MovieManagerController extends ManagerController{
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
	            if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete movie", ERROR_MESSAGE) != YES_OPTION) {
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
	            System.out.println(movies2);
	            view.setTableData(movies);
	        } catch (Exception e) {
	            view.showError(e);
	        }
	    }

	    @Override
	    public void actionSearch() {
	        try {
	            ArrayList<Movie> movies = movieDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
	            view.setTableData(movies);
	        } catch (Exception e) {
	            view.showError(e);
	        }
	    }
}
