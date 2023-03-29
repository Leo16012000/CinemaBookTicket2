package com.leo.service;

import java.io.IOException;
import java.util.List;

import com.leo.models.User;

public interface IUserService {
  User getByUsername(String username) throws IOException;

  User get(int selectedId) throws IOException;

  List<User> getAll() throws IOException;

  List<User> searchByKey(String key, String term) throws IOException;

  void update(User u) throws IOException;

  void save(User u) throws IOException;

  void deleteByIds(List<Integer> selectedIds) throws IOException;

  User login(String username, String password) throws IOException;
}
