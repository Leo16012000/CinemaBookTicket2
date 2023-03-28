package com.leo.service;

import java.util.List;

import com.leo.models.User;

public interface IUserService {
  User getByUsername(String username);

  User get(int selectedId);

  void deleteById(int i);

  List<User> getAll();

  List<User> searchByKey(String string, String valueOf);

  void update(User u);

  void save(User u);
}
