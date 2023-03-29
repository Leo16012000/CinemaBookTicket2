package com.leo.controllers.admin;

import com.leo.dao.ShowtimeDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.Showtime;

import java.util.List;

public class ShowtimeManagerController {
  private ShowtimeDao showtimeDao = new ShowtimeDao();

  public ShowtimeManagerController() {}

  public ResponseDto getAll() {
    ResponseDto<ListDto<Showtime>> responseDto = new ResponseDto();
    try{
      ListDto<Showtime> listDto = new ListDto();
      listDto.setList(showtimeDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list showtimes successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
  public ResponseDto add(Showtime showtime){
    ResponseDto responseDto = new ResponseDto();
    try{
      Integer id = showtimeDao.save(showtime);
      responseDto.setMessage("Add showtime successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto edit(Showtime showtime) {
    ResponseDto responseDto = new ResponseDto();
    try{
      showtimeDao.update(showtime);
      responseDto.setMessage("Edit showtime successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto delete(Showtime showtime) {
    ResponseDto responseDto = new ResponseDto();
    try{
      showtimeDao.delete(showtime);
      responseDto.setMessage("Delete showtime successfully");
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
    ResponseDto<ListDto<Showtime>> responseDto = new ResponseDto();
    try{
      ListDto<Showtime> listDto = new ListDto();
      List<Showtime> showtimes = showtimeDao.searchByKey(key, value);
      listDto.setList(showtimes);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list showtimes successfully");
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
