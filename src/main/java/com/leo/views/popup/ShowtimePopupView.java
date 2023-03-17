package com.leo.views.popup;

import com.leo.components.TimePicker;
import com.leo.utils.ErrorPopup;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ShowtimePopupView extends JFrame implements PopupView {
  private JButton btnCancel, btnOK;
  private JLabel jLabel1, jLabel2,jLabel3,jLabel4,jLabel5;
  private JPanel jPanel1, jPanel2, jPanel3;
  private JLabel lbTitle;

  private JSpinner startTimeSpinner, endTimeSpinner;
  private JDateChooser jDate;

  public JComboBox getCboMovieName() {
    return cboMovieName;
  }

  public JComboBox getCboAuditoriumNumber() {
    return cboAuditoriumNumber;
  }

  private JComboBox cboMovieName, cboAuditoriumNumber;

  // End of variables declaration//GEN-END:variables
  public ShowtimePopupView() {
    initComponents();
    setLocationRelativeTo(null);
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    GridBagConstraints gridBagConstraints;
    jLabel1 = new JLabel();
    jLabel2 = new JLabel();
    jLabel3 = new JLabel();
    jLabel4 = new JLabel();
    jLabel5 = new JLabel();
    jPanel2 = new JPanel();
    jPanel1 = new JPanel();
    lbTitle = new JLabel();
    jDate = new JDateChooser();
    cboMovieName = new JComboBox();
    cboAuditoriumNumber = new JComboBox();
    jPanel3 = new JPanel();
    btnOK = new JButton();
    btnCancel = new JButton();
    startTimeSpinner = new TimePicker().getTimeSpinner();
    endTimeSpinner = new TimePicker().getTimeSpinner();

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setMinimumSize(new Dimension(350, 400));
    setResizable(false);

    jPanel2.setPreferredSize(new Dimension(350, 50));
    jPanel2.setLayout(new GridBagLayout());

    lbTitle.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    lbTitle.setText("Add showtime");
    jPanel2.add(lbTitle, new GridBagConstraints());

    getContentPane().add(jPanel2, BorderLayout.PAGE_START);

    jPanel1.setLayout(new GridBagLayout());

    jLabel1.setText("Start time");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);

    jPanel1.add(jLabel1, gridBagConstraints);
    jLabel2.setText("End time");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(jLabel2, gridBagConstraints);
    jLabel3.setText("Date");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(jLabel3, gridBagConstraints);
    jLabel4.setText("Auditorium");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(jLabel4, gridBagConstraints);
    jLabel5.setText("Movie");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(jLabel5, gridBagConstraints);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
//    gridBagConstraints.fill = GridBagConstraints.BOTH;
//    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    gridBagConstraints.gridwidth = 1;
    jPanel1.add(startTimeSpinner, gridBagConstraints);

    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
//    gridBagConstraints.fill = GridBagConstraints.BOTH;
//    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(endTimeSpinner, gridBagConstraints);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(jDate, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(cboAuditoriumNumber, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel1.add(cboMovieName, gridBagConstraints);

    getContentPane().add(jPanel1, BorderLayout.CENTER);

    jPanel3.setPreferredSize(new Dimension(350, 75));
    jPanel3.setLayout(new java.awt.GridBagLayout());

    btnOK.setText("Thêm");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.1;
    jPanel3.add(btnOK, gridBagConstraints);

    btnCancel.setText("Hủy");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.1;
    jPanel3.add(btnCancel, gridBagConstraints);

    getContentPane().add(jPanel3, BorderLayout.PAGE_END);

    pack();
  }

  public void showError(String message) {
    ErrorPopup.show(new Exception(message));
  }

  public void showError(Exception e) {
    ErrorPopup.show(e);
  }

  public void showMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  public JButton getBtnOK() {
    return btnOK;
  }

  public JButton getBtnCancel() {
    return btnCancel;
  }
  public JLabel getLbTitle() {
    return lbTitle;
  }
}
