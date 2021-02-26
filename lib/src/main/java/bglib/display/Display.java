package bglib.display;

import bglib.display.shapes.Shape;
import bglib.display.shapes.Shape.Conversion;
import bglib.util.RectType;
import bglib.util.Vector2d;
import bglib.util.Vector2i;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame {
    public final int WIDTH;
    public final int HEIGHT;

    private Color background;
    private List<Shape> frameShapes;

    protected Draw currentDraw;

    public Display() { this(500, 500, Color.WHITE, ""); }
    public Display(Color background) { this(500, 500, background, ""); }
    public Display(int width, int height, Color background, String name) {
        super(name);

        WIDTH = width;
        HEIGHT = height;
        this.background = background;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        getContentPane().setBackground(background);

        frameShapes = new ArrayList<Shape>();
    }

    public void frameAdd(Shape shape) {
        frameShapes.add(shape);
    }

    public void draw() {
        draw((pos) -> (pos));
    }
    public void draw(Conversion conversion) {
        try {
            remove(currentDraw);
        // using general exception to catch NullPointer and "AWT-EventQueue-0" errors
        } catch(Exception e) {}

        Draw d = new Draw(frameShapes, background, conversion);
        currentDraw = d;
       
        add(d);
        revalidate();
        frameShapes = new ArrayList<Shape>();
    }

    public void setInternalBackground(Color background) {
        this.background = background;

        getContentPane().setBackground(background);
    }

    public Vector2i getDSize() {
        return new Vector2i(WIDTH, HEIGHT);
    }

    public RectType getDimensions() {
        return new RectType(
            Vector2d.ORIGIN, getDSize().asVector2d()
        );
    }

    private class Draw extends JPanel {
        private final List<Shape> shapes;
        private final Conversion conversion;

        public Draw(List<Shape> shapes, Color background, Conversion conversion) {
            this.shapes = shapes;
            this.conversion = conversion;

            // setPreferredSize(new Dimension(1, 1));
            setBackground(background);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            for (Shape i : shapes) {
                i.draw(conversion, g2);
            }
        }
    }
}