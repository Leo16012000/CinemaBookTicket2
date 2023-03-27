package com.leo.controllers.admin;

import com.leo.Client;
import com.leo.controllers.ManagerController;
import com.leo.controllers.popup.AuditoriumPopupController;
import com.leo.dao.AuditoriumDao;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Auditorium;
import com.leo.views.popup.AuditoriumPopupView;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

public class AuditoriumManagerController extends ManagerController {
  private AuditoriumDao auditoriumDao;
  private AuditoriumPopupController popupController;
  private static Logger logger = Client.getLogger();

  public AuditoriumManagerController() {
    super();
    auditoriumDao = AuditoriumDao.getInstance();
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
  public ResponseDto actionDelete() {
    int selectedIds[] = view.getSelectedIds();
    try {
      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete auditorium",
          ERROR_MESSAGE) != YES_OPTION) {
        return null;
      }
      for (int i = 0; i < selectedIds.length; i++) {
        logger.info("Delete auditorium id: ", selectedIds[i]);
        Auditorium auditorium = new Auditorium();
        auditorium.setId(selectedIds[i]);
        RequestDto payload = new RequestDto("DELETE_AUDITORIUM", auditorium);
        ResponseDto responseDto = payload.sendRequest();
        if(responseDto.getStatus().equals("FAILURE")) throw new Exception(responseDto.getMessage());
        updateData();
        return responseDto;
      }
    } catch (Exception e) {
//      view.showError(e);
    }
    return null;
  }

  @Override
  public void updateData() {
    try {
      List<Auditorium> auditoriums = auditoriumDao.getAll();
      System.out.println(auditoriums);
      view.setTableData(auditoriums);
    } catch (Exception e) {
      view.showError(e);
    }
  }

  @Override
  public void actionSearch() {
    try {
      List<Auditorium> auditoriums = auditoriumDao.searchByKey(
          view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
      view.setTableData(auditoriums);
    } catch (Exception e) {
      view.showError(e);
    }
  }
}
