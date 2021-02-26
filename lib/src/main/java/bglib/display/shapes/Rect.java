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
        if (rect.getSize().x < 0) {
            rect.setPos(rect.getPos().add(new Vector2d(rect.getSize().x, 0)));
            rect.setSize(new Vector2d(-rect.getSize().x, rect.getSize().y));
        }
        if (rect.getSize().y < 0) {
            rect.setPos(rect.getPos().add(new Vector2d(0, rect.getSize().y)));
            rect.setSize(new Vector2d(rect.getSize().x, -rect.getSize().y));
        }

        this.rect = rect;
        this.border = border;
        this.color = color;
        this.useConversion = useConversion;
    }

    @Override
    public void draw(Conversion conversion, Graphics2D g) {
        Vector2i pos;
        Vector2i size;
        
        if (useConversion) {
            pos = conversion.convert(rect.getPos());
            size = conversion.convert(rect.getPos().add(rect.getSize()).sub(pos));
        } else {
            pos = rect.getPos().floor();
            size = rect.getSize().floor();
        }

        g.setColor(color);
        if (border > 0)
            g.setStroke(new BasicStroke(border));
        g.drawRect(pos.x, pos.y, size.x, size.y);
    }
}
