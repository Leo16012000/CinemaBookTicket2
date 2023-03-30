package com.leo.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadConfigs {
  private static LoadConfig instance;

  public static LoadConfig getInstance() throws FileNotFoundException, IOException {
    if (instance == null) {
      synchronized (LoadConfig.class) {
        if (instance == null) {
          try {
            instance = new LoadConfig("server/src/main/resources/config.properties");
          } catch (FileNotFoundException e) {
            instance = new LoadConfig(System.getenv("SERVER_CONFIG_PATH"));
          }
        }
      }
    }
    return instance;
  }
}
