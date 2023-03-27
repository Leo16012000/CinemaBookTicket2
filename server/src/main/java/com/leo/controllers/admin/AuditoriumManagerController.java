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

  public ResponseDto actionEdit(Auditorium auditorium) {
    ResponseDto responseDto = new ResponseDto();
    try{
      auditoriumDao.update(auditorium);
      responseDto.setMessage("Edit auditorium successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto actionDelete(Auditorium auditorium) {
    ResponseDto responseDto = new ResponseDto();
    try{
      auditoriumDao.delete(auditorium);
      responseDto.setMessage("Delete auditorium successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
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
