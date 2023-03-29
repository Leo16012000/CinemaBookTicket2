package com.leo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class ErrorPopup {
  private static final Logger logger = LogManager.getLogger(ErrorPopup.class);

  public static void show(Exception e) {
    try {
      logger.error(e.getMessage());

      // JOptionPane.showMessageDialog(null, e.getMessage(), "Something happened",
      // JOptionPane.ERROR_MESSAGE);
      int val = JOptionPane.showConfirmDialog(null, e.getMessage() + "!\nSee details?", "Error",
          JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
      if (val == JOptionPane.YES_OPTION) {
        String errorSummary = "";
        int numElement = 0;
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
          if (numElement >= 20) {
            errorSummary += "\t...  more";
          } else {
            errorSummary += "\tat " + stackTraceElement;
            errorSummary += "\n";
          }
        }
        logger.error(errorSummary);
        JOptionPane.showMessageDialog(null, errorSummary, "Có lỗi xảy ra", JOptionPane.ERROR_MESSAGE);
      }
    } catch (Exception ex) {

    }

  }
}
