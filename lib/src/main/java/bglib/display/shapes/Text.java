package bglib.display.shapes;

import bglib.util.Vector2d;
import bglib.util.Vector2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.*;
import java.awt.Graphics2D;

public class Text extends Shape {
    private final List<String> text;
    private final Vector2d pos;
    private final Color color;
    private final Font font;

    private final boolean useConversion;

    private static final int PADDING = 10;

    public Text(String text, Vector2i pos, Color color, Font font, boolean useConversion) {
        this(new ArrayList<String>(Arrays.asList(text)), pos.asVector2d(), color, font, useConversion);
    }
    public Text(String text, Vector2d pos, Color color, Font font, boolean useConversion) {
        this(new ArrayList<String>(Arrays.asList(text)), pos, color, font, useConversion);
    }
    public Text(String[] text, Vector2d pos, Color color, Font font, boolean useConversion) {
        this(Arrays.asList(text), pos, color, font, useConversion);
    }
    public Text(List<String> text, Vector2d pos, Color color, Font font, boolean useConversion) {
        this.text = text;
        this.pos = pos;
        this.color = color;
        this.font = font;
        this.useConversion = useConversion;
    }

    @Override
    public void draw(Conversion conversion, Graphics2D g) {
        Vector2i draw;
        if (useConversion)
            draw = conversion.convert(pos);
        else
            draw = pos.floor();

        g.setFont(font);
        g.setColor(color);
        int fontHeight = g.getFontMetrics(font).getAscent();
        int baseHeight = draw.y;

        for (int i = 0; i < text.size(); i++)
            g.drawString(text.get(i), draw.x, baseHeight+i*(fontHeight+PADDING));
    }
}
