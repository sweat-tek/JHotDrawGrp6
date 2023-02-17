package org.jhotdraw.samples.svg.gui;

import org.jhotdraw.api.app.Disposable;
import org.jhotdraw.draw.DrawingEditor;

import javax.swing.*;
import java.util.LinkedList;

public interface StateStrategy {

    public JComponent initDisclosedComponent (DrawingEditor editor,
                                              LinkedList<Disposable> disposables);

}
