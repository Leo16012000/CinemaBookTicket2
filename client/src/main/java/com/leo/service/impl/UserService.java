package com.leo.service.impl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.component.ServiceHandler;
import com.leo.dtos.LoginDto;
import com.leo.dtos.ResponseDto;
import com.leo.dtos.SearchDto;
import com.leo.models.User;
import com.leo.service.IUserService;
import com.leo.utils.Sockets;

public class UserService implements IUserService {
  private static IUserService instance;
  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  @Override
  public User getByUsername(String username) throws IOException {
    return serviceHandler.sendRequest(Sockets.getSocket(), "", username, User.class).getPayload();
  }

  @Override
  public User get(int selectedId) throws IOException {
    return serviceHandler.sendRequest(Sockets.getSocket(), "", selectedId, User.class).getPayload();

  }

  @Override
  public List<User> getAll() throws IOException {
    return serviceHandler
        .sendRequest(Sockets.getSocket(), "", null, new TypeReference<ResponseDto<List<User>>>() {
        }).getPayload();
  }

  @Override
  public List<User> searchByKey(String key, String term) throws IOException {
    return serviceHandler.sendRequest(Sockets.getSocket(), "", SearchDto.builder().key(key).value(term).build(),
        new TypeReference<ResponseDto<List<User>>>() {
        }).getPayload();
  }

  @Override
  public void update(User u) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "UPDATE_AUDITORIUM", u, Void.class);
  }

  @Override
  public void save(User u) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "CREATE_AUDITORIUM", u, Void.class);
  }

  @Override
  public void deleteByIds(List<Integer> selectedIds) throws IOException {
    serviceHandler.sendRequest(Sockets.getSocket(), "", selectedIds, Void.class);
  }

  @Override
  public User login(String username, String password) throws IOException {
    serviceHandler.setAuthentication(serviceHandler.sendRequest(Sockets.getSocket(), "",
        LoginDto.builder().username(username).password(password).build(), Object.class).getPayload());
    return getByUsername(username);
  }

  public static IUserService getInstance() {
    if (instance == null) {
      synchronized (UserService.class) {
        if (instance == null) {
          instance = new UserService();
        }
      }
    }
    return instance;
  }
}
