package org.jhotdraw.draw;

public class ArrangeStrat {
    public ArrangeLayer redo;
    public ArrangeLayer undo;

    public ArrangeStrat(ArrangeLayer redo, ArrangeLayer undo) {
        this.redo = redo;
        this.undo = undo;
    }
}
