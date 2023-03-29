package com.leo.controllers.admin;

import com.leo.dao.UserDao;
import com.leo.dtos.ListDto;
import com.leo.dtos.ResponseDto;
import com.leo.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserManagerController {
  private UserDao userDao = UserDao.getInstance();

  public UserManagerController() {
  }

  public ResponseDto<User> get(Integer id) {
    ResponseDto<User> responseDto = new ResponseDto<>();
    try {
      User user = userDao.get(id);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get user successfully");
      responseDto.setPayload(user);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<User> getByUsername(String username) throws SQLException {
    ResponseDto<User> responseDto = new ResponseDto<>();
    try {
      User user = userDao.getByUsername(username);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get user by username successfully");
      responseDto.setPayload(user);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<ListDto<User>> getAll() {
    ResponseDto<ListDto<User>> responseDto = new ResponseDto<>();
    try {
      ListDto<User> listDto = new ListDto<>();
      listDto.setList(userDao.getAll());
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list users successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> add(User user) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      userDao.save(user);
      responseDto.setMessage("Add user successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> edit(User user) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      userDao.update(user);
      responseDto.setMessage("Edit user successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<Void> delete(User user) {
    ResponseDto<Void> responseDto = new ResponseDto<>();
    try {
      userDao.delete(user);
      responseDto.setMessage("Delete user successfully");
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
      userDao.deleteByIds(ids);
      responseDto.setMessage("Delete users successfully");
      responseDto.setStatus("SUCCESS");
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }

  public ResponseDto<ListDto<User>> search(String key, String value) {
    ResponseDto<ListDto<User>> responseDto = new ResponseDto<>();
    try {
      ListDto<User> listDto = new ListDto<>();
      List<User> users = userDao.searchByKey(key, value);
      listDto.setList(users);
      listDto.setTotalCount(listDto.getList().size());
      responseDto.setPayload(listDto);
      responseDto.setStatus("SUCCESS");
      responseDto.setMessage("Get list users successfully");
      responseDto.setPayload(listDto);
      return responseDto;
    } catch (Exception e) {
      responseDto.setMessage(e.getMessage());
      responseDto.setStatus("FAILURE");
      return responseDto;
    }
  }
}
