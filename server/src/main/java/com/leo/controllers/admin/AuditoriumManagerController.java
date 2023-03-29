package com.leo.controllers.admin;

import java.util.List;

import com.leo.dao.AuditoriumDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Auditorium;

import java.util.List;

public class AuditoriumManagerController {
  private AuditoriumDao auditoriumDao = new AuditoriumDao();

  public AuditoriumManagerController() {
  }

  public ResponseDto<ListDto<Auditorium>> getAll() {
    ResponseDto<ListDto<Auditorium>> responseDto = new ResponseDto<>();
    try {
      ListDto<Auditorium> listDto = new ListDto();
      listDto.setList(auditoriumDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list auditoriums successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> add(Auditorium auditorium) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      auditoriumDao.save(auditorium);
      responseDto.setMessage("Add auditorium successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> edit(Auditorium auditorium) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      auditoriumDao.update(auditorium);
      responseDto.setMessage("Edit auditorium successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> delete(Auditorium auditorium) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      auditoriumDao.delete(auditorium);
      responseDto.setMessage("Delete auditorium successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<ListDto<Auditorium>> search(String key, String value) {
    ResponseDto<ListDto<Auditorium>> responseDto = new ResponseDto<>();
    try {
      ListDto<Auditorium> listDto = new ListDto<>();
      List<Auditorium> auditoriums = auditoriumDao.searchByKey(key, value);
      listDto.setList(auditoriums);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list auditoriums successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
}
