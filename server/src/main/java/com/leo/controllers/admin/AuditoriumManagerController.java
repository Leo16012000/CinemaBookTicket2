package com.leo.controllers.admin;

import com.leo.dao.AuditoriumDao;
import com.leo.dtos.ResponseDto;
import com.leo.models.Auditorium;

public class AuditoriumManagerController {
  private AuditoriumDao auditoriumDao = new AuditoriumDao();

  public AuditoriumManagerController() {

  }

  public ResponseDto actionAdd(Auditorium auditorium){
    ResponseDto responseDto = new ResponseDto();
    try{
      Integer id = auditoriumDao.save(auditorium);
      responseDto.setMessage("Add auditorium successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

//  public void actionEdit() {
//    try {
//      int selectedId = view.getSelectedId();
//      if (selectedId < 0) {
//        throw new Exception("Chooose the one to edit");
//      }
//      Auditorium auditorium = auditoriumDao.get(selectedId);
//      if (auditorium == null) {
//        throw new Exception("Invalid auditorium selected");
//      }
//      popupController.edit(new AuditoriumPopupView(), auditorium, this::updateData, view::showError);
//
//    } catch (Exception e) {
//      view.showError(e);
//    }
//  }
//
//  public void actionDelete() {
//    int selectedIds[] = view.getSelectedIds();
//    try {
//      if (JOptionPane.showConfirmDialog(null, "Delete multiple records?", "Delete auditorium",
//          ERROR_MESSAGE) != YES_OPTION) {
//        return;
//      }
//      for (int i = 0; i < selectedIds.length; i++) {
//        auditoriumDao.deleteById(selectedIds[i]);
//        updateData();
//      }
//    } catch (Exception e) {
//      view.showError(e);
//    }
//  }
//
//  public void actionSearch() {
//    try {
//      List<Auditorium> auditoriums = auditoriumDao.searchByKey(
//          view.getCboSearchField().getSelectedItem().toString(), String.valueOf(view.getTxtSearch().getText()));
//      view.setTableData(auditoriums);
//    } catch (Exception e) {
//      view.showError(e);
//    }
//  }
}
