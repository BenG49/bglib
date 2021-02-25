package bglib.display.shapes;

import java.awt.*;
import java.awt.Graphics2D;

import bglib.util.Vector2i;

public class Line extends Shape {
    private final Vector2i posA, posB;
    private final Color c;
    private final int width;

    public Line(Vector2i posA, Vector2i posB, Color c, int width) {
        this.posA = posA;
        this.posB = posB;
        this.c = c;
        this.width = width;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(c);
        g.setStroke(new BasicStroke(width));
        g.drawLine(posA.x, posA.y, posB.x, posB.y);
    }
}
