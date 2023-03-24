package com.leo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Transaction implements AutoCloseable {
  private ThreadLocal<Transaction> threadLocal;
  private Connection con;
  private int startCount;

  Transaction(Connection con, ThreadLocal<Transaction> threadLocal) throws SQLException {
    con.setAutoCommit(false);
    this.con = con;
    this.startCount = 0;
    this.threadLocal = threadLocal;
    threadLocal.set(this);
  }

  static Transaction of(Connection con, ThreadLocal<Transaction> threadLocal) throws SQLException {
    return new Transaction(con, threadLocal);
  }

  public void start() {
    startCount++;
  }

  public void rollback() throws SQLException {
    try {
      if (con.isClosed()) {
        return;
      }
      con.rollback();
    } finally {
      closeQuietly();
    }
  }

  public void commit() throws SQLException {
    if (startCount == 0) {
      throw new SQLException("Transaction not started");
    }
    if (startCount > 0) {
      startCount--;
    }
    if (startCount == 0) {
      try {
        if (con.isClosed()) {
          return;
        }
        con.commit();
      } finally {
        closeQuietly();
      }
    }
  }

  public <T> T query(TxFunction<Connection, ResultSet> query, TxFunction<ResultSet, T> mapper)
      throws SQLException {
    start();
    try (ResultSet rs = query.apply(con)) {
      T ret = null;
      if (rs.next()) {
        ret = mapper.apply(rs);
      }
      commit();
      return ret;
    } catch (SQLException e) {
      rollback();
      throw e;
    } catch (Exception e) {
      rollback();
      throw new RuntimeException(e);
    }
  }

  public <T> T func(TxFunction<Connection, T> fn)
      throws SQLException {
    start();
    try {
      T ret = fn.apply(con);
      commit();
      return ret;
    } catch (SQLException e) {
      rollback();
      throw e;
    } catch (Exception e) {
      rollback();
      throw new RuntimeException(e);
    }
  }

  public <T> List<T> queryList(
      TxFunction<Connection, ResultSet> query, TxFunction<ResultSet, T> mapper)
      throws SQLException {
    start();
    try (ResultSet rs = query.apply(con)) {
      List<T> rets = new ArrayList<>();
      while (rs.next()) {
        rets.add(mapper.apply(rs));
      }
      commit();
      return rets;
    } catch (SQLException e) {
      rollback();
      throw e;
    } catch (Exception e) {
      rollback();
      throw new RuntimeException(e);
    }
  }

  public void run(TxConsumer<Connection> consumer) throws SQLException {
    start();
    try {
      consumer.accept(con);
      commit();
    } catch (SQLException e) {
      rollback();
      throw e;
    } catch (Exception e) {
      rollback();
      throw new RuntimeException(e);
    }
  }

  public void closeQuietly() {
    try {
      close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() throws Exception {
    if (!con.isClosed()) {
      con.close();
      startCount = 0;
      threadLocal.remove();
    }
  }

  public interface TxFunction<T, K> {
    K apply(T arg) throws Exception;
  }

  public interface TxConsumer<T> {
    void accept(T arg) throws Exception;
  }
}
