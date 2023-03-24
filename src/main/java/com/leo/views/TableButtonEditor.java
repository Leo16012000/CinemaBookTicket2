package com.leo.views;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

class TableButtonEditor extends DefaultCellEditor {

  protected JButton button;
  private String label;

  public TableButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
  }

  public void addActionListener(ActionListener actionListener) {
    button.addActionListener(actionListener);
  }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    button.putClientProperty("row", row);
    button.putClientProperty("column", column);
    button.putClientProperty("table", table);
    return button;
  }

  @Override
  public Object getCellEditorValue() {
    return label;
  }
}
