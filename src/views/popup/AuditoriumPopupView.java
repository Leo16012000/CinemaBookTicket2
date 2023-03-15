package views.popup;

import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.ManagerController;
import dao.UserDao;
import utils.ErrorPopup;

public class AuditoriumPopupView extends JFrame implements PopupView {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JButton btnCancel;
	private JButton btnOK;
	private JComboBox<String> cboPermission;
	private JLabel jLabel1;
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
	private JTextField txtNumber;
	private JTextField txtRowsNum;
	private JTextField txtColumnsNum;

	// End of variables declaration//GEN-END:variables
	public AuditoriumPopupView() {
		initComponents();
		setLocationRelativeTo(null);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		GridBagConstraints gridBagConstraints;
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jPanel2 = new JPanel();
		jPanel1 = new JPanel();
		lbTitle = new JLabel();
		txtNumber = new JTextField();
		txtRowsNum = new JTextField();
		txtColumnsNum = new JTextField();
		cboPermission = new JComboBox<>();
		jPanel3 = new javax.swing.JPanel();
		btnOK = new JButton();
		btnCancel = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(350, 400));
		setResizable(false);

		jPanel2.setPreferredSize(new java.awt.Dimension(350, 50));
		jPanel2.setLayout(new java.awt.GridBagLayout());

		lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lbTitle.setText("Add auditorium");
		jPanel2.add(lbTitle, new java.awt.GridBagConstraints());

		getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

		jPanel1.setLayout(new java.awt.GridBagLayout());

		jLabel1.setText("Auditorium number");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 136;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(jLabel1, gridBagConstraints);
		jLabel2.setText("Number of rows");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 136;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(jLabel2, gridBagConstraints);
		jLabel3.setText("Number of columns");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 136;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(jLabel3, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 136;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(txtNumber, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 136;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(txtRowsNum, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 136;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel1.add(txtColumnsNum, gridBagConstraints);

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

	public JTextField getTxtNumber() {
		return txtNumber;
	}

	public void setTxtNumber(JTextField txtNumber) {
		this.txtNumber = txtNumber;
	}

	public JTextField getTxtRowsNum() {
		return txtRowsNum;
	}

	public void setTxtRowsNum(JTextField txtRowsNum) {
		this.txtRowsNum = txtRowsNum;
	}

	public JTextField getTxtColumnsNum() {
		return txtColumnsNum;
	}

	public void setTxtColumnsNum(JTextField txtColumnsNum) {
		this.txtColumnsNum = txtColumnsNum;
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

	public JLabel getLbTitle() {
		return lbTitle;
	}
}
