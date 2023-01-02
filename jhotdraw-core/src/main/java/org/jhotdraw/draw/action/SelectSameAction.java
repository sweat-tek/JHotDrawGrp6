/*
 * @(#)SelectSameAction.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.action;

import java.util.*;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * SelectSameAction.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class SelectSameAction extends AbstractSelectedAction {
    private static final long serialVersionUID = 1L;
    public static final String ID = "edit.selectSame";

    /**
     * Creates a new instance.
     */
    @FeatureEntryPoint(value = "selectSame")
    public SelectSameAction(DrawingEditor editor) {
        super(editor);
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        labels.configureAction(this, ID);
        updateEnabledState();
    }
    @FeatureEntryPoint(value = "selectSame")
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        selectSame();
    }
    @FeatureEntryPoint(value = "selectSame  ")
    public void selectSame() {
        HashSet<Class<?>> selectedClasses = new HashSet<>();
        for (Figure selected : getView().getSelectedFigures()) {
            selectedClasses.add(selected.getClass());
        }
        for (Figure f : getDrawing().getChildren()) {
            if (selectedClasses.contains(f.getClass())) {
                getView().addToSelection(f);
            }
        }
    }
}
