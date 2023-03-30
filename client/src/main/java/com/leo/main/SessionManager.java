package com.leo.main;

import com.leo.models.User;

import com.leo.utils.ServiceHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionManager {
  private static SessionManager instance;
  private User session;
  private ServiceHandler serviceHandler = ServiceHandler.getInstance();

  public void clear() {
    serviceHandler.setAuthentication(null);
    session = null;
  }

  public static SessionManager getInstance() {
    if (instance == null) {
      synchronized (SessionManager.class) {
        if (instance == null) {
          instance = new SessionManager();
        }
      }
    }
    return instance;
  }
}
