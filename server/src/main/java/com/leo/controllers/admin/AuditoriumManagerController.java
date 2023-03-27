package com.leo.controllers.admin;

import com.leo.dao.AuditoriumDao;

import java.util.LinkedHashMap;

public class AuditoriumManagerController {
  private AuditoriumDao auditoriumDao = new AuditoriumDao();

  public AuditoriumManagerController() {

  }

  public String actionAdd(LinkedHashMap object){
    try{
      Integer id = auditoriumDao.save(object);
      String res = "SUCCESS:<id>" + id + "<id>";
      return res;
    }
    catch (Exception e){
      return e.getMessage();
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
