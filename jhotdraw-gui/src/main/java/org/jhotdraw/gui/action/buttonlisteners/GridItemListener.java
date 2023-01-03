package org.jhotdraw.gui.action.buttonlisteners;

import org.jhotdraw.draw.DrawingView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GridItemListener implements ItemListener {

    public GridItemListener(JToggleButton toggleButton, DrawingView view) {
        this.toggleButton = toggleButton;
        this.view = view;
    }

    private JToggleButton toggleButton;
    private DrawingView view;

    @Override
    public void itemStateChanged(ItemEvent e) {
        view.setConstrainerVisible(toggleButton.isSelected());
    }
}
