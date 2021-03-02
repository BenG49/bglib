package bglib.input;

import java.awt.Color;
import java.awt.LayoutManager;
import java.util.HashMap;

import bglib.display.Display;
import bglib.util.Vector2d;

import com.stuypulse.stuylib.util.chart.MouseTracker;

public class InputDisplay extends Display {
    private TypingTracker tracker;
    private MouseTracker mouse;
    private MouseClick mouseClicks;

    public InputDisplay() { this(500, 500, Color.WHITE, ""); }
    public InputDisplay(Color background) { this(500, 500, background, ""); }
    public InputDisplay(int width, int height, Color background, String name) {
        this(width, height, background, name, Display.DEFAULT_MGR);
    }
    public InputDisplay(int width, int height, Color background, String name, LayoutManager manager) {
        super(width, height, background, name);

        mouse = new MouseTracker(this);
        mouseClicks = new MouseClick();
        tracker = new TypingTracker(new HashMap<String, InputEvent>());

        addMouseListener(mouseClicks);
        addKeyListener(tracker.keyboard);
    }
    
    public Vector2d getMousePos() {
        return new Vector2d(mouse.getMouseX(), 1-mouse.getMouseY());
    }
    
    public Vector2d getMousePosMenu() {
        return new Vector2d(mouse.getMouseX(), 1-mouse.getMouseY()*(1f/0.975f));
    }

    public boolean getButtonPressed(int button) {
        return mouseClicks.getButtonPressed(button);
    }

    public boolean anyButtonsPressed() {
        return mouseClicks.anyButtonsPressed();
    }

    public void setTrackerEvents(HashMap<String, InputEvent> keyEvents) {
        tracker.setKeyEvents(keyEvents);
    }

    public void checkKeys() {
        tracker.checkKeys();
    }

    public boolean hasKey(String key) {
        return tracker.hasKey(key);
    }
}