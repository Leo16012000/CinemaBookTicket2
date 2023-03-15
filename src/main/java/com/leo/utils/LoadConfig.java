package com.leo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {

  private static final String CONFIG_PATH = "src/main/resources/config.properties";
  private static LoadConfig instance;
  private Properties properties = new Properties();

  public LoadConfig() {
    readConfig();
  }

  public static LoadConfig getInstance() {
    if (instance == null) {
      synchronized (LoadConfig.class) {
        if (instance == null) {
          instance = new LoadConfig();
        }
      }
    }
    return instance;
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public Properties getProperties() {
    return properties;
  }

  private void readConfig() {
    try (InputStream inputStream = new FileInputStream(CONFIG_PATH)) {
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
