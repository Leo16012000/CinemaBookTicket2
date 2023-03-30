package com.leo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {
  private String configPath;
  private static LoadConfig instance;
  private Properties properties = new Properties();

  public LoadConfig(String configPath) throws FileNotFoundException, IOException {
    this.configPath = configPath;
    readConfig();
  }

  public static LoadConfig getInstance() throws FileNotFoundException, IOException {
    if (instance == null) {
      synchronized (LoadConfig.class) {
        if (instance == null) {
          instance = new LoadConfig("./src/main/resources/config.properties");
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

  private void readConfig() throws FileNotFoundException, IOException {
    try (InputStream inputStream = new FileInputStream(configPath)) {
      properties.load(inputStream);
    }
  }
}
