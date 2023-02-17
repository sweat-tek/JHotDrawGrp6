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
import org.jhotdraw.text.ColorFormatter;
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

public class CTExtendedStrategy implements StateStrategy {

    public JComponent initDisclosedComponent(DrawingEditor editor,
                                             LinkedList<Disposable> disposables) {

        JPanel p = new JPanel();
        p.setOpaque(false);
        // Abort if no editor is set
        if (editor == null) {
            return null;
        }
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        JPanel p3 = new JPanel(new GridBagLayout());
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        AbstractButton btn;
        p.removeAll();
        p.setBorder(new EmptyBorder(5, 5, 5, 8));
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels");
        GridBagLayout layout = new GridBagLayout();
        p.setLayout(layout);
        // Fill color field with button
        JAttributeTextField<Color> colorField = new JAttributeTextField<Color>();
        colorField.setColumns(7);
        colorField.setToolTipText(labels.getString("attribute.canvasFillColor.toolTipText"));
        colorField.putClientProperty("Palette.Component.segmentPosition", "first");
        colorField.setUI((PaletteFormattedTextFieldUI) PaletteFormattedTextFieldUI.createUI(colorField));
        colorField.setFormatterFactory(ColorFormatter.createFormatterFactory());
        colorField.setHorizontalAlignment(JTextField.LEFT);
        disposables.add(new DrawingAttributeEditorHandler<Color>(CANVAS_FILL_COLOR, colorField, editor));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        p1.add(colorField, gbc);
        btn = ButtonFactory.createDrawingColorChooserButton(editor,
                CANVAS_FILL_COLOR, "attribute.canvasFillColor", labels,
                null, new Rectangle(3, 3, 10, 10), PaletteColorChooserUI.class, disposables);
        btn.setUI((PaletteButtonUI) PaletteButtonUI.createUI(btn));
        ((JPopupButton) btn).setAction(null, null);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        p1.add(btn, gbc);
        // Opacity field with slider
        JAttributeTextField<Double> opacityField = new JAttributeTextField<Double>();
        opacityField.setColumns(4);
        opacityField.setToolTipText(labels.getString("attribute.figureOpacity.toolTipText"));
        opacityField.setHorizontalAlignment(JAttributeTextField.RIGHT);
        opacityField.putClientProperty("Palette.Component.segmentPosition", "first");
        opacityField.setUI((PaletteFormattedTextFieldUI) PaletteFormattedTextFieldUI.createUI(opacityField));
        JavaNumberFormatter formatter = new JavaNumberFormatter(0d, 100d, 100d, false, "%");
        formatter.setUsesScientificNotation(false);
        formatter.setMaximumFractionDigits(1);
        opacityField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        opacityField.setHorizontalAlignment(JTextField.LEADING);
        disposables.add(new DrawingAttributeEditorHandler<Double>(CANVAS_FILL_OPACITY, opacityField, editor));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(3, 0, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        p1.add(opacityField, gbc);
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
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(3, 0, 0, 0);
        p1.add(opacityPopupButton, gbc);
        // Width and height fields
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
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 0, 0, 0);
        p3.add(widthLabel, gbc);
        widthField.setUI((TextUI) PaletteFormattedTextFieldUI.createUI(widthField));
        widthField.setColumns(3);
        widthField.setToolTipText(labels.getString("attribute.canvasWidth.toolTipText"));
        widthField.setFormatterFactory(JavaNumberFormatter.createFormatterFactory(1d, 4096d, 1d, true));
        widthField.setHorizontalAlignment(JTextField.LEADING);
        disposables.add(new DrawingAttributeEditorHandler<Double>(CANVAS_WIDTH, widthField, editor));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 0, 0);
        p3.add(widthField, gbc);
        heightLabel.setUI((LabelUI) PaletteLabelUI.createUI(heightLabel));
        heightLabel.setLabelFor(widthField);
        heightLabel.setToolTipText(labels.getString("attribute.canvasHeight.toolTipText"));
        heightLabel.setText(labels.getString("attribute.canvasHeight.text")); // NOI18N
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 0, 0);
        p3.add(heightLabel, gbc);
        heightField.setUI((TextUI) PaletteFormattedTextFieldUI.createUI(widthField));
        heightField.setColumns(3);
        heightField.setToolTipText(labels.getString("attribute.canvasHeight.toolTipText"));
        heightField.setFormatterFactory(JavaNumberFormatter.createFormatterFactory(1d, 4096d, 1d, true));
        heightField.setHorizontalAlignment(JTextField.LEADING);
        disposables.add(new DrawingAttributeEditorHandler<Double>(CANVAS_HEIGHT, heightField, editor));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 0, 0);
        p3.add(heightField, gbc);
        // Add horizontal strips
        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        p.add(p1, gbc);
        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        p.add(p2, gbc);
        gbc = new GridBagConstraints();
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        p.add(p3, gbc);

        return p;
    }
}
