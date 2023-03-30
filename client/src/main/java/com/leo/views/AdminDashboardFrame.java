package com.leo.views;

import com.leo.views.admin.MenuItem;
import com.leo.utils.ErrorPopup;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Dang Tuan Anh
 */
public class AdminDashboardFrame extends javax.swing.JFrame {

  JPanel[] cards;
  ArrayList<MenuItem> menuItems = new ArrayList<>();

  public AdminDashboardFrame() {
    initComponents();
    setLocationRelativeTo(null);
    btnLogout.putClientProperty("JButton.buttonType", "roundRect");
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

  public JButton getBtnLogout() {
    return btnLogout;
  }

  public JLabel getLbName() {
    return lbName;
  }

  // Thêm dropdown menu
  public void addMenu(MenuItem... menu) {
    for (int i = 0; i < menu.length; i++) {
      MenuItem item = menu[i];
      menuItems.add(item);
      panelSidebar.add(item);
      ArrayList<MenuItem> subMenus = item.getSubMenu();
      for (MenuItem subMenu : subMenus) {
        addMenu(subMenu);
        subMenu.setVisible(false);
      }
    }
  }

  public ArrayList<MenuItem> getMenuItems() {
    return menuItems;
  }

  public void setCards(JPanel[] cards) {
    this.cards = cards;
    initLayout();
  }

  // Thêm các pane vào cardlayout
  public void initLayout() {
    panelLayout.removeAll();
    for (int i = 0; i < cards.length; i++) {
      panelLayout.add(cards[i]);
    }
    panelLayout.updateUI();
  }

  public JPanel getPanelLayout() {
    return panelLayout;
  }

  public JPanel getPanelSidebar() {
    return panelSidebar;
  }

  public void setPanel(JPanel panel) {
    for (JPanel card : cards) {
      card.setVisible(false);
    }
    panel.setVisible(true);
  }

  public JPanel getPanelHeader() {
    return panelHeader;
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    GridBagConstraints gridBagConstraints;

    panelLeft = new JPanel();
    panelHeader = new JPanel();
    lbName = new JLabel();
    btnLogout = new JButton();
    panelSidebar = new JPanel();
    panelLayout = new JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Admin Page");
    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    setResizable(false);

    panelLeft.setPreferredSize(new Dimension(200, 680));
    panelLeft.setLayout(new BorderLayout());

    panelHeader.setBackground(new Color(34, 153, 84));
    panelHeader.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
    panelHeader.setForeground(new Color(255, 255, 255));
    panelHeader.setPreferredSize(new Dimension(200, 50));
    panelHeader.setLayout(new GridBagLayout());

    lbName.setFont(new Font("Tahoma", 1, 14)); // NOI18N
    lbName.setForeground(new Color(255, 255, 255));
    lbName.setText("Admin");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.1;
    panelHeader.add(lbName, gridBagConstraints);

    btnLogout.setText("Exit");
    btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnLogout.setFocusable(false);
    btnLogout.setRequestFocusEnabled(false);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.1;
    panelHeader.add(btnLogout, gridBagConstraints);

    panelLeft.add(panelHeader, BorderLayout.PAGE_START);

    panelSidebar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelLeft.add(panelSidebar, BorderLayout.CENTER);

    getContentPane().add(panelLeft, BorderLayout.LINE_START);

    panelLayout.setMaximumSize(new Dimension(1000, 680));
    panelLayout.setMinimumSize(new Dimension(1000, 680));
    panelLayout.setPreferredSize(new Dimension(1008, 680));
    panelLayout.setLayout(new CardLayout());
    getContentPane().add(panelLayout, BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args
   *          the command line arguments
   */
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JButton btnLogout;
  private JLabel lbName;
  private JPanel panelHeader;
  private JPanel panelLayout;
  private JPanel panelLeft;
  private JPanel panelSidebar;
  // End of variables declaration//GEN-END:variables
}
