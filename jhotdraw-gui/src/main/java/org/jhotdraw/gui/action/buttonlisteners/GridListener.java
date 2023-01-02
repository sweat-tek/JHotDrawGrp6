package org.jhotdraw.gui.action.buttonlisteners;

import org.jhotdraw.draw.DrawingView;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GridListener implements PropertyChangeListener {

    public GridListener(JToggleButton toggleButton, DrawingView view) {
        this.toggleButton = toggleButton;
        this.view = view;
    }

    private JToggleButton toggleButton;
    private DrawingView view;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // String constants are interned
        if (evt.getPropertyName() == null || constrainerNotNull(evt)) {
            toggleButton.setSelected(view.isConstrainerVisible());
        }
    }

    private boolean constrainerNotNull (PropertyChangeEvent evt) {
        return evt.getPropertyName() != null && evt.getPropertyName().equals((DrawingView.CONSTRAINER_VISIBLE_PROPERTY));
    }
}
