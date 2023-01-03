package org.jhotdraw.draw;

import org.jhotdraw.draw.figure.Figure;

import java.util.LinkedList;

public class ArrangeModel {
    public final DrawingView view;
    public LinkedList<Figure> figures;

    public ArrangeModel(final DrawingView view) {
        this.view = view;
        this.figures = new LinkedList<Figure>(view.getSelectedFigures());
    }
}
