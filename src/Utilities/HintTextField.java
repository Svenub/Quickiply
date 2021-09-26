package Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private Color hintColor = Color.GRAY;
    private Color defaultColor;
    private boolean showingHint;

    public HintTextField(final String hint, Color startingHintColor) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        this.hintColor = startingHintColor;
        super.setForeground(hintColor);
        super.addFocusListener(this);
    }
    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.setForeground(hintColor);
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
            super.setForeground(defaultColor);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
            super.setForeground(hintColor);
        }

    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

    @Override
    public void setForeground(Color color){
        defaultColor = color;
    }

    public void setHintColor(Color color) {
        hintColor = color;
        super.setForeground(hintColor);
    }


}