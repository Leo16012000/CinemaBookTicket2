package com.leo.views.popup;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.leo.utils.ErrorPopup;

public class MoviePopupView extends JFrame implements PopupView {
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JButton btnCancel;
  private JButton btnOK;
  private JComboBox<String> cboPermission;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JLabel lbTitle;
  private JTextField txtTitle;
  private JTextField txtCountry;
  private JTextField txtDurationTime;
  private JTextField txtPrice;

  // End of variables declaration//GEN-END:variables
  public MoviePopupView() {
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
    jLabel6 = new JLabel();
    jPanel2 = new JPanel();
    jPanel1 = new JPanel();
    lbTitle = new JLabel();
    txtCountry = new JTextField();
    txtDurationTime = new JTextField();
    txtTitle = new JTextField();
    txtPrice = new JTextField();
    cboPermission = new JComboBox<>();
    jPanel3 = new JPanel();
    btnOK = new JButton();
    btnCancel = new JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(350, 400));
    setResizable(false);

    jPanel2.setPreferredSize(new java.awt.Dimension(350, 50));
    jPanel2.setLayout(new java.awt.GridBagLayout());

    lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    lbTitle.setText("Add movie");
    jPanel2.add(lbTitle, new GridBagConstraints());

    getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

    jPanel1.setLayout(new java.awt.GridBagLayout());

    jLabel1.setText("Title");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel1, gridBagConstraints);
    jLabel2.setText("Country");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel2, gridBagConstraints);
    jLabel3.setText("Duration time");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel3, gridBagConstraints);
    jLabel4.setText("Price");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel4, gridBagConstraints);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtTitle, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtCountry, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtDurationTime, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtPrice, gridBagConstraints);

    getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    jPanel3.setPreferredSize(new java.awt.Dimension(350, 75));
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

    getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

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

  public JComboBox<String> getCboPermission() {
    return cboPermission;
  }

  public JTextField getTxtTitle() {
    return txtTitle;
  }

  public JTextField getTxtName() {
    return txtTitle;
  }

  public JTextField getTxtCountry() {
    return txtCountry;
  }

  public JTextField getTxtDurationTime() {
    return txtDurationTime;
  }

  public JTextField getTxtPrice() {
    return txtPrice;
  }

  public JLabel getLbTitle() {
    return lbTitle;
  }
}
