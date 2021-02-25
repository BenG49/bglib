package bglib.display.shapes;

import java.awt.*;
import java.awt.Graphics2D;
import bglib.util.Vector2i;

public class FillRect extends Shape {
    private final int x, y, width, height, border;
    private final Color fill;

    public FillRect(Vector2i pos, Vector2i size, int border, Color background) {
        this(pos.x, pos.y, size.x, size.y, border, background);
    }
    public FillRect(Vector2i pos, int width, int height, int border, Color background) {
        this(pos.x, pos.y, width, height, border, background);
    }
    public FillRect(Vector2i pos, int size, int border, Color background) {
        this(pos.x, pos.y, size, size, border, background);
    }
    public FillRect(int x, int y, int width, int height, int border, Color fill) {
        if (width < 0) {
            x += width;
            width *= -1;
        }
        if (height < 0) {
            y += height;
            height *= -1;
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.border = border;
        this.fill = fill;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(fill);
        g.setStroke(new BasicStroke(border));
        g.fillRect(x, y, width, height);
    }
}
