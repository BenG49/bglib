package bglib.display.shapes;

import java.awt.Graphics2D;

import bglib.util.Vector2d;
import bglib.util.Vector2i;

public abstract class Shape {
    public abstract void draw(Conversion conversion, Graphics2D g);

    public interface Conversion {
        public Vector2i convert(Vector2d pos);
    }
}
