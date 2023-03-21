package com.leo.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leo.utils.SidebarColor;

/**
 *
 * @author Dang Tuan Anh
 */
public class MenuItem extends JPanel {
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JLabel lbIcon;
  private JLabel lbMenuName;
  private JLabel lbOpen;

  private List<MenuItem> subMenu = new ArrayList<>();
  private MenuItem parentMenu = null;
  private String id;
  private int level;
  public boolean active; // Biến kiểm tra xem có đang chọn không
  // Color inactiveChildColor = Color.decode("#D5DBDB"), inactiveParrentColor =
  // Color.decode("#D5DBDB"), activeParrentColor = Color.decode("#F5CBA7"),
  // activeChildColor = Color.decode("#EAFAF1"); // Màu tương ứng

  public MenuItem(String id, String menuName) {
    initComponents();
    this.id = id;
    lbMenuName.setText(menuName);
  }

  public List<MenuItem> getSubMenu() {
    return subMenu;
  }

  public void addSubMenu(MenuItem item) {
    item.setParentMenu(this);
    item.setLevel(this.level + 1);
    this.subMenu.add(item);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MenuItem getParentMenu() {
    return parentMenu;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setParentMenu(MenuItem parentMenu) {
    this.parentMenu = parentMenu;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
    if (active) {
      setBackground(SidebarColor.getActiveColor(level));
    } else {
      setBackground(SidebarColor.getInactiveColor(level));
    }
    if (!hasSubMenu()) {
      lbOpen.setVisible(false);
      this.updateUI();
    }
    // if (!active) {
    // if (hasSubMenu() || getParentMenu() == null) {
    // setBackground(inactiveParrentColor);
    // } else {
    // setBackground(inactiveChildColor);
    // }
    // } else if (hasSubMenu() || getParentMenu() == null) {
    // setBackground(activeParrentColor);
    // } else {
    // setBackground(activeChildColor);
    // }
  }

  // Kiểm tra xem có menu con không
  public boolean hasSubMenu() {
    return !subMenu.isEmpty();
  }

  public boolean equals(MenuItem obj) {
    // if (this == obj) {
    // return true;
    // }
    // if (obj == null) {
    // return true;
    // }
    // return obj.getId() == this.getId();
    return this == obj;
  }

  public boolean hasChild(MenuItem obj) {
    if (obj == null) {
      return false;
    }
    if (obj.equals(this)) {
      return true;
    }
    return hasChild(obj.getParentMenu());
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + Objects.hashCode(this.id);
    return hash;
  }

  @Override
  public String toString() {
    return "MenuItem{" + "id=" + id + ", active=" + active + '}';
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new JPanel();
    lbIcon = new JLabel();
    jPanel3 = new JPanel();
    lbOpen = new JLabel();
    jPanel2 = new JPanel();
    lbMenuName = new JLabel();

    setBackground(new Color(255, 255, 255));
    setBorder(BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
    setPreferredSize(new Dimension(200, 45));
    setLayout(new BorderLayout());

    jPanel1.setMaximumSize(new Dimension(45, 45));
    jPanel1.setMinimumSize(new Dimension(45, 45));
    jPanel1.setOpaque(false);
    jPanel1.setPreferredSize(new Dimension(45, 45));

    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 25,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 25,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)));

    add(jPanel1, java.awt.BorderLayout.WEST);

    jPanel3.setMaximumSize(new java.awt.Dimension(45, 45));
    jPanel3.setMinimumSize(new java.awt.Dimension(45, 45));
    jPanel3.setOpaque(false);

    GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbOpen, GroupLayout.PREFERRED_SIZE, 25,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)));
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbOpen, GroupLayout.PREFERRED_SIZE, 25,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)));

    add(jPanel3, java.awt.BorderLayout.EAST);

    jPanel2.setOpaque(false);
    jPanel2.setPreferredSize(new java.awt.Dimension(155, 45));

    lbMenuName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbMenuName.setText("Menu Name");

    GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbMenuName)
                .addContainerGap(36, Short.MAX_VALUE)));
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbMenuName)
                .addContainerGap(13, Short.MAX_VALUE)));

    add(jPanel2, java.awt.BorderLayout.CENTER);
  }
}
