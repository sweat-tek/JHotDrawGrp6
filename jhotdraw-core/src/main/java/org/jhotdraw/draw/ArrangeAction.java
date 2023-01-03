package org.jhotdraw.draw;

import org.jhotdraw.draw.action.AbstractSelectedAction;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.util.ResourceBundleUtil;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;
import java.util.Iterator;

public abstract class ArrangeAction extends AbstractSelectedAction {
    public String ID;
    public ArrangeLayer direction;

    public ArrangeAction(DrawingEditor editor, String id, ArrangeLayer direction) {
        super(editor);
        this.ID = id;
        this.direction = direction;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        ArrangeModel model = new ArrangeModel(getView());
        arrange(model, this.direction);
        ArrangeStrat strat;

        if (this.direction == ArrangeLayer.BACK) {
            strat = new ArrangeStrat(ArrangeLayer.BACK, ArrangeLayer.FRONT);
        } else {
            strat = new ArrangeStrat(ArrangeLayer.FRONT, ArrangeLayer.BACK);
        }
        UndoableEdit edit = undoableAction(model, strat);
        fireUndoableEditHappened(edit);
    }

    public static void arrange(ArrangeModel model, ArrangeLayer direction) {
        Iterator i = model.figures.iterator();
        Drawing d = model.view.getDrawing();

        while (i.hasNext()) {
            Figure figure = (Figure) i.next();
            d.arrange(figure, direction);
        }
    }

    private UndoableEdit undoableAction(ArrangeModel model, ArrangeStrat strategy) {
        return new AbstractUndoableEdit() {
            @Override
            public String getPresentationName() {
                ResourceBundleUtil labels
                        = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
                return labels.getTextProperty(ID);
            }
            @Override
            public void redo() throws CannotRedoException {
                arrange(model, strategy.redo);
                super.redo();
            }

            @Override
            public void undo() throws CannotUndoException {
                arrange(model, strategy.undo);
                super.undo();
            }
        };
    }
}
