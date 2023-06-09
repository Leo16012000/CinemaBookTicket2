package controllers.admin;

import javax.swing.JOptionPane;

import controllers.ManagerController;
import controllers.popup.AuditoriumPopupController;
import controllers.popup.UserPopupController;
import dao.AuditoriumDao;
import dao.UserDao;
import models.Auditorium;
import models.User;
import utils.UserPermission;
import views.popup.AuditoriumPopupView;
import views.popup.UserPopupView;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;
import java.util.Arrays;

public class AuditoriumManagerController extends ManagerController{
	private AuditoriumDao auditoriumDao;
	private AuditoriumPopupController popupController;
	
	public AuditoriumManagerController() {
		super();
		auditoriumDao = new AuditoriumDao();
		popupController = new AuditoriumPopupController();
	}
	
	  @Override
	    public void actionAdd() {
	        popupController.add(new AuditoriumPopupView(), this::updateData, view::showError);
	    }

	    @Override
	    public void actionEdit() {
	        try {
	            int selectedId = view.getSelectedId();
	            if (selectedId < 0) {
	                throw new Exception("Chooose the one to edit");
	            }
	            Auditorium auditorium = auditoriumDao.get(selectedId);
	            if (auditorium == null) {
	                throw new Exception("Invalid auditorium selected");
	            }
	            popupController.edit(new AuditoriumPopupView(), auditorium, this::updateData, view::showError);

	        } catch (Exception e) {
	            view.showError(e);
	        }
	    }

	    @Override
	    public void actionDelete() {
	        int selectedIds[] = view.getSelectedIds();
	        try {
	            if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete auditorium", ERROR_MESSAGE) != YES_OPTION) {
	                return;
	            }
	            for (int i = 0; i < selectedIds.length; i++) {
	                auditoriumDao.deleteById(selectedIds[i]);
	                updateData();
	            }
	        } catch (Exception e) {
	            view.showError(e);
	        }
	    }

	    @Override
	    public void updateData() {
	        try {
	            ArrayList<Auditorium> auditoriums = auditoriumDao.getAll();
	            System.out.println(auditoriums);
	            view.setTableData(auditoriums);
	        } catch (Exception e) {
	            view.showError(e);
	        }
	    }

	    @Override
	    public void actionSearch() {
	        try {
	            ArrayList<Auditorium> auditoriums = auditoriumDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
	            view.setTableData(auditoriums);
	        } catch (Exception e) {
	            view.showError(e);
	        }
	    }
}
