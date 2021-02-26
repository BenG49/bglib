package bglib.display.shapes;

import java.awt.*;

import bglib.util.RectType;
import bglib.util.Vector2i;

public class FillRect extends Rect {

    public FillRect(RectType rect, int border, Color color, boolean useConversion) {
        super(rect, border, color, useConversion);
    }
    public FillRect(RectType rect, int border, Color color) {
        super(rect, border, color);
    }

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
        g.fillRect(pos.x, pos.y, size.x, size.y);
    }
}
