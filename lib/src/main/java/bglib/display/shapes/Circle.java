package bglib.display.shapes;

import java.awt.Graphics2D;
import java.awt.Color;

import bglib.util.Vector2i;

public class Circle extends Shape {
    private final Vector2i pos;
    private final int diameter;
    private final Color c;

    public Circle(Vector2i pos, int diameter, Color c) {
        this.pos = pos;
        this.diameter = diameter;
        this.c = c;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(c);
        g.fillOval(pos.x, pos.y, diameter, diameter);
    }
    
}
