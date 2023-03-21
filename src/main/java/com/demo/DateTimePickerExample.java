package com.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DateTimePickerExample {
  public static void main(String[] args) {
    JFrame frame = new JFrame("DateTimePicker Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton button = new JButton("Pick a date and time");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime selectedDateTime = (LocalDateTime) JOptionPane.showInputDialog(
            frame,
            "Select a date and time:",
            "DateTimePicker",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            currentDateTime);
        if (selectedDateTime != null) {
          System.out.println("Selected datetime: " + selectedDateTime);
        }
      }
    });

    frame.getContentPane().add(button);
    frame.pack();
    frame.setVisible(true);
  }
}
