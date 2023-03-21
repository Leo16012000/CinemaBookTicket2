package com.leo.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.leo.models.Model;
import com.leo.utils.ErrorPopup;

/**
 *
 * @author Dang Tuan Anh
 */
public abstract class ManagerPaneView<T extends Model> extends JPanel {

  DefaultTableModel tableModel = new DefaultTableModel();
  List<T> tableData = new ArrayList<>();

  public ManagerPaneView() {
    initComponents();
    tblData.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    tblData.getTableHeader().setOpaque(false);
    tblData.getTableHeader().setBackground(new Color(51, 175, 255));
    tblData.getTableHeader().setForeground(new Color(255, 255, 255));
    ((DefaultTableCellRenderer) tblData.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(JLabel.LEFT);
    tblData.setAutoCreateRowSorter(true);
    tblData.setModel(tableModel);
    // cbx_list.putClientProperty("JButton.buttonType", "roundRect");
    btnAdd.putClientProperty("JButton.buttonType", "roundRect");
    btnDelete.putClientProperty("JButton.buttonType", "roundRect");
    btnEdit.putClientProperty("JButton.buttonType", "roundRect");
    btnSync.putClientProperty("JButton.buttonType", "roundRect");
    addEvent();
  }

  private void addEvent() {
    // Hiển thị place holder
    getTxtSearch().addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent evt) {
        if (getTxtSearch().getText().equals("Search")) {
          getTxtSearch().setText("");
          getTxtSearch().setForeground(Color.BLACK);
        }
      }

      public void focusLost(FocusEvent evt) {
        if (getTxtSearch().getText().equals("") || getTxtSearch().getText().equals("Search")) {
          getTxtSearch().setText("Search");
          getTxtSearch().setForeground(new Color(153, 153, 153));
        }
      }
    });
    getTxtSearch().addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          actionSearch();
        }
      }
    });
    // Sự kiện bấm nút thêm
    getBtnAdd().addActionListener(evt -> {
      try {
        actionAdd();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    // Sự kiện bấm nút sửa
    getBtnEdit().addActionListener(evt -> actionEdit());
    // Sự kiện bấm nút xóa
    getBtnDelete().addActionListener(evt -> actionDelete());
    // Sự kiện bấm nút cập nhật
    getBtnSync().addActionListener(evt -> updateData());
  }

  public abstract void actionAdd() throws SQLException;

  public abstract void actionSearch();

  public abstract void actionDelete();

  public abstract void actionEdit();

  public abstract void updateData();

  public JComboBox<String> getCboSearchField() {
    return cboSearchField;
  }

  public void setCboSearchField(JComboBox<String> cboSearchField) {
    this.cboSearchField = cboSearchField;
  }

  public JTextField getTxtSearch() {
    return txtSearch;
  }

  public void setTxtSearch(JTextField txtSearch) {
    this.txtSearch = txtSearch;
  }

  public DefaultTableModel getTableModel() {
    return this.tableModel;
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

  // Lấy các nút để set event
  public JButton getBtnAdd() {
    return btnAdd;
  }

  public JButton getBtnDelete() {
    return btnDelete;
  }

  public JButton getBtnEdit() {
    return btnEdit;
  }

  public JButton getBtnSync() {
    return btnSync;
  }

  public List<T> getTableData() {
    return tableData;
  }

  public void setTableData(List<T> tableData) {
    this.tableData = tableData;
    renderTable();
  }

  public JTable getTblData() {
    return tblData;
  }

  // Lấy id các hàng đc chọn
  public int[] getSelectedIds() {
    int selectedRows[] = tblData.getSelectedRows();
    int selectedIds[] = new int[selectedRows.length];
    for (int i = 0; i < selectedRows.length; i++) {
      int selectedRow = selectedRows[i];
      int id = (int) tblData.getValueAt(selectedRow, 0);
      selectedIds[i] = id;
    }
    return selectedIds;
  }

  // Lấy id hàng chọn đầu
  public int getSelectedId() {

    int selectedRow = tblData.getSelectedRow();
    if (selectedRow < 0) {
      return -1;
    }
    int id = (int) tblData.getValueAt(selectedRow, 0);
    return id;
  }

  public void renderTable() {
    tableModel.setNumRows(0);
    try {
      for (T item : tableData) {
        tableModel.addRow(item.toRowTable());
        System.out.println("item: " + item);
      }
    } catch (Exception e) {
      showError(e);
    }
  }

  public abstract void setTableModel();

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    GridBagConstraints gridBagConstraints;

    jScrollPane1 = new JScrollPane();
    tblData = new JTable();
    jPanel1 = new JPanel();
    btnDelete = new JButton();
    btnSync = new JButton();
    btnEdit = new JButton();
    btnAdd = new JButton();
    jPanel2 = new JPanel();
    txtSearch = new JTextField();
    cboSearchField = new JComboBox<>();

    setBackground(new Color(118, 215, 196));
    setAlignmentX(0.0F);
    setAlignmentY(0.0F);
    setPreferredSize(new Dimension(1008, 680));
    setLayout(new BorderLayout());

    jScrollPane1.setOpaque(false);

    tblData.setModel(new DefaultTableModel(
        new Object[][] {
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null }
        },
        new String[] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }));
    tblData.setFocusable(false);
    tblData.setRowHeight(30);
    tblData.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tblData);

    add(jScrollPane1, BorderLayout.CENTER);

    jPanel1.setOpaque(false);
    jPanel1.setLayout(new GridBagLayout());

    btnDelete.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
    btnDelete.setText("Delete");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 42;
    gridBagConstraints.insets = new Insets(15, 5, 15, 5);
    jPanel1.add(btnDelete, gridBagConstraints);

    btnEdit.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
    btnEdit.setText("Edit");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 42;
    gridBagConstraints.insets = new Insets(15, 5, 15, 5);
    jPanel1.add(btnEdit, gridBagConstraints);

    btnAdd.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
    btnAdd.setText("Add");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.ipadx = 42;
    gridBagConstraints.insets = new Insets(15, 5, 15, 5);
    jPanel1.add(btnAdd, gridBagConstraints);

    add(jPanel1, BorderLayout.LINE_END);

    jPanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    jPanel2.setOpaque(false);
    jPanel2.setPreferredSize(new Dimension(1008, 40));
    jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

    txtSearch.setForeground(new Color(153, 153, 153));
    txtSearch.setText("Search");
    txtSearch.setAlignmentX(0.0F);
    txtSearch.setAlignmentY(0.0F);
    txtSearch.setBorder(null);
    txtSearch.setPreferredSize(new Dimension(200, 25));
    jPanel2.add(txtSearch);

    cboSearchField.setMinimumSize(new Dimension(100, 25));
    cboSearchField.setPreferredSize(new Dimension(100, 25));
    jPanel2.add(cboSearchField);

    add(jPanel2, BorderLayout.PAGE_START);
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JButton btnAdd;
  private JButton btnDelete;
  private JButton btnEdit;
  private JButton btnSync;
  private JComboBox<String> cboSearchField;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JScrollPane jScrollPane1;
  private JTable tblData;
  private JTextField txtSearch;
  // End of variables declaration//GEN-END:variables
}
