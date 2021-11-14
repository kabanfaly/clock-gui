package timer;

import java.util.EventListener;

public interface TimerListener extends EventListener {

    void tick(TimerEvent evt);
}
