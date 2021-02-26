package bglib.display.shapes;

import java.awt.*;

import bglib.util.RectType;
import bglib.util.Vector2i;

public class FillRect extends Rect {

    public FillRect(RectType rect, int border, Color color) {
        super(rect, border, color);
    }

    @Override
    public void draw(Conversion conversion, Graphics2D g) {
        Vector2i pos = conversion.convert(rect.getPos().round());
        Vector2i size = conversion.convert(rect.getPos().add(rect.getSize()).round()).sub(pos);
        System.out.println(rect.getPos().round()+", "+pos);
        System.out.println(rect.getSize().round()+", "+size);
        g.setColor(color);
        g.setStroke(new BasicStroke(border));
        g.fillRect(pos.x, pos.y, size.x, size.y);
    }
}
