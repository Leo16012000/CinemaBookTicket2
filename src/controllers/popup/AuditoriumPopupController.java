package controllers.popup;

import javax.swing.JFrame;

import dao.AuditoriumDao;
import dao.UserDao;
import models.Auditorium;
import models.User;
import utils.UserPermission;
import views.popup.AuditoriumPopupView;
import views.popup.UserPopupView;

public class AuditoriumPopupController {
	AuditoriumDao auditoriumDao = new AuditoriumDao();
	JFrame previousView;
	
    public void add(AuditoriumPopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnOK().addActionListener(evt -> {
            try {
                addAuditorium(view);
                view.dispose();
                view.showMessage("Added auditorium successfully!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }
    
    public void addAuditorium(AuditoriumPopupView view) throws Exception {
        Auditorium auditorium = new Auditorium();
        auditorium.setAuditoriumNum(Integer.valueOf(view.getTxtNumber().getText()));
        auditorium.setSeatsRowNum(Integer.valueOf(view.getTxtRowsNum().getText()));
        auditorium.setSeatsColumnNum(Integer.valueOf(view.getTxtColumnsNum().getText()));
        System.out.println(auditorium);
        auditoriumDao.save(auditorium);
        return;
    }
    
    public void edit(AuditoriumPopupView view, Auditorium auditorium, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getTxtNumber().setText(Integer.toString(auditorium.getAuditoriumNum()));
        view.getTxtRowsNum().setText(Integer.toString(auditorium.getSeatsRowNum()));
        view.getTxtColumnsNum().setText(Integer.toString(auditorium.getSeatsColumnNum()));
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editAuditorium(view, auditorium);
                view.dispose();
                view.showMessage("Edited auditorium successfully!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }
    public boolean editAuditorium(AuditoriumPopupView view, Auditorium auditorium) throws Exception {
        Integer number = Integer.valueOf(view.getTxtNumber().getText()),
            rowsNum = Integer.valueOf(view.getTxtRowsNum().getText()),
            columnsNum = Integer.valueOf(view.getTxtColumnsNum().getText());
        if (number == null || rowsNum == null || columnsNum == null) {
            throw new Exception("Please complete all infomation!");
        }

        auditorium.setTitle(title);
        auditorium.setCountry(country);
        auditorium.setDurationTime(durationTime);
        auditorium.setPrice(price);
        auditoriumDao.update(auditorium);
        return true;
    }
}
