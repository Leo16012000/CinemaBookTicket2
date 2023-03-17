package com.leo.dao;

import java.sql.SQLException;
import java.util.List;

public abstract class Dao<T> {

  protected TransactionManager transactionManager;

  protected Dao(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  protected Dao() {
    this(TransactionManager.getInstance());
  }

  public abstract List<T> getAll() throws SQLException;

  public abstract T get(int id) throws SQLException;

  public abstract void save(T t) throws SQLException;

  public abstract void update(T t) throws SQLException;

  public abstract void delete(T t) throws SQLException;

  public abstract void deleteById(int id) throws SQLException;
}
