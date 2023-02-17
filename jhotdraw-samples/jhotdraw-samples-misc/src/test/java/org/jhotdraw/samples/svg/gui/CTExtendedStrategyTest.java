package org.jhotdraw.samples.svg.gui;

import org.jhotdraw.draw.DefaultDrawingEditor;
import org.junit.Test;

import javax.swing.*;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CTExtendedStrategyTest {

    @Test
    public void initDisclosedComponent() {
        StateStrategy ctDefaultStrategy = new CTExtendedStrategy();
        JComponent panel = ctDefaultStrategy.initDisclosedComponent(new DefaultDrawingEditor(),
                new LinkedList<>());
        assertNotNull(panel);
    }
}


