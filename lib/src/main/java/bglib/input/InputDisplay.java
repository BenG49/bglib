package bglib.input;

import java.awt.Color;

import bglib.display.Display;
import bglib.util.Vector2d;

import com.stuypulse.stuylib.util.chart.KeyTracker;
import com.stuypulse.stuylib.util.chart.MouseTracker;

public class InputDisplay extends Display {
    private KeyTracker keyboard;
    private MouseTracker mouse;
    private MouseClick mouseClicks;

    public InputDisplay() { this(500, 500, Color.WHITE, ""); }
    public InputDisplay(Color background) { this(500, 500, background, ""); }
    public InputDisplay(int width, int height, Color background, String name) {
        super(width, height, background, name);

        keyboard = new KeyTracker();
        mouse = new MouseTracker(this);
        mouseClicks = new MouseClick();

        addMouseListener(mouseClicks);
        addKeyListener(keyboard);
    }

    public boolean hasKey(String key) {
        if (key.contains(" "))
            return hasKeyChord(key);
        else
            return keyboard.hasKey(key);
    }

    private boolean hasKeyChord(String chord) {
        String[] keys = chord.split(" ");
        for (String i : keys)
            if (!hasKey(i))
                return false;
            
        return true;
    }
    
    public Vector2d getMousePos(boolean menu) {
        if (menu)
            return new Vector2d(mouse.getMouseX(), 1-mouse.getMouseY()*(1f/0.975f));
        else
            return new Vector2d(mouse.getMouseX(), 1-mouse.getMouseY());
    }

    public boolean getButtonPressed(int button) {
        return mouseClicks.getButtonPressed(button);
    }

    public boolean anyButtonsPressed() {
        return mouseClicks.anyButtonsPressed();
    }
}