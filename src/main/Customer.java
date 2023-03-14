package main;

import controllers.LoginController;
import dao.UserDao;
import models.User;
import views.LoginView;

public class Customer {
	
	public static void main(String[] args) {
      UserDao userDao = new UserDao();
      try {
          javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
          System.out.println("Khởi tạo look and feel thành công!");
          new LoginController(new LoginView());
          Runtime.getRuntime().addShutdownHook(new Thread());
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

}
