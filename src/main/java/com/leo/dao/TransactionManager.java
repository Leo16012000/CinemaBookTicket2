package com.leo.dao;

import com.leo.utils.LoadConfig;
import com.sun.javafx.binding.StringFormatter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
  private static ThreadLocal<Transaction> THREAD_LOCAL = new ThreadLocal<>();
  private static TransactionManager INSTANCE;
  private static final LoadConfig cfg = LoadConfig.getInstance();
  private static HikariConfig hikariCfg = new HikariConfig();
  private static HikariDataSource hikariDs;

  public TransactionManager() {
    String connectProperty = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String host = cfg.getProperty("database.host"),
        port = cfg.getProperty("database.port"),
        user = cfg.getProperty("database.username"),
        password = cfg.getProperty("database.password"),
        name = cfg.getProperty("database.name");
    String url = String.format(
        "jdbc:%s://%s:%s/%s?%s",
        cfg.getProperty("database.jdbc"), host, port, name, connectProperty);
    hikariCfg.setJdbcUrl(url);
    hikariCfg.setUsername(user);
    hikariCfg.setPassword(password);
    hikariCfg.addDataSourceProperty("cachePrepStmts", "true");
    hikariCfg.addDataSourceProperty("prepStmtCacheSize", "250");
    hikariCfg.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    hikariCfg.setMaximumPoolSize(10);
    hikariDs = new HikariDataSource(hikariCfg);
  }

  private static Connection getConnection() throws SQLException {
    return hikariDs.getConnection();
  }

  public Transaction getTransaction() throws SQLException {
    Transaction tx = THREAD_LOCAL.get();
    if (tx != null) {
      return tx;
    }
    Connection con = getConnection();
    tx = Transaction.of(con, THREAD_LOCAL);
    return tx;
  }

  public static TransactionManager getInstance() {
    if (INSTANCE == null) {
      synchronized (TransactionManager.class) {
        if (INSTANCE == null) {
          INSTANCE = new TransactionManager();
        }
      }
    }
    return INSTANCE;
  }

  public static interface SqlFunction<T, K> {
    K apply(T arg) throws Exception;
  }

  public static interface TxConsumer<T> {
    void accept(T arg) throws Exception;
  }

  public static interface TxSupplier<T> {
    T get() throws Exception;
  }
}
