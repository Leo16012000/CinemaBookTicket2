package com.leo.controllers.admin;

import com.leo.dao.UserDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.User;

import java.util.List;

public class UserManagerController {
  private UserDao userDao = new UserDao();

  public UserManagerController() {}

  public ResponseDto getAll() {
    ResponseDto<ListDto<User>> responseDto = new ResponseDto();
    try{
      ListDto<User> listDto = new ListDto();
      listDto.setList(userDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list users successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
  public ResponseDto add(User user){
    ResponseDto responseDto = new ResponseDto();
    try{
      Integer id = userDao.save(user);
      responseDto.setMessage("Add user successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto edit(User user) {
    ResponseDto responseDto = new ResponseDto();
    try{
      userDao.update(user);
      responseDto.setMessage("Edit user successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    }
    catch (Exception e){
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto delete(User user) {
    ResponseDto responseDto = new ResponseDto();
    try{
      userDao.delete(user);
      responseDto.setMessage("Delete user successfully");
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
    ResponseDto<ListDto<User>> responseDto = new ResponseDto();
    try{
      ListDto<User> listDto = new ListDto();
      List<User> users = userDao.searchByKey(key, value);
      listDto.setList(users);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list users successfully");
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
