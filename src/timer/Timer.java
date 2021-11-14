package timer;

import java.util.ArrayList;

public class Timer implements Runnable {

    private Thread runner;
    public final static long ZZZ = 100;
    private long zzz = ZZZ;
    private ArrayList<TimerListener> listeners = null;
    private long zeroTime = 0;
    private long initTimer = 0; // pour memoriser le zeroTimer
    private boolean start = true;
    private boolean stop = false;

    public Timer() {
        this(ZZZ);
        runner = new Thread(this);
    }

    public Timer(long zzz) {
        super();
        this.zzz = zzz;
        listeners = new ArrayList<>();
    }

    public void addTimerListener(TimerListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void removeTimerListener(TimerListener l) {
        int index = listeners.indexOf(l);
        if (index != -1) {
            listeners.remove(index);
        }
    }

    private void fireTimerEvent(TimerEvent evt) {
        listeners.forEach(listener -> {
            listener.tick(evt);
        });
    }

    /**
     * cette methode fait tourner le timer de façon continue à chaque instant on
     * affiche à travers la methode fireTimerEvent la difference de temps entre
     * le temps final qui change à chaque tour de boucle tant que start est à
     * vrai et le temps initial enregistré avant l'entrée dans la boucle. Quand
     * on invoque la methode stop(), start est mis à faux et stop à vrai et
     * quand doit recommencer en invoquant la methode start pour continuer le
     * temps initial est memorisé avec la variable initTimer pour permettre de
     * continuer à l'endroi d'arrêt. Quand reset() est invoqué celà reprend tout
     * à zero.
     */
    @Override
    public void run() {
        /* initialize time at start of execution*/
        zeroTime = System.currentTimeMillis(); // debut
        /* loops forever */
        while (start) {
            try {
                fireTimerEvent(new TimerEvent(this, System.currentTimeMillis() - (stop ? initTimer : zeroTime)));
                runner.sleep(zzz);
            } catch (InterruptedException ie) {
                System.out.println("interruption");
            }
        }
    }

    public void start() {
        initTimer = zeroTime; // On memorise le temps initial pour pouvoir continuer quand il y aura l'evenement "Stop"      	
        runner = new Thread(this);
        start = true;
        runner.start();
    }

    public void stop() {
        start = false;// On arrete le thread
        stop = true; // On met stop a true pour pouvoir continuer au même endroit

    }

    public void reset() {
        stop();
        stop = false;
        this.runner = null;
        start();
    }

    public void setSleep(long newSleep) {
        zzz = newSleep;
    }

}
