package timer;

public class IListenToTimer implements TimerListener {

    @Override
    public void tick(TimerEvent evt) {
        System.out.println(evt.getTime());
    }
}
