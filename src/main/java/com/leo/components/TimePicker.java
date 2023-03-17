package com.leo.components;

import javax.swing.*;
import java.util.Date;

public class TimePicker{
    public JSpinner getTimeSpinner() {
        return timeSpinner;
    }

    private JSpinner timeSpinner;
    public TimePicker() {
        // Create a SpinnerDateModel with current time as initial value
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(new Date());

        // Create a JSpinner with the SpinnerDateModel
        timeSpinner = new JSpinner(model);

        // Configure the JSpinner to display only time (no date)
        JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(editor);
    }
}
