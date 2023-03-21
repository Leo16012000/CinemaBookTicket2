package com.leo.views.popup;

import java.awt.GridBagConstraints;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.leo.controllers.popup.AuditoriumPopupController;
import com.leo.models.Auditorium;
import com.leo.utils.DocumentBinder;
import com.leo.utils.ErrorPopup;
import com.leo.views.admin.AuditoriumManagerView;

public class AuditoriumPopupView extends AbstractCrudPopupView<Auditorium, AuditoriumPopupController> {
  private JButton btnCancel;
  private JButton btnOK;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JLabel lbTitle;
  private JTextField txtNumber;
  private JTextField txtRowsNum;
  private JTextField txtColumnsNum;
  private AuditoriumManagerView managerView;

  public AuditoriumPopupView(AuditoriumManagerView managerView) {
    super();
    this.managerView = managerView;
  }

  public AuditoriumPopupView(Auditorium auditorium, AuditoriumManagerView managerView) {
    super(auditorium);
    this.managerView = managerView;
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    GridBagConstraints gridBagConstraints;
    jLabel1 = new JLabel();
    jLabel2 = new JLabel();
    jLabel3 = new JLabel();
    jPanel1 = new JPanel();
    jPanel2 = new JPanel();
    lbTitle = new JLabel();
    txtNumber = new JTextField();
    txtRowsNum = new JTextField();
    txtColumnsNum = new JTextField();
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

  public JTextField getTxtRowsNum() {
    return txtRowsNum;
  }

  public JTextField getTxtColumnsNum() {
    return txtColumnsNum;
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

  public JLabel getLbTitle() {
    return lbTitle;
  }

  @Override
  public void bindModel(Auditorium model) {
    this.model = Optional.ofNullable(model).orElse(new Auditorium());
  }

  @Override
  public void refresh() {
    txtNumber.setText(String.valueOf(model.getAuditoriumNum()));
    txtRowsNum.setText(String.valueOf(model.getSeatsRowNum()));
    txtColumnsNum.setText(String.valueOf(model.getSeatsColumnNum()));
  }

  @Override
  public void init() {
    this.editingModel = new Auditorium();
    this.controller = new AuditoriumPopupController();
    initComponents();
    txtNumber.getDocument().addDocumentListener(DocumentBinder.bind(editingModel::setAuditoriumNum, s -> DocumentBinder.toInt(s, 0)));
    txtRowsNum.getDocument().addDocumentListener(DocumentBinder.bind(editingModel::setSeatsRowNum, s -> DocumentBinder.toInt(s, 0)));
    txtColumnsNum.getDocument()
        .addDocumentListener(DocumentBinder.bind(editingModel::setSeatsColumnNum, s -> DocumentBinder.toInt(s, 0)));
    addEvent();
  }

  @Override
  protected void onSuccess() {
    if (!isUpdating) {
      showMessage("Added auditorium successfully!");
    } else {
      showMessage("Updated auditorium successfully!");
    }
    close();
    managerView.refresh();
  }

  @Override
  protected void onError(Exception e) {
    showError(e);
  }

  @Override
  protected void onConfirm() throws Exception {
    btnOK.setText("In progress");
    if (!isUpdating) {
      controller.addAuditorium(editingModel);
    } else {
      controller.editAuditorium(editingModel);
    }
  }

  @Override
  protected void addEvent() {
    btnOK.addActionListener(e -> confirm());
    btnCancel.addActionListener(e -> cancel());
  }
}
