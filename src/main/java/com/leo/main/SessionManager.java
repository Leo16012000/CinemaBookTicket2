package com.leo.main;

import com.leo.dao.SessionDao;
import com.leo.models.Session;
import com.leo.models.User;

import java.sql.SQLException;
import java.sql.Timestamp;

public class SessionManager {

  public static Session session;
  static SessionDao sessionDao = SessionDao.getInstance();

  public SessionManager() {
  }

  public static Session getSession() {
    return session;
  }

  public static void setSession(Session session) {
    SessionManager.session = session;
  }

  public static void create(User user) throws SQLException {
    if (user == null) {
      throw new SQLException("Invalid user!");
    }
    Session ss = new Session();
    ss.setUser(user);
    ss.setMessage("login");
    ss.setStartTime(new Timestamp(System.currentTimeMillis()));
    ss.setEndTime(new Timestamp(System.currentTimeMillis() + 7200000));
    sessionDao.save(ss);
    Session sss = sessionDao.getLast(user.getId());
    setSession(sss);
  }

  public static void update() throws SQLException {
    if (session == null) {
      throw new SQLException("You need to log in!");
    }
    session.setMessage("logout");
    session.setEndTime(new Timestamp(System.currentTimeMillis()));
    sessionDao.update(session);
    setSession(null);
  }

}
