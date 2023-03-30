package com.leo.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.leo.controllers.LoginController;
import com.leo.controllers.SeatPanelController;
import com.leo.main.SessionManager;
import com.leo.models.SeatAuditorium;
import com.leo.models.SeatSelection;
import com.leo.models.User;
import com.leo.models.SeatSelection.SeatSelectionStatus;
import com.leo.utils.ErrorPopup;
import com.leo.utils.GridBagConstraintsBuilder;

public class SeatAuditoriumPanel extends JPanel implements IView<SeatAuditorium>, ICrudView {
  private SeatPanelController seatController;
  private SeatAuditorium model;
  private SeatAuditorium editingModel;
  private List<SeatPanel> managedSeatPanels = new ArrayList<>();
  private int showtimeId;
  private JPanel contentPanel;
  private JScrollPane scrollPane;
  private static GridBagConstraintsBuilder defaultGbcBuilder = GridBagConstraintsBuilder.builder()
      .fill(GridBagConstraints.HORIZONTAL).gridx(0).gridy(0).anchor(GridBagConstraints.NORTH).weighty(0.).weightx(1.)
      .insets(new Insets(5, 5, 0, 0));
  private JButton confirmBtn;
  private User session;
  private Map<String, SeatSelection> seatSelections = new HashMap<String, SeatSelection>();

  public SeatAuditoriumPanel(int showtimeId) {
    this(new SeatPanelController(), showtimeId);
  }

  public SeatAuditoriumPanel(SeatPanelController seatController, int showtimeId) {
    try {
      this.seatController = seatController;
      this.showtimeId = showtimeId;
      init();
      initUI();
      registerEvent();
    } catch (Exception e) {
      handleError(e);
    }
  }

  @Override
  public void init() throws Exception {
    this.model = seatController.getSeatAuditorium(showtimeId);
    this.editingModel = model.clone();
    this.session = SessionManager.getInstance().getSession();
  }

  @Override
  public void registerEvent() {
    managedSeatPanels.stream().forEach(item -> {
      item.registerActionListener(e -> {
        try {
          if (!item.isToggle()) {
            return;
          }
          SeatSelection model = item.getModel();
          if (model.getStatus() == SeatSelectionStatus.HOLD) {
            seatController.hold(model);
            seatSelections.put(Integer.toString(model.getSeatId()), model);
          } else if (model.getStatus() == SeatSelectionStatus.AVAILABLE) {
            seatController.unhold(model);
            seatSelections.remove(Integer.toString(model.getSeatId()));
          }
        } catch (IOException e1) {
          ErrorPopup.show(e1);
        }
      });
    });
    confirmBtn.addActionListener(e -> {
      if (session == null) {
        int selected = JOptionPane.showConfirmDialog(this, "You need to login before booking", "Login required",
            JOptionPane.YES_NO_OPTION);
        if (selected == JOptionPane.YES_OPTION) {
          backToLogin();
        } else {
          /** noop */
        }
      } else {
        try {
          seatController.reserve(showtimeId);
          JOptionPane.showMessageDialog(this, "Booking successfully");
        } catch (IOException e1) {
          handleError(e1);
        }
      }
    });
  }

  private void backToLogin() {
    SwingUtilities.getWindowAncestor(this).dispose();
    new LoginController(new LoginView());
  }

  @Override
  public void refresh() throws IOException {
    seatController.getSeatAuditorium(showtimeId).streamSeat().forEach(seat -> {
      SeatSelection item = model.getSeatSelectionAt(seat.getSeatRow(), seat.getSeatColumn());
      item.setStatus(seat.getStatus());
    });
    this.editingModel = model.clone();
  }

  @Override
  public SeatAuditorium getModel() {
    return model;
  }

  @Override
  public void handleError(Exception e) {
    ErrorPopup.show(e);
  }

  @Override
  public void confirm() throws IOException {
    int selected = JOptionPane.showConfirmDialog(this, "Reserve seat?", "Confirm reservation",
        JOptionPane.YES_NO_OPTION);
    if (selected == JOptionPane.YES_OPTION) {
      /** noop */
    } else {
      List<SeatSelection> selectedSeats = getSelectedSeats();
      seatController.reserve(model.getShowtime().getId());
      selectedSeats.stream().forEach(seat -> {
        SeatSelection newSelection = editingModel.getSeatSelectionAt(seat.getSeatRow(), seat.getSeatColumn());
        model.getSeatSelectionAt(seat.getSeatRow(), seat.getSeatColumn()).setStatus(newSelection.getStatus());
      });
      JOptionPane.showMessageDialog(this, "Reserve successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void cancel() {
    int selected = JOptionPane.showConfirmDialog(this, "Cancel reservation?", "Confirm cancel",
        JOptionPane.YES_NO_OPTION);
    if (selected == JOptionPane.YES_OPTION) {
      revert();
    } else {
      /** noop */
    }
    ;
  }

  public void revert() {
    getSelectedSeats().stream().forEach(seat -> {
      SeatSelection orgSelection = model.getSeatSelectionAt(seat.getSeatRow(), seat.getSeatColumn());
      editingModel.getSeatSelectionAt(seat.getSeatRow(), seat.getSeatColumn()).setStatus(orgSelection.getStatus());
    });
  }

  public List<SeatSelection> getSelectedSeats() {
    return seatSelections.values().stream().collect(Collectors.toList());
  }

  @Override
  public void initUI() {
    setLayout(new GridBagLayout());
    add(new JLabel("Movie: " + model.getShowtime().getMovie().getTitle()), defaultGbcBuilder.clone().gridy(0).build());
    contentPanel = new JPanel();
    contentPanel.setLayout(new GridBagLayout());
    for (int i = 0; i < model.getAuditoriumHeight(); i++) {
      for (int j = 0; j < model.getAuditoriumWidth(); j++) {
        JPanel seatPanel = null;
        SeatSelection seatSelection = editingModel.getSeatSelectionAt(i, j);
        if (seatSelection == null) {
          seatPanel = new JPanel();
          seatPanel.setBackground(new Color(211, 211, 211));
        } else {
          seatPanel = new SeatPanel(seatSelection);
          managedSeatPanels.add((SeatPanel) seatPanel);
        }
        seatPanel.setMinimumSize(new Dimension(40, 40));
        seatPanel.setPreferredSize(new Dimension(40, 40));
        contentPanel.add(seatPanel,
            GridBagConstraintsBuilder.builder().gridx(j).gridy(i).insets(new Insets(5, 5, 5, 5)).build());
      }
    }
    scrollPane = new JScrollPane(contentPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(1000, 1000));

    add(scrollPane, defaultGbcBuilder.clone().weighty(1.).gridy(1).fill(GridBagConstraints.BOTH).build());
    JPanel confirmBtnPanel = new JPanel();
    confirmBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    this.confirmBtn = new JButton("Confirm your reservation");
    confirmBtnPanel.add(confirmBtn);
    add(confirmBtnPanel, defaultGbcBuilder.clone().gridy(2).build());
  }

  @Override
  public void refreshUI() {
  }
}
