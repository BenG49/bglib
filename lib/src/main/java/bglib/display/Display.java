package bglib.display;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import bglib.display.shapes.Shape;

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
        try {
            remove(currentDraw);
        // using general exception to catch NullPointer and "AWT-EventQueue-0" errors
        } catch(Exception e) {}

        Draw d = new Draw(frameShapes, background);
        currentDraw = d;
       
        add(d);
        revalidate();
        frameShapes = new ArrayList<Shape>();
    }

    public void setInternalBackground(Color background) {
        this.background = background;

        getContentPane().setBackground(background);
    }
}