package com.leo.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Optional;

public class GridBagConstraintsBuilder implements Cloneable {
  private Integer gridx;
  private Integer gridy;
  private Integer gridwidth;
  private Integer gridheight;
  private Double weightx;
  private Double weighty;
  private Integer fill;
  private Integer anchor;
  private Insets insets;
  private Integer ipadx;
  private Integer ipady;

  public static GridBagConstraintsBuilder builder() {
    return new GridBagConstraintsBuilder();
  }

  public GridBagConstraintsBuilder gridx(Integer gridx) {
    this.gridx = gridx;
    return this;
  }

  public GridBagConstraintsBuilder gridy(Integer gridy) {
    this.gridy = gridy;
    return this;
  }

  public GridBagConstraintsBuilder gridwidth(Integer gridwidth) {
    this.gridwidth = gridwidth;
    return this;
  }

  public GridBagConstraintsBuilder gridheight(Integer gridheight) {
    this.gridheight = gridheight;
    return this;
  }

  public GridBagConstraintsBuilder weightx(Double weightx) {
    this.weightx = weightx;
    return this;
  }

  public GridBagConstraintsBuilder weighty(Double weighty) {
    this.weighty = weighty;
    return this;
  }

  public GridBagConstraintsBuilder fill(Integer fill) {
    this.fill = fill;
    return this;
  }

  public GridBagConstraintsBuilder anchor(Integer anchor) {
    this.anchor = anchor;
    return this;
  }

  public GridBagConstraintsBuilder insets(Insets insets) {
    this.insets = insets;
    return this;
  }

  public GridBagConstraintsBuilder ipadx(Integer ipadx) {
    this.ipadx = ipadx;
    return this;
  }

  public GridBagConstraintsBuilder ipady(Integer ipady) {
    this.ipady = ipady;
    return this;
  }

  public GridBagConstraints build() {
    GridBagConstraints gbc = new GridBagConstraints();
    Optional.ofNullable(gridx).ifPresent(it -> gbc.gridx = it);
    Optional.ofNullable(gridy).ifPresent(it -> gbc.gridy = it);
    Optional.ofNullable(gridwidth).ifPresent(it -> gbc.gridwidth = it);
    Optional.ofNullable(gridheight).ifPresent(it -> gbc.gridheight = it);
    Optional.ofNullable(weightx).ifPresent(it -> gbc.weightx = it);
    Optional.ofNullable(weighty).ifPresent(it -> gbc.weighty = it);
    Optional.ofNullable(fill).ifPresent(it -> gbc.fill = it);
    Optional.ofNullable(anchor).ifPresent(it -> gbc.anchor = it);
    Optional.ofNullable(insets).ifPresent(it -> gbc.insets = it);
    Optional.ofNullable(ipadx).ifPresent(it -> gbc.ipadx = it);
    Optional.ofNullable(ipady).ifPresent(it -> gbc.ipady = it);
    return gbc;
  }

  public GridBagConstraintsBuilder clone() {
    try {
      return (GridBagConstraintsBuilder) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }
}
