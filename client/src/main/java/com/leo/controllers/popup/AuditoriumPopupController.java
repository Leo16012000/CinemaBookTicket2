package com.leo.controllers.popup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.component.ServiceHandler;
import com.leo.dtos.ResponseDto;
import com.leo.models.Auditorium;
import com.leo.utils.Convert;
import com.leo.utils.Sockets;
import com.leo.views.popup.AuditoriumPopupView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class AuditoriumPopupController {
  private JFrame previousView;
  private Convert convert = new Convert();
  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  private static Logger logger = LogManager.getLogger(AuditoriumPopupController.class);

  public void add(AuditoriumPopupView view, SuccessCallback sc, ErrorCallback ec) {
    if (previousView != null && previousView.isDisplayable()) {
      previousView.requestFocus();
      return;
    }
    previousView = view;
    view.setVisible(true);
    view.getBtnCancel().addActionListener(evt -> view.dispose());
    view.getBtnOK().addActionListener(evt -> {
      ResponseDto<Void> responseDto = addAuditorium(view);
      if (responseDto != null && responseDto.getStatus().equals("SUCCESS")) {
        view.dispose();
        view.showMessage("Added auditorium successfully!");
        sc.onSuccess();
      } else {
        view.showError("Something wrong!!!");
      }
      // try {
      // view.dispose();
      // view.showMessage("Added auditorium successfully!");
      // sc.onSuccess();
      // } catch (Exception ex) {
      // ec.onError(ex);
      // }
    });

  }

  public ResponseDto<Void> addAuditorium(AuditoriumPopupView view) {
    try {
      Auditorium auditorium = new Auditorium();
      auditorium.setAuditoriumNum(Integer.valueOf(view.getTxtNumber().getText()));
      auditorium.setSeatsRowNum(Integer.valueOf(view.getTxtRowsNum().getText()));
      auditorium.setSeatsColumnNum(Integer.valueOf(view.getTxtColumnsNum().getText()));
      logger.info("Add auditorium: ", auditorium);
      return serviceHandler.sendRequest(Sockets.getSocket(), "CREATE_AUDITORIUM", auditorium,
          new TypeReference<ResponseDto<Void>>() {
          });
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
      return null;
    }
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

  public void editAuditorium(AuditoriumPopupView view, Auditorium auditorium) throws Exception {
    Integer number = Integer.valueOf(view.getTxtNumber().getText()),
        rowsNum = Integer.valueOf(view.getTxtRowsNum().getText()),
        columnsNum = Integer.valueOf(view.getTxtColumnsNum().getText());
    if (number == null || rowsNum == null || columnsNum == null) {
      throw new Exception("Please complete all infomation!");
    }

    auditorium.setAuditoriumNum(number);
    auditorium.setSeatsRowNum(rowsNum);
    auditorium.setSeatsColumnNum(columnsNum);
    logger.info("Edit auditorium: ", auditorium);
    serviceHandler.sendRequest(Sockets.getSocket(), "UPDATE_AUDITORIUM", auditorium,
        new TypeReference<ResponseDto<Void>>() {
        });
    return;
  }
}
