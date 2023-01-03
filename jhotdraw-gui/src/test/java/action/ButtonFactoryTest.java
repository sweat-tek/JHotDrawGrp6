package action;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.action.ZoomAction;
import org.jhotdraw.gui.JPopupButton;

import java.awt.event.ActionEvent;

import static org.junit.Assert.*;

public class ButtonFactoryTest {

    private ZoomAction zoomAction1;
    private DrawingView drawingView;


    @org.junit.Before
    public void setUp() {
        JPopupButton zoomPopUpButton = new JPopupButton();
        drawingView = new DefaultDrawingView();
        zoomAction1 = new ZoomAction(drawingView, 1.5, zoomPopUpButton);
    }

    @org.junit.Test
    public void isZoomLevel() {
        if (drawingView.getScaleFactor() == 1.5) {
            fail("Zoom level is already set");
        }
        zoomAction1.actionPerformed(new ActionEvent(this, 1, "zoom"));
        assertEquals(drawingView.getScaleFactor(), zoomAction1.getScaleFactor(), 0);
    }
}