package com.leo.views;

import com.leo.main.SessionManager;
import com.leo.utils.ErrorPopup;

import java.awt.GridBagConstraints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
  private JButton btnLogin = new JButton("Login");
  private JButton btnLogout = new JButton("Logout");
  private JLabel lblName;

  /**
   * Create the panel.
   */
  public HeaderPanel() {
    Icon icon = new ImageIcon("/../resources/images/back.png");
    JButton btnBack = new JButton("Back");
    btnBack.setBounds(45, 65, 80, 25);

    btnLogin.setBounds(45, 25, 80, 25);
    btnLogout.setBounds(45, 25, 80, 25);

    lblName = new JLabel(
        SessionManager.getSession() != null ? SessionManager.getSession().getName() : "Guest");
    lblName.setBounds(0, 30, 70, 15);

    GridBagConstraints gc = new GridBagConstraints();
    // First column ////////////////////
    gc.anchor = GridBagConstraints.LINE_END;
    gc.weightx = 0.5;
    gc.weighty = 0.5;

    gc.gridx = 0;
    gc.gridy = 0;

    add(lblName, gc);

    gc.gridx = 0;
    gc.gridy = 1;
    add(SessionManager.getSession() != null ? btnLogout : btnLogin, gc);

    gc.gridx = 0;
    gc.gridy = 2;
    add(btnBack, gc);
  }

  public JLabel getLblName() {
    return lblName;
  }

  public void setLblName(JLabel lblName) {
    this.lblName = lblName;
  }

  public JButton getBtnLogout() {
    return btnLogout;
  }

  public void setBtnLogout(JButton btnLogout) {
    this.btnLogout = btnLogout;
  }

  public JButton getBtnLogin() {
    return btnLogin;
  }

  public void setBtnLogin(JButton btnLogin) {
    this.btnLogin = btnLogin;
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
}
