package com.leo.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.leo.models.SeatSelection;
import com.leo.utils.GridBagConstraintsBuilder;

public class SeatPanel extends JPanel implements IView<SeatSelection> {
  private static final Color AVAILABLE_COLOR = new Color(0, 255, 0, 20);
  private static final Color RESERVED_COLOR = new Color(255, 0, 0, 20);
  private static final Color HOLD_COLOR = new Color(255, 255, 0, 20);

  private static final Color HOVER_AVAILABLE_COLOR = new Color(0, 255, 0, 255);
  private static final Color HOVER_HOLD_COLOR = new Color(255, 255, 0, 255);
  private static final Color HOVER_RESERVED_COLOR = new Color(255, 0, 0, 255);

  private JButton clickBtn;
  private SeatSelection model;
  private GridBagConstraintsBuilder defaultGbcBuilder = GridBagConstraintsBuilder.builder()
      .fill(GridBagConstraints.BOTH).weightx(1.).weighty(1.).gridx(0).gridy(0);
  private boolean toggle = false;
  private ActionListener actionListener;

  public SeatPanel(SeatSelection model) {
    this.model = model;
    init();
    initUI();
    registerEvent();
  }

  public void registerActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
  }

  public boolean isToggle() {
    return toggle;
  }

  @Override
  public void init() {
    /** noop */
  }

  @Override
  public void initUI() {
    setLayout(new GridBagLayout());

    clickBtn = new JButton();
    clickBtn.setBackground(getColor(false));
    add(clickBtn, defaultGbcBuilder.build());

    setOpaque(false);
  }

  @Override
  public void registerEvent() {
    clickBtn.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent me) {
        clickBtn.setBackground(getColor(true));
        clickBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      }

      @Override
      public void mouseExited(MouseEvent me) {
        clickBtn.setBackground(getColor(false));
        clickBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
    });

    clickBtn.addActionListener(e -> {
      if (model.toggle()) {
        refreshUI();
        this.toggle = true;
      } else {
        this.toggle = false;
      }
      Optional.ofNullable(actionListener).ifPresent(it -> it.actionPerformed(e));
    });
  }

  @Override
  public void refresh() throws SQLException {
    /** noop */
  }

  @Override
  public SeatSelection getModel() {
    return this.model;
  }

  @Override
  public void handleError(Exception e) {
    /** noop */
  }

  @Override
  public void refreshUI() {
    setBackground(getColor(false));
  }

  private Color getColor(boolean isHovered) {
    switch (model.getStatus()) {
      case RESERVED:
        if (isHovered) {
          return HOVER_RESERVED_COLOR;
        }
        return RESERVED_COLOR;
      case AVAILABLE:
        if (isHovered) {
          return HOVER_AVAILABLE_COLOR;
        }
        return AVAILABLE_COLOR;
      case HOLD:
        if (isHovered) {
          return HOVER_HOLD_COLOR;
        }
        return HOLD_COLOR;
      default:
        throw new UnsupportedOperationException();
    }
  }

  // @Override
  // protected void paintComponent(Graphics g) {
  // // g.setColor(getBackground());
  // g.fillRect(0, 0, getWidth(), getHeight());
  // super.paintComponent(g);
  // }
}
