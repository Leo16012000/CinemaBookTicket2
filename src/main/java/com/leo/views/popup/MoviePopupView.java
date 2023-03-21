package com.leo.views.popup;

import java.awt.GridBagConstraints;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.leo.controllers.popup.MoviePopupController;
import com.leo.models.Movie;
import com.leo.utils.DocumentBinder;
import com.leo.utils.ErrorPopup;

public class MoviePopupView extends AbstractCrudPopupView<Movie, MoviePopupController> implements PopupView {
  private JButton btnCancel;
  private JButton btnOK;
  private JComboBox<String> cboPermission;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JLabel lbTitle;
  private JTextField txtTitle;
  private JTextField txtCountry;
  private JTextField txtDurationTime;
  private JTextField txtPrice;

  public MoviePopupView() {
    super();
  }

  public MoviePopupView(Movie movie) {
    super(movie);
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    GridBagConstraints gridBagConstraints;
    jLabel1 = new JLabel();
    jLabel2 = new JLabel();
    jLabel3 = new JLabel();
    jLabel4 = new JLabel();
    jPanel2 = new JPanel();
    jPanel1 = new JPanel();
    lbTitle = new JLabel();
    txtCountry = new JTextField();
    txtDurationTime = new JTextField();
    txtTitle = new JTextField();
    txtPrice = new JTextField();
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
    lbTitle.setText("Add movie");
    jPanel2.add(lbTitle, new java.awt.GridBagConstraints());

    getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

    jPanel1.setLayout(new java.awt.GridBagLayout());

    jLabel1.setText("Title");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel1, gridBagConstraints);
    jLabel2.setText("Country");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel2, gridBagConstraints);
    jLabel3.setText("Duration time");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel3, gridBagConstraints);
    jLabel4.setText("Price");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(jLabel4, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtTitle, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtCountry, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtDurationTime, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 136;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    jPanel1.add(txtPrice, gridBagConstraints);

    getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    jPanel3.setPreferredSize(new java.awt.Dimension(350, 75));
    jPanel3.setLayout(new java.awt.GridBagLayout());

    btnOK.setText("Thêm");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.1;
    jPanel3.add(btnOK, gridBagConstraints);

    btnCancel.setText("Hủy");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.1;
    jPanel3.add(btnCancel, gridBagConstraints);

    getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

    pack();
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

  public JTextField getTxtTitle() {
    return txtTitle;
  }

  public JTextField getTxtName() {
    return txtTitle;
  }

  public JTextField getTxtCountry() {
    return txtCountry;
  }

  public JTextField getTxtDurationTime() {
    return txtDurationTime;
  }

  public JTextField getTxtPrice() {
    return txtPrice;
  }

  public JLabel getLbTitle() {
    return lbTitle;
  }

  @Override
  public void bindModel(Movie model) {
    this.model = Optional.ofNullable(model).orElse(new Movie());
  }

  @Override
  public void refresh() {
    txtTitle.setText(model.getTitle());
    txtCountry.setText(model.getCountry());
    txtDurationTime.setText(Optional.ofNullable(model.getDurationTime()).map(item -> Integer.toString(item)).orElse(""));
    txtPrice.setText(Optional.ofNullable(model.getPrice()).map(item -> Integer.toString(item)).orElse(""));
  }

  @Override
  public void init() {
    this.editingModel = new Movie();
    this.controller = new MoviePopupController();
    initComponents();
    txtTitle.getDocument().addDocumentListener(DocumentBinder.bind(editingModel::setTitle));
    txtCountry.getDocument().addDocumentListener(DocumentBinder.bind(editingModel::setCountry));
    txtDurationTime.getDocument()
        .addDocumentListener(DocumentBinder.bind(editingModel::setDurationTime, s -> DocumentBinder.toInt(s, 0)));
    txtPrice.getDocument().addDocumentListener(DocumentBinder.bind(editingModel::setPrice, s -> DocumentBinder.toInt(s, 0)));
  }

  @Override
  public void confirm() throws SQLException {
    super.confirm(editingModel -> {
      if (!isUpdating) {
        return controller.addMovie(editingModel);
      } else {
        return controller.editMovie(editingModel);
      }
    }, 
    () -> btnOK.setText("In progress"),
    movie -> {
      if (!isUpdating) {
        showMessage("Added movie successfully!");
      } else {
        showMessage("Updated movie successfully!");
      }
      refresh();
    }, this::showError);
    super.confirm(editingModel -> {
      if (!isUpdating) {
        return controller.addMovie(editingModel);
      } else {
        return controller.editMovie(editingModel);
      }
    }, movie -> {
      if (!isUpdating) {
        showMessage("Added movie successfully!");
      } else {
        showMessage("Updated movie successfully!");
      }
    }, this::showError);
  }
}
