package controller;

import java.awt.*;
import javax.swing.*;

public class ClockController {

    private final JFrame frame = new JFrame("Buttons");
    private Container contentPane = null;
    private JPanel container = null;
    private final JButton buttonStart = new JButton("Start");
    private final JButton buttonStop = new JButton("Stop");
    private final JButton buttonReset = new JButton("Reset");
    private final JButton buttonClock = new JButton("Clock");
    private final JButton buttonDigit = new JButton("Digital");
    private final JButton buttonClockDigit = new JButton("Digit_Clock");

    private clock.Clock horloge;
    protected timer.Timer timer;

    public ClockController(long timer) {
        this.horloge = new clock.Clock();

        this.timer = new timer.Timer(timer);
        contentPane = frame.getContentPane();
        contentPane.add(makeFrame());
        frame.setPreferredSize(new Dimension(250, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel makeFrame() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Speed");
        menuBar.add(fileMenu);

        JMenuItem itemUn100Sec = new JMenuItem("1/100 sec"); // intervalle de temps à 1/100 de seconde
        fileMenu.add(itemUn100Sec);
        itemUn100Sec.addActionListener(e -> {
            horloge.getClock().setSleep(10);
            timer.setSleep(10);
        });

        JMenuItem itemSec = new JMenuItem("1/10 sec"); // intervalle de temps à 1/10 de seconde
        fileMenu.add(itemSec);
        itemSec.addActionListener(e -> {
            horloge.getClock().setSleep(100);
            timer.setSleep(100);
        });

        JMenuItem item1sec = new JMenuItem("1 sec");  // intervalle de temps à 1 de seconde
        fileMenu.add(item1sec);
        item1sec.addActionListener(e -> {
            horloge.getClock().setSleep(1000);
            timer.setSleep(1000);
        });

        JMenuItem item5sec = new JMenuItem("5 sec"); // intervalle de temps à 5 de secondes
        fileMenu.add(item5sec);
        item5sec.addActionListener(e -> {
            horloge.getClock().setSleep(5000);
            timer.setSleep(5000);
        });
        container = new JPanel();
        container.setLayout(new GridLayout(3, 3));

        container.add(buttonStart);
        buttonStart.addActionListener(e -> {
            timer.start();
            horloge.getClock().start();
        });
        container.add(buttonStop);
        buttonStop.addActionListener(e -> {
            timer.stop();
            horloge.getClock().stop();
        });
        container.add(buttonReset);
        buttonReset.addActionListener(e -> {
            timer.reset();
            horloge.getClock().reset();
        });

        container.add(buttonClock);
        buttonClock.addActionListener(e -> {
            horloge.getClock().setClock(true);
            horloge.getClock().setDigit(false);
        });
        container.add(buttonDigit);
        buttonDigit.addActionListener(e -> {
            horloge.getClock().setClock(false);
            horloge.getClock().setDigit(true);
        });
        container.add(buttonClockDigit);
        buttonClockDigit.addActionListener(e -> {
            horloge.getClock().setClock(true);
            horloge.getClock().setDigit(true);
        });
        return container;

    }
}
