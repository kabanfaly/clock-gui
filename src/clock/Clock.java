package clock;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Clock {

    private JFrame frame = new JFrame("Timer");
    private ClockPanel clock = null;

    public Clock() {

        clock = new ClockPanel(0, 0, 0);

        frame.setContentPane(clock);

        frame.setLocationRelativeTo(null);//on centre la fenetre sur l'ecran
        frame.setPreferredSize(new Dimension(500, 500));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public ClockPanel getClock() {
        return clock;
    }
}
