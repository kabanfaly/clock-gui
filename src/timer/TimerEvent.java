package timer;

import java.util.EventObject;

public class TimerEvent extends EventObject {

    private long time;

    public TimerEvent(Object source) {
        this(source, System.currentTimeMillis());
    }

    public TimerEvent(Object source, long time) {
        super(source);
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
