package bglib.input;

import java.util.HashMap;
import java.util.HashSet;

import com.stuypulse.stuylib.util.chart.KeyTracker;

import bglib.input.InputEvent.EventTime;

public class TypingTracker {
    protected KeyTracker keyboard;
    private HashSet<String> lastKeysPressed;
    private HashMap<String, Integer> repeatCounters;

    private HashMap<String, InputEvent> keyEvents;

    public TypingTracker(HashMap<String, InputEvent> keyEvents) {
        this.keyEvents = keyEvents;

        keyboard = new KeyTracker();
        repeatCounters = new HashMap<String, Integer>();
        lastKeysPressed = new HashSet<String>();
    }

    public void checkKeys() {
        for (String key : keyEvents.keySet()) {
            if (canBePressed(key, keyEvents.get(key)))
                keyEvents.get(key).run();
        }
    }

    public void setKeyEvents(HashMap<String, InputEvent> keyEvents) {
        this.keyEvents = new HashMap<String, InputEvent>(keyEvents);
    }

    protected boolean hasKey(String key) {
        if (key.contains(" "))
            return hasKeyChord(key);
        else
            return keyboard.hasKey(key);
    }

    private boolean hasKeyChord(String chord) {
        String[] keys = chord.split(" ");
        for (String i : keys)
            if (!keyboard.hasKey(i))
                return false;
            
        return true;
    }

    private boolean canBePressed(String in, InputEvent event) {
        String key = in.toUpperCase();
        EventTime time = event.getEventType().getTime();

        boolean out = false;
        
        if (hasKey(key)) {
            if (time == EventTime.REPEAT) {
                if(!lastKeysPressed.contains(key))
                    repeatCounters.put(key, 0);
                else
                    repeatCounters.replace(key, repeatCounters.get(key)+1);
            }

            lastKeysPressed.add(key);
        } else {
            if (time == EventTime.REPEAT && lastKeysPressed.contains(key))
                repeatCounters.remove(key);
            lastKeysPressed.remove(key);
        }

        if (time == EventTime.PRESS && hasKey(key) && !lastKeysPressed.contains(key))
            out = true;
        else if (time == EventTime.RELEASE && !hasKey(key) && lastKeysPressed.contains(key))
            out = true;
        else if (time == EventTime.REPEAT && hasKey(key) && (repeatCounters.get(key) % event.getEventType().getRepMod()) == 0)
            out = true;

        return out;
    }

}
