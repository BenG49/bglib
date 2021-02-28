package bglib.display;

import bglib.display.shapes.Shape;
import bglib.display.shapes.Shape.Conversion;
import bglib.util.RectType;
import bglib.util.Vector2d;
import bglib.util.Vector2i;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame {
    public final int WIDTH;
    public final int HEIGHT;

    private List<Shape> frameShapes;
    private Draw panel;

    public Display() { this(500, 500, Color.WHITE, ""); }
    public Display(Color background) { this(500, 500, background, ""); }
    public Display(int width, int height, Color background, String name) {
        super(name);

        WIDTH = width;
        HEIGHT = height;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        getContentPane().setBackground(background);

        frameShapes = new ArrayList<Shape>();
        panel = new Draw(background);
        add(panel);
    }

    public void frameAdd(Shape shape) {
        frameShapes.add(shape);
    }

    public void draw() {
        draw((pos) -> (pos.floor()));
    }
    public void draw(Conversion conversion) {
        panel.removeAll();
        panel.setShapes(frameShapes);
        panel.setConversion(conversion);
        panel.revalidate();
        panel.repaint();

        revalidate();
        frameShapes = new ArrayList<Shape>();
    }

    public void setInternalBackground(Color background) {
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
        private List<Shape> shapes;
        private Conversion conversion;

        public Draw(Color background) {
            this(new ArrayList<Shape>(), background, (pos) -> (pos.floor()));
        }
        public Draw(List<Shape> shapes, Color background, Conversion conversion) {
            this.shapes = shapes;
            this.conversion = conversion;

            setBackground(background);
        }

        public void setShapes(List<Shape> shapes) {
            this.shapes = shapes;
        }

        public void setConversion(Conversion conversion) {
            this.conversion = conversion;
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