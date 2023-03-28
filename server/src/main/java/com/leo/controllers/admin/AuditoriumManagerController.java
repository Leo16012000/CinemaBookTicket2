package com.leo.controllers.admin;

import com.leo.dao.AuditoriumDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Auditorium;

import java.util.List;

public class AuditoriumManagerController {
  private AuditoriumDao auditoriumDao = new AuditoriumDao();

  public AuditoriumManagerController() {}

  public ResponseDto getAll() {
    ResponseDto<ListDto<Auditorium>> responseDto = new ResponseDto();
    try{
      ListDto<Auditorium> listDto = new ListDto();
      listDto.setList(auditoriumDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list auditoriums successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
  public ResponseDto add(Auditorium auditorium){
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

  public ResponseDto edit(Auditorium auditorium) {
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

  public ResponseDto delete(Auditorium auditorium) {
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

  public ResponseDto search(String key, String value) {
    ResponseDto<ListDto<Auditorium>> responseDto = new ResponseDto();
    try{
      ListDto<Auditorium> listDto = new ListDto();
      List<Auditorium> auditoriums = auditoriumDao.searchByKey(key, value);
      listDto.setList(auditoriums);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list auditoriums successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
}
