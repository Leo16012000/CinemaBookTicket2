package com.leo.utils;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

public class DocumentBinder {
  public static DocumentListener bind(Consumer<String> setter) {
    return bind(setter, v -> v);
  }

  public static <T> DocumentListener bind(Consumer<T> setter, Function<String, T> mapper) {
    return new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        update(e, setter, mapper);
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        update(e, setter, mapper);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        update(e, setter, mapper);
      }
    };
  }

  private static <T> void update(DocumentEvent e, Consumer<T> setter, Function<String, T> mapper) {
    int length = e.getDocument().getLength();
    T val = null;
    try {
      val = mapper.apply(e.getDocument().getText(0, length));
    } catch (BadLocationException e1) {
    }
    setter.accept(val);
  }

  public static Integer parseInteger(String txt) {
    if (StringUtils.isBlank(txt)) {
      return null;
    }
    try {
      int num = Integer.valueOf(txt);
      return num;
    }
    catch (Exception e) {
      return null;
    }
  }
}
