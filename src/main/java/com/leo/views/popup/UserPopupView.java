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

public class UserPopupView extends JFrame implements PopupView {
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JButton btnCancel;
  private JButton btnOK;
  private JComboBox<String> cboPermission;
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

  public JLabel getLbTitle() {
    return lbTitle;
  }

  public void setLbTitle(javax.swing.JLabel lbTitle) {
    this.lbTitle = lbTitle;
  }

  private JTextField txtName;
  private JTextField txtPassword;
  private JTextField txtUsername;

  // End of variables declaration//GEN-END:variables
  public UserPopupView() {
    initComponents();
    setLocationRelativeTo(null);
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    GridBagConstraints gridBagConstraints;

    jPanel2 = new JPanel();
    lbTitle = new JLabel();
    jPanel1 = new JPanel();
    jLabel2 = new JLabel();
    txtPassword = new JTextField();
    jLabel6 = new JLabel();
    jLabel4 = new JLabel();
    jLabel5 = new JLabel();
    txtName = new JTextField();
    txtUsername = new JTextField();
    jLabel3 = new JLabel();
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
    lbTitle.setText("Add user");
    jPanel2.add(lbTitle, new java.awt.GridBagConstraints());

    getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

    jPanel1.setLayout(new java.awt.GridBagLayout());

    jLabel2.setText("Username:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel2, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtPassword, gridBagConstraints);

    jLabel6.setText("Fullname:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel6, gridBagConstraints);

    jLabel5.setText("Role:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel5, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtName, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtUsername, gridBagConstraints);

    jLabel3.setText("Password:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel3, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 110;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(cboPermission, gridBagConstraints);

    getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    jPanel3.setPreferredSize(new java.awt.Dimension(350, 75));
    jPanel3.setLayout(new java.awt.GridBagLayout());

    btnOK.setText("Add");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.1;
    jPanel3.add(btnOK, gridBagConstraints);

    btnCancel.setText("Cancel");
    gridBagConstraints = new java.awt.GridBagConstraints();
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

  public JTextField getTxtName() {
    return txtName;
  }

  public JTextField getTxtPassword() {
    return txtPassword;
  }

  public JTextField getTxtUsername() {
    return txtUsername;
  }
}
