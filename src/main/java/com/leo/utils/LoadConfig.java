package com.leo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {

  private static final String CONFIG_PATH = "classpath://resources/config.properties";
  private static LoadConfig intanse;
  private Properties properties = new Properties();

  public LoadConfig() {
    readConfig();
  }

  public static LoadConfig getIntanse() {
    if (intanse == null) {
      intanse = new LoadConfig();
    }
    return intanse;
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public Properties getProperties() {
    return properties;
  }

  private void readConfig() {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream("/resources/config.properties");
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
