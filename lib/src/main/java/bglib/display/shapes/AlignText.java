package bglib.display.shapes;

import bglib.util.RectType;
import bglib.util.Vector2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.*;

public class AlignText extends Shape {
    private final List<String> text;
    private final Alignment preset;
    private final Color color;
    private final RectType dimension;
    private final Font font;

    private final boolean useConversion;

    private static final int PADDING = 10;

    public enum Alignment {
        TOP_LEFT(-1, -1), TOP_CENTER(0, -1), TOP_RIGHT(1, -1), MID_LEFT(-1, 0), MID_CENTER(0, 0), MID_RIGHT(1, 0),
        BOT_LEFT(-1, 1), BOT_CENTER(0, 1), BOT_RIGHT(1, 1);

        int presetX;
        int presetY;

        Alignment(int presetX, int presetY) {
            this.presetX = presetX;
            this.presetY = presetY;
        }

        // thanks to
        // https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-java
        public Vector2i getXY(RectType dim, FontMetrics metrics, String text) {
            Vector2i dimPos = dim.getPos().round();
            Vector2i dimSize = dim.getSize().round();

            int outputX, outputY;

            if (presetX == -1)
                outputX = PADDING;
            else if (presetX == 1)
                outputX = (dimSize.x - metrics.stringWidth(text) - PADDING);
            else
                outputX = (dimSize.x - metrics.stringWidth(text)) / 2;

            if (presetY == -1)
                outputY = metrics.getAscent() + PADDING;
            else if (presetY == 1)
                outputY = dimSize.y - PADDING;
            else
                outputY = ((dimSize.y - metrics.getHeight()) / 2) + metrics.getAscent();

            return new Vector2i(outputX + dimPos.x, outputY + dimPos.y);
        }

        public int getBaseHeight(int y, FontMetrics metrics, int lineCount) {
            if (presetY == -1)
                return y + PADDING;
            else if (presetY == 1)
                return y - lineCount * (metrics.getHeight() + PADDING);
            else
                return y - lineCount / 2 * (metrics.getHeight() + PADDING);
        }
    };

    public AlignText(String text, Alignment preset, RectType centerDimension, Color c, Font font, boolean useConversion) {
        this(new ArrayList<String>(Arrays.asList(text)), preset, centerDimension, c, font, useConversion);
    }
    public AlignText(String[] text, Alignment preset, RectType centerDimension, Color c, Font font, boolean useConversion) {
        this(Arrays.asList(text), preset, centerDimension, c, font, useConversion);
    }
    public AlignText(List<String> text, Alignment preset, RectType centerDimension, Color color, Font font, boolean useConversion) {
        this.preset = preset;
        this.color = color;
        this.dimension = centerDimension;
        this.text = text;
        this.font = font;
        this.useConversion = useConversion;
    }

    @Override
    public void draw(Conversion conversion, Graphics2D g) {
        RectType draw;

        if (useConversion) {
            Vector2i tempPos = conversion.convert(dimension.getPos());
            draw = new RectType(
                tempPos.asVector2d(),
                conversion.convert(dimension.getPos().add(dimension.getSize()).sub(tempPos)).asVector2d()
            );
        } else
            draw = dimension;

        g.setFont(font);
        g.setColor(color);

        FontMetrics metrics = g.getFontMetrics(font);
        for (int i = 0; i < text.size(); i++) {
            Vector2i pos = preset.getXY(draw, metrics, text.get(i));
            int baseHeight = preset.getBaseHeight(pos.y, metrics, text.size());
            g.drawString(text.get(i), pos.x, baseHeight+i*(g.getFontMetrics(font).getAscent()+PADDING));
        }
    }
}
