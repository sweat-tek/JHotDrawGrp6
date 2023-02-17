package org.jhotdraw.samples.svg.gui;

import org.jhotdraw.api.app.Disposable;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AbstractSelectedAction;
import org.jhotdraw.draw.event.DrawingAttributeEditorHandler;
import org.jhotdraw.draw.event.DrawingComponentRepainter;
import org.jhotdraw.draw.gui.JAttributeSlider;
import org.jhotdraw.draw.gui.JAttributeTextField;
import org.jhotdraw.formatter.JavaNumberFormatter;
import org.jhotdraw.gui.JPopupButton;
import org.jhotdraw.gui.action.ButtonFactory;
import org.jhotdraw.gui.plaf.palette.*;
import org.jhotdraw.util.Images;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.SliderUI;
import javax.swing.plaf.TextUI;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.util.LinkedList;

import static org.jhotdraw.draw.AttributeKeys.*;
import static org.jhotdraw.draw.AttributeKeys.CANVAS_HEIGHT;

public class CTDefaultStrategy implements StateStrategy {



    public JComponent initDisclosedComponent(DrawingEditor editor,
                                             LinkedList<Disposable> disposables) {

        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(5, 5, 5, 8));
        // Abort if no editor is set
        if (editor == null) {
            return null;
        }
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle(
                "org.jhotdraw.samples.svg.Labels");
        GridBagLayout layout = new GridBagLayout();
        p.setLayout(layout);
        GridBagConstraints gbc;
        AbstractButton btn;
        AbstractSelectedAction d;
        // Fill color
        btn = ButtonFactory.createDrawingColorChooserButton(editor,
                CANVAS_FILL_COLOR, "attribute.canvasFillColor", labels,
                null, new Rectangle(3, 3, 10, 10), PaletteColorChooserUI.class, disposables);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        ((JPopupButton) btn).setAction(null, null);
        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        p.add(btn, gbc);
        // Opacity slider
        JPopupButton opacityPopupButton = new JPopupButton();
        JAttributeSlider opacitySlider = new JAttributeSlider(JSlider.VERTICAL, 0, 100, 100);
        opacitySlider.setUI((SliderUI) PaletteSliderUI.createUI(opacitySlider));
        opacitySlider.setScaleFactor(100d);
        disposables.add(new DrawingAttributeEditorHandler<Double>(CANVAS_FILL_OPACITY, opacitySlider, editor));
        opacityPopupButton.add(opacitySlider);
        labels.configureToolBarButton(opacityPopupButton, "attribute.canvasFillOpacity");
        opacityPopupButton.setUI((PaletteButtonUI) PaletteButtonUI.createUI(opacityPopupButton));
        opacityPopupButton.setIcon(
                new DrawingOpacityIcon(editor, CANVAS_FILL_OPACITY, CANVAS_FILL_COLOR, null, Images.createImage(getClass(), labels.getString("attribute.canvasFillOpacity.icon")),
                        new Rectangle(5, 5, 6, 6), new Rectangle(4, 4, 7, 7)));
        disposables.add(new DrawingComponentRepainter(editor, opacityPopupButton));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(0, 3, 0, 0);
        p.add(opacityPopupButton, gbc);
        // Width and height fields
        JLabel widthLabel, heightLabel;
        JAttributeTextField<Double> widthField, heightField;
        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        widthField = new JAttributeTextField<Double>();
        heightField = new JAttributeTextField<Double>();
        widthLabel.setUI((LabelUI) PaletteLabelUI.createUI(widthLabel));
        widthLabel.setLabelFor(widthField);
        widthLabel.setToolTipText(labels.getString("attribute.canvasWidth.toolTipText"));
        widthLabel.setText(labels.getString("attribute.canvasWidth.text")); // NOI18N
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 0, 0, 0);
        p.add(widthLabel, gbc);
        widthField.setUI((TextUI) PaletteFormattedTextFieldUI.createUI(widthField));
        widthField.setColumns(3);
        widthField.setToolTipText(labels.getString("attribute.canvasWidth.toolTipText"));
        JavaNumberFormatter formatter = new JavaNumberFormatter(1d, 4096d, 1d, true);
        formatter.setUsesScientificNotation(false);
        widthField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        widthField.setHorizontalAlignment(JTextField.LEADING);
        disposables.add(new DrawingAttributeEditorHandler<Double>(CANVAS_WIDTH, widthField, editor));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 0, 0);
        p.add(widthField, gbc);
        heightLabel.setUI((LabelUI) PaletteLabelUI.createUI(heightLabel));
        heightLabel.setLabelFor(widthField);
        heightLabel.setToolTipText(labels.getString("attribute.canvasHeight.toolTipText"));
        heightLabel.setText(labels.getString("attribute.canvasHeight.text")); // NOI18N
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 0, 0, 0);
        p.add(heightLabel, gbc);
        heightField.setUI((TextUI) PaletteFormattedTextFieldUI.createUI(widthField));
        heightField.setColumns(3);
        heightField.setToolTipText(labels.getString("attribute.canvasHeight.toolTipText"));
        formatter = new JavaNumberFormatter(1d, 4096d, 1d, true);
        formatter.setUsesScientificNotation(false);
        heightField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        heightField.setHorizontalAlignment(JTextField.LEADING);
        disposables.add(new DrawingAttributeEditorHandler<Double>(CANVAS_HEIGHT, heightField, editor));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 0, 0);
        gbc.gridwidth = 2;
        p.add(heightField, gbc);

        return p;
    }
}
