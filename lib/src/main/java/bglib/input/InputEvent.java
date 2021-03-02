package bglib.input;

public interface InputEvent {
    public void run();
    public EventType getEventType();

    public enum EventTime { PRESS, REPEAT, NORMAL, RELEASE };

    public class EventType {
        private EventTime time;
        private int repMod;

        private static final int DEFAULT_REP_MOD = 75;

        public EventType(EventTime time) { this(time, DEFAULT_REP_MOD); }
        public EventType(EventTime time, int repMod) {
            this.time = time;
            this.repMod = repMod;
        }

        public EventTime getTime() {
            return time;
        }

        public int getRepMod() {
            return repMod;
        }
    }
}
