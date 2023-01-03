package org.jhotdraw.gui.action.buttonlisteners;

import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.gui.JPopupButton;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ZoomListener implements PropertyChangeListener {

    private JPopupButton zoomPopupButton;
    private DrawingView view;

    public ZoomListener(JPopupButton zoomPopupButton, DrawingView view) {
        this.zoomPopupButton = zoomPopupButton;
        this.view = view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // String constants are interned
        if ("scaleFactor".equals(evt.getPropertyName())) {
            zoomPopupButton.setText((int) (view.getScaleFactor() * 100) + " %");
        }
    }
}
