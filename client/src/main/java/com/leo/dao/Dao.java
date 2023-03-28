package com.leo.dao;

import com.leo.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public abstract class Dao<T> {
  private static final Logger logger = Logger.getLogger(Dao.class.getName());

  protected TransactionManager transactionManager;

  protected Dao(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  protected Dao() {
    this(TransactionManager.getInstance());
  }

  public abstract List<T> getAll() throws SQLException;

  public abstract T get(Integer id) throws SQLException;

  public abstract Integer save(T t) throws SQLException;

  public abstract void update(T t) throws SQLException;

  public abstract void delete(T t) throws SQLException;

  public abstract void deleteById(Integer id) throws SQLException;
}
