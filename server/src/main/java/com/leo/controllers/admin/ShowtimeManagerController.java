package com.leo.controllers.admin;

import com.leo.dao.ShowtimeDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Showtime;

import java.util.List;

public class ShowtimeManagerController {
  private ShowtimeDao showtimeDao = ShowtimeDao.getInstance();

  public ShowtimeManagerController() {
  }

  public ResponseDto<Showtime> get(Integer id) {
    ResponseDto<Showtime> responseDto = new ResponseDto<>();
    try {
      Showtime showtime = showtimeDao.get(id);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get showtime successfully");
      responseDto.setPayload(showtime);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<ListDto<Showtime>> getAll() {
    ResponseDto<ListDto<Showtime>> responseDto = new ResponseDto<>();
    try {
      ListDto<Showtime> listDto = new ListDto<>();
      listDto.setList(showtimeDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list showtimes successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> add(Showtime showtime) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      showtimeDao.save(showtime);
      responseDto.setMessage("Add showtime successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> edit(Showtime showtime) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      showtimeDao.update(showtime);
      responseDto.setMessage("Edit showtime successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> delete(Showtime showtime) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      showtimeDao.delete(showtime);
      responseDto.setMessage("Delete showtime successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> deleteByIds(List<Integer> ids) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      showtimeDao.deleteByIds(ids);
      responseDto.setMessage("Delete showtimes successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<ListDto<Showtime>> search(String key, String value) {
    ResponseDto<ListDto<Showtime>> responseDto = new ResponseDto<>();
    try {
      ListDto<Showtime> listDto = new ListDto<>();
      List<Showtime> showtimes = showtimeDao.searchByKey(key, value);
      listDto.setList(showtimes);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list showtimes successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
}
