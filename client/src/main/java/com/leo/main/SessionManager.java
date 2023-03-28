package com.leo.main;

import com.leo.models.User;

public class SessionManager {

  public static User session;

  public SessionManager() {
  }

  public static User getSession() {
    return session;
  }

  public static void setSession(User session) {
    SessionManager.session = session;
  }

  public static void clear() {
    session = null;
  }
}
