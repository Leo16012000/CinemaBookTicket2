package com.leo.component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {
  private static LoadConfig instance;
  private Properties properties = new Properties();
  private String configPath;

  public LoadConfig(String configPath) {
    this.configPath = configPath;
    readConfig();
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public Properties getProperties() {
    return properties;
  }

  private void readConfig() {
    try (InputStream inputStream = new FileInputStream(configPath)) {
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static LoadConfig getInstance() {
    if (instance == null) {
      synchronized (LoadConfig.class) {
        if (instance == null) {
          instance = new LoadConfig("./src/main/resources/config.properties");
        }
      }
    }
    return instance;
  }
}
