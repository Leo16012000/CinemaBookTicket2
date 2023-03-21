package com.leo.controllers;

import com.leo.views.admin.MenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 *
 * @author Dang Tuan Anh
 *         Quản lý menu sidebar
 */
public class SidebarController {

  private JPanel panelSidebar;
  private List<MenuItem> menuItems = new ArrayList<>();
  private MenuItem activeMenuItem = null; // item vừa chọn

  public interface MenuBarEvent {
    public abstract void onSelectMenuItem(MenuItem item);
  }

  public SidebarController() {
  }

  public SidebarController(JPanel panelSidebar) {
    this.panelSidebar = panelSidebar;
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  public JPanel getPanelSidebar() {
    return panelSidebar;
  }

  public void setPanelSidebar(JPanel panelSidebar) {
    this.panelSidebar = panelSidebar;
  }

  public List<MenuItem> getMenuItems() {
    return menuItems;
  }

  // Thêm dropdown menu
  public void addMenu(MenuItem... menu) {
    for (int i = 0; i < menu.length; i++) {
      MenuItem item = menu[i];
      menuItems.add(item);
      item.setActive(false);
      panelSidebar.add(item);
      List<MenuItem> subMenus = item.getSubMenu();
      for (MenuItem subMenu : subMenus) {
        addMenu(subMenu);
        subMenu.setVisible(false);
      }
    }
  }

  public void addMenuEvent(MenuBarEvent mbe) { // Thêm event mỗi khi click vào 1 item
    for (MenuItem menuItem : menuItems) {
      menuItem.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          if (!menuItem.equals(activeMenuItem)) { // Nếu click lại thì bỏ qua
            mbe.onSelectMenuItem(menuItem);
          }
          setMenu(menuItem);
        }
      });
    }
  }

  public void renderMenu() {
    for (MenuItem menuItem : menuItems) {
      MenuItem parrent = menuItem.getParentMenu();
      if (parrent == null) { // Nếu là menu cha trên cùng
        menuItem.setVisible(true);
      } else if (parrent.isActive()) {// Nếu cha được chọn thì show con ra
        menuItem.setVisible(true);
      } else if (menuItem.isActive()) {// Nếu đang chọn thì show ra
        menuItem.setVisible(true);
      } else {
        menuItem.setVisible(false);
      }
    }
  }

  private void closePreviosMenu(MenuItem previousItem) {// Đóng menu cũ
    MenuItem parrent = previousItem.getParentMenu();
    previousItem.setActive(false);
    while (parrent != null) {
      parrent.setActive(false);
      parrent = parrent.getParentMenu();
    }
  }

  public void setMenu(MenuItem item) {// Chọn menu
    boolean isActive = item.isActive();
    if (activeMenuItem != null) {
      closePreviosMenu(activeMenuItem);
    }
    MenuItem parrent = item.getParentMenu();
    while (parrent != null) {
      parrent.setActive(true);
      parrent = parrent.getParentMenu();
    }
    item.setActive(!isActive);
    activeMenuItem = item;
    renderMenu();
  }

}
