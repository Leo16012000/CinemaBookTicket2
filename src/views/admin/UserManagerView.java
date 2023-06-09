package views.admin;

import javax.swing.DefaultComboBoxModel;

import models.User;

public class UserManagerView extends ManagerPaneView<User>{
	String[] list = {"id", "name"};
	
	public UserManagerView() {
		super();
		setTableModel();
		renderTable();
	}
	@Override 
	public void setTableModel() {
		tableModel.addColumn("ID");
		tableModel.addColumn("Full name");
		tableModel.addColumn("Username");
		tableModel.addColumn("Permission");
		tableModel.addColumn("Created at");
        this.getCboSearchField().setModel(new DefaultComboBoxModel(list));
	}
}
