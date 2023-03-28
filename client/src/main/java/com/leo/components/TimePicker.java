package com.leo.components;

import javax.swing.*;
import java.util.Date;

public class TimePicker{
    public JSpinner getTimeSpinner() {
        return timeSpinner;
    }

    private JSpinner timeSpinner;
    public TimePicker() {
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
    }
}
