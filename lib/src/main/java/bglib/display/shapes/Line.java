package bglib.display.shapes;

import java.awt.*;
import java.awt.Graphics2D;

import bglib.util.Vector2i;

public class Line extends Shape {
    private final Vector2i posA, posB;
    private final Color color;
    private final int width;

    private final boolean useConversion;

    public Line(Vector2i posA, Vector2i posB, Color color, int width) {
        this(posA, posB, color, width, true);
    }
    public Line(Vector2i posA, Vector2i posB, Color color, int width, boolean useConversion) {
        this.posA = posA;
        this.posB = posB;
        this.color = color;
        this.width = width;
        this.useConversion = useConversion;
    }

    @Override
    public void draw(Conversion conversion, Graphics2D g) {
        Vector2i drawA;
        Vector2i drawB;
        
        if (useConversion) {
            drawA = conversion.convert(posA);
            drawB = conversion.convert(posB);
        } else {
            drawA = posA;
            drawB = posB;
        }

        g.setColor(color);
        g.setStroke(new BasicStroke(width));
        g.drawLine(drawA.x, drawA.y, drawB.x, drawB.y);
    }
}
