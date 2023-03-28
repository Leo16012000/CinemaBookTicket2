package com.leo.views;

import com.leo.utils.ErrorPopup;

import javax.swing.*;

public class LoginView extends JFrame {
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JButton btnLogin;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JPanel jPanel1;
  private JPanel jPanel3;
  private JPanel jPanel4;
  private JPanel jPanel5;
  private JLabel lblForgotPassword;
  private JLabel lblRegister, lblAccessAsGuest;
  private JPasswordField txtPassword;
  private JTextField txtUsername;

  // End of variables declaration//GEN-END:variables
  public LoginView() {
    initComponents();
    setLocationRelativeTo(null);
    getRootPane().setDefaultButton(btnLogin);
  }

  public void showError(String message) {
    ErrorPopup.show(new Exception(message));
  }

  public void showError(Exception e) {
    ErrorPopup.show(e);
  }

  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  public JPasswordField getTxtPassword() {
    return txtPassword;
  }

  public JTextField getTxtUsername() {
    return txtUsername;
  }

  public JButton getBtnLogin() {
    return btnLogin;
  }

  public JLabel getLblForgotPassword() {
    return lblForgotPassword;
  }

  public JLabel getLblRegister() {
    return lblRegister;
  }

  public JLabel getLblAccessAsGuest() {
    return lblAccessAsGuest;
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    jPanel4 = new JPanel();
    jLabel3 = new JLabel();
    jPanel5 = new JPanel();
    jPanel1 = new JPanel();
    lblRegister = new JLabel();
    lblAccessAsGuest = new JLabel();
    btnLogin = new JButton();
    jPanel3 = new JPanel();
    txtPassword = new JPasswordField();
    jLabel1 = new JLabel();
    jLabel2 = new JLabel();
    lblForgotPassword = new JLabel();
    txtUsername = new JTextField();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Đăng Nhập Hệ Thống");
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    setResizable(false);

    jPanel4.setBackground(new java.awt.Color(87, 184, 70));
    jPanel4.setMaximumSize(new java.awt.Dimension(400, 75));
    jPanel4.setMinimumSize(new java.awt.Dimension(400, 75));
    jPanel4.setPreferredSize(new java.awt.Dimension(400, 75));

    jLabel3.setBackground(new java.awt.Color(255, 255, 255));
    jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Đăng Nhập");

    GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup().addGap(157, 157, 157).addComponent(jLabel3)
            .addContainerGap(156, Short.MAX_VALUE)));
    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup().addGap(28, 28, 28).addComponent(jLabel3)
            .addContainerGap(25, Short.MAX_VALUE)));

    getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

    jPanel5.setLayout(new java.awt.BorderLayout());

    jPanel1.setPreferredSize(new java.awt.Dimension(414, 100));

    lblRegister.setForeground(new java.awt.Color(0, 132, 255));
    lblRegister.setText("Dont have an account?");

    lblAccessAsGuest.setForeground(new java.awt.Color(0, 132, 255));
    lblAccessAsGuest.setText("Login as guest");

    btnLogin.setBackground(new java.awt.Color(87, 184, 70));
    btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    btnLogin.setForeground(new java.awt.Color(255, 255, 255));
    btnLogin.setText("Login");

    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout
        .setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegister).addComponent(lblAccessAsGuest).addComponent(btnLogin))
                .addGap(143, 143, 143)));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnLogin)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lblAccessAsGuest)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lblRegister)
            .addContainerGap()));

    jPanel5.add(jPanel1, java.awt.BorderLayout.PAGE_END);

    jPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    jPanel3.setLayout(new java.awt.GridBagLayout());

    txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        txtPasswordKeyPressed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
    gridBagConstraints.weightx = 0.1;
    gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
    jPanel3.add(txtPassword, gridBagConstraints);

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jLabel1.setText("Username:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
    jPanel3.add(jLabel1, gridBagConstraints);

    jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jLabel2.setText("Password:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
    jPanel3.add(jLabel2, gridBagConstraints);

    lblForgotPassword.setText("Forgot password?");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel3.add(lblForgotPassword, gridBagConstraints);

    txtUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.1;
    gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
    jPanel3.add(txtUsername, gridBagConstraints);

    jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);

    getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtPasswordKeyPressed
    // TODO add your handling code here:
  }// GEN-LAST:event_txtPasswordKeyPressed

}
