package com.leo.controllers.popup;

import com.leo.Client;
import com.leo.controllers.Payload;
import com.leo.dao.AuditoriumDao;
import com.leo.models.Auditorium;
import com.leo.utils.Convert;
import com.leo.views.popup.AuditoriumPopupView;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class AuditoriumPopupController {
  private AuditoriumDao auditoriumDao = AuditoriumDao.getInstance();
  private JFrame previousView;
  private Convert convert = new Convert();

  private static Logger logger = Client.getLogger();

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
    try {
      logger.info("Add auditorium: ", auditorium);
      Payload payload = new Payload("CREATE_AUDITORIUM", convert.objectToXML(auditorium));
      payload.sendPayload();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
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

    auditorium.setAuditoriumNum(number);
    auditorium.setSeatsRowNum(rowsNum);
    auditorium.setSeatsColumnNum(columnsNum);
    auditoriumDao.update(auditorium);
    return true;
  }
}
