package com.leo.views;

import com.leo.main.SessionManager;
import com.leo.utils.ErrorPopup;

import lombok.Getter;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@Getter
public class HeaderPanel extends JPanel {
  private JButton btnLogin = new JButton("Login");
  private JButton btnLogout = new JButton("Logout");
  private JLabel lblName;
  private JButton btnBack;

  /**
   * Create the panel.
   */
  public HeaderPanel() {
    setLayout(new FlowLayout(FlowLayout.RIGHT));
    Icon icon = new ImageIcon("/../resources/images/back.png");
    this.btnBack = new JButton("Back");
    btnBack.setBounds(45, 65, 80, 25);

    btnLogin.setBounds(45, 25, 80, 25);
    btnLogout.setBounds(45, 25, 80, 25);

    lblName = new JLabel(
        SessionManager.getInstance().getSession() != null ? SessionManager.getInstance().getSession().getName()
            : "Guest");
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
    add(SessionManager.getInstance().getSession() != null ? btnLogout : btnLogin, gc);

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

  public void addBackActionListener(ActionListener actionListener) {
    btnBack.addActionListener(actionListener);
  }
}
