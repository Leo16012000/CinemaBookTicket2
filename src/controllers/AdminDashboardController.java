package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.admin.MovieManagerController;
import controllers.admin.UserManagerController;
import main.SessionManager;
import models.User;
import views.AdminDashboardFrame;
import views.LoginView;
import views.HeaderPanel;
import views.admin.ManagerPaneView;
import views.admin.MenuItem;
import views.admin.MovieManagerView;
import views.admin.UserManagerView;

public class AdminDashboardController{
	private AdminDashboardFrame view;
	private ManagerController userManagerController;
	private ManagerController movieManagerController;
//	movieManagerController = new MovieManagerController();
	
	private JPanel headerPanel;
	
    private ManagerPaneView userManagerView, movieManagerView; // View
//            movieManagerView = new MovieManagerView();
    private JPanel[] cards;
    private SidebarController sidebarController = new SidebarController();
    public AdminDashboardController() throws SQLException {
    	view = new AdminDashboardFrame();
    	
    	userManagerView = new UserManagerView();
    	movieManagerView = new MovieManagerView();
    	
    	userManagerController = new UserManagerController();
    	movieManagerController = new MovieManagerController();
    	
    	headerPanel = view.getPanelHeader();
        sidebarController.setPanelSidebar(view.getPanelSidebar());
    	cards = new JPanel[]{movieManagerView, userManagerView};
    	view.setVisible(true);
    	initMenu();
    	addEvent();
    	User session = SessionManager.getSession().getUser();
    	if(session!= null) {
    		view.getLbName().setText(session.getName());
    	}
    	view.setCards(cards);
    	view.setPanel(headerPanel);
    }
    
    public AdminDashboardFrame getView() {
        return view;
    }

    public void setView(AdminDashboardFrame view) {
        this.view = view;
        sidebarController.setPanelSidebar(view.getPanelSidebar());
    }
    
    private void initMenu() {
        MenuItem menuUser = new MenuItem("USER", "User Management");
        MenuItem menuMovie = new MenuItem("MOVIE", "Movie Management");
        sidebarController.addMenu(menuUser);
        sidebarController.addMenu(menuMovie);
        sidebarController.addMenuEvent(this::onMenuChange);
    }
    
    // Tạo sự kiện
    private void addEvent() {
    	view.getBtnLogout().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
            	System.out.println("logout click");
                int confirm = JOptionPane.showConfirmDialog(view, "Do you want to log out?");
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                try {
                    SessionManager.update();// Log out
                } catch (SQLException ex) {
                    view.showError(ex);
                }
                view.dispose();
                new LoginController(new LoginView());
            }
        });
    }

    private void onMenuChange(MenuItem item) {
    	System.out.println("menu change" + item.getId());
        switch (item.getId()) {
            case "USER":
                view.setPanel(userManagerView);
                userManagerController.setView(userManagerView);
                userManagerController.updateData();
                break;
            case "MOVIE":
                view.setPanel(movieManagerView);
                movieManagerController.setView(movieManagerView);
                movieManagerController.updateData();
                break;
        }
    }
}


