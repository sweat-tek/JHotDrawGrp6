import org.jhotdraw.draw.ArrangeLayer;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.action.BringToFrontAction;
import org.jhotdraw.draw.figure.Figure;
import org.testng.annotations.Test;


import javax.swing.undo.UndoableEdit;
import java.util.HashSet;

import static org.mockito.Mockito.*;

public class BtfaTest {
    @Test
    public void testconst() {
        BringToFrontAction btfa = new BringToFrontAction(null);
        assert (btfa.ID.equals("edit.bringToFront"));
        assert (btfa.direction == ArrangeLayer.FRONT);
    }

    @Test
    public void testbtfa() {
        DrawingEditor edit = mock(DrawingEditor.class);
        DrawingView view = mock(DrawingView.class);
        Drawing draw = mock(Drawing.class);
        Figure fig = mock(Drawing.class);
        HashSet<Figure> figures = new HashSet<Figure>();
        figures.add(fig);

        when(edit.getActiveView()).thenReturn(view);
        when(view.getDrawing()).thenReturn(draw);
        when(view.getSelectedFigures()).thenReturn(figures);
        doNothing().when(draw).arrange(fig, ArrangeLayer.FRONT);
        BringToFrontAction btfa = new BringToFrontAction(edit);
        btfa.actionPerformed(null);
        verify(draw).arrange(fig, ArrangeLayer.FRONT);
        verify(draw).fireUndoableEditHappened(any(UndoableEdit.class));
    }

}
