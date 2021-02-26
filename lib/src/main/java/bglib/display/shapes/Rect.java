package bglib.display.shapes;

import bglib.util.*;

import java.awt.*;
import java.awt.Graphics2D;

public class Rect extends Shape {
    protected final RectType rect;
    protected final int border;
    protected final Color color;

    protected final boolean useConversion;

    public Rect(RectType rect, int border, Color color) {
        this(rect, border, color, true);
    }
    public Rect(RectType rect, int border, Color color, boolean useConversion) {
        this.rect = rect;
        this.border = border;
        this.color = color;
        this.useConversion = useConversion;
    }

    /**
     * Note: could use two Conversions for pos and size, but I think that won't be great for other shapes
     */
    @Override
    public void draw(Conversion conversion, Graphics2D g) {
        Vector2i pos;
        Vector2i size;
        
        if (useConversion)
            pos = conversion.convert(rect.getPos());
        else
            pos = rect.getPos().floor();

        size = rect.getSize().floor();

        g.setColor(color);
        if (border > 0)
            g.setStroke(new BasicStroke(border));
        g.drawRect(pos.x, pos.y, size.x, size.y);
    }
}
