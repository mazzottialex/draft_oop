package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

/**
 * A custom border implementation that adds rounded corners to a component.
 * This border can be used to give a rounded appearance to Swing components.
 *
 * Source: Stack Overflow post: https://stackoverflow.com/questions/25796572/simplest-code-to-round-corners-of-jlabel-in-java
 */
public final class RoundedBorder extends AbstractBorder {

    private static final long serialVersionUID = 1L;
    private final Color color;
    private final int gap;

    /**
     * Constructs a RoundedBorder object with the specified color and gap.
     *
     * @param c   the color of the border
     * @param g   the gap size between the border and the component
     */
    public RoundedBorder(final Color c, final int g) {
        color = c;
        gap = g;
    }

    @Override
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
        final Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, gap, gap));
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(final Component c) {
        return getBorderInsets(c, new Insets(gap, gap, gap, gap));
    }

    @Override
    public Insets getBorderInsets(final Component c, final Insets insets) {
        insets.left = gap / 2;
        insets.top = gap / 2;
        insets.right = gap / 2;
        insets.bottom = gap / 2;
        return insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
