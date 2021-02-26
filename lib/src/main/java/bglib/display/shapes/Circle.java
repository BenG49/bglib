package bglib.display.shapes;

import java.awt.Graphics2D;
import java.awt.Color;

import bglib.util.Vector2i;

public class Circle extends Shape {
    private final Vector2i pos;
    private final int diameter;
    private final Color color;

    private final boolean useConversion;

    public Circle(Vector2i pos, int diameter, Color color) {
        this(pos, diameter, color, true);
    }
    public Circle(Vector2i pos, int diameter, Color color, boolean useConversion) {
        this.pos = pos;
        this.diameter = diameter;
        this.color = color;
        this.useConversion = useConversion;
    }

    @Override
    public void draw(Conversion conversion, Graphics2D g) {
        Vector2i draw;
        if (useConversion)
            draw = conversion.convert(pos);
        else
            draw = pos;

        g.setColor(color);
        g.fillOval(draw.x, draw.y, diameter, diameter);
    }
    
}
