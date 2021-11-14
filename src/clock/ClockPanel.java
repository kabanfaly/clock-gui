package clock;

import java.awt.*;
import javax.swing.*;

public class ClockPanel extends JPanel implements Runnable {

    private long hour;
    private long minute;
    private long second;
    private final JPanel panelDigit;
    private final JLabel label;
    private final static int RAYON = 150;
    private final double angle = Math.PI / 30; // increment d'angle 
    private double angleH; // angle de rotation de l'aiguille des heures
    private double angleM;// angle de rotation de l'aiguille des minutes
    private double angleS; // angle de rotation de l'aiguille des secondes 
    private boolean start; // commmande pour arrêt et reprise
    private boolean clock; // pour l'affichage de l'horloge
    private boolean digit;   // pour l'affichage chronometre numerique 
    private long sleep;
    private Thread th = new Thread(this);

    public ClockPanel(long hour, long minute, long second) {

        panelDigit = new JPanel();
        label = new JLabel();
        this.setLayout(new GridLayout(1, 1));
        //this.add(panelClock);
        panelDigit.add(label);
        this.add(panelDigit);

        start = false;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        clock = digit = true;
        /* initialisation de angles */
        angleH = Math.PI * 3 / 2;
        angleM = Math.PI * 3 / 2;
        angleS = Math.PI * 3 / 2;
        sleep = 1000;// intervalle de temps
        th.start();
    }

    @Override
    public void paint(Graphics g) {

        int rootX = getWidth() / 2;
        int rootY = getHeight() / 2;

        int xOval = rootX - RAYON; //Pour dessiner le cerle
        int yOval = rootY - RAYON;

        Font font = new Font("Times New Roman", 2, 15);

        g.setFont(font);
        if (clock) {
            g.setColor(Color.blue);
        } else {
            g.setColor(getBackground());
        }

        g.drawOval(xOval, yOval, 2 * RAYON, 2 * RAYON);
        g.fillOval(xOval, yOval, 2 * RAYON, 2 * RAYON);

        if (clock) {
            g.setColor(Color.white);
        } else {
            g.setColor(getBackground());
        }

        for (int i = 1; i <= 12; i++) {// Pour dessiner les chiffres dans l'horloge
            double ang = i * Math.PI / 6 - Math.PI / 2;
            int x = rootX + (int) ((RAYON - 10) * Math.cos(ang));
            int y = rootY + (int) ((RAYON - 10) * Math.sin(ang));

            g.drawString("" + i, x, y);
        }
        if (clock) {
            g.setColor(Color.black); // pour dessiner l'aiguille des secondes
        } else {
            g.setColor(getBackground());
        }

        int xS = rootX + (int) (RAYON * 0.7 * Math.cos(angleS));
        int yS = rootY + (int) (RAYON * 0.7 * Math.sin(angleS));

        g.drawLine(rootX, rootY, xS, yS);

        if (clock) {
            g.setColor(Color.green);// pour dessiner l'aiguille des minutes
        } else {
            g.setColor(this.getBackground());
        }

        int xM = rootX + (int) (RAYON * 0.6 * Math.cos(angleM));
        int yM = rootY + (int) (RAYON * 0.6 * Math.sin(angleM));

        g.drawLine(rootX, rootY, xM, yM);

        if (clock) {
            g.setColor(Color.yellow);// pour dessiner l'aiguille des heures
        } else {
            g.setColor(getBackground());
        }

        int xH = rootX + (int) (RAYON * 0.4 * Math.cos(angleH));
        int yH = rootY + (int) (RAYON * 0.4 * Math.sin(angleH));
        g.drawLine(rootX, rootY, xH, yH);

        if (second == 60) {
            if (clock) {
                g.setColor(Color.green);
            } else {
                g.setColor(getBackground());
            }

            angleM += angle;
            xM = rootX + (int) (RAYON * 0.6 * Math.cos(angleM));
            yM = rootY + (int) (RAYON * 0.6 * Math.sin(angleM));
            g.drawLine(rootX, rootY, xM, yM);

            minute++;
            second = 0;
        }

        if (minute == 60) {
            minute = 0;
            hour++;
            angleH += 5 * angle;
            if (clock) {
                g.setColor(Color.yellow);
            } else {
                g.setColor(getBackground());
            }
            xH = rootX + (int) (RAYON * 0.4 * Math.cos(angleH));
            yH = rootY + (int) (RAYON * 0.4 * Math.sin(angleH));
            g.drawLine(rootX, rootY, xH, yH);
        }

        if (hour == 24) {
            hour = 0;
        }

        if (digit) //pour dessine le chronometre digital	 		
        {
            g.setColor(Color.black);
        } else {
            g.setColor(getBackground());
        }

        g.drawRect(22, 18, 140, 40);
        g.fillRect(22, 18, 140, 40);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", 3, 20));
        g.drawString(formatDigit(hour) + ":" + formatDigit(minute) + ":" + formatDigit(second), 40, 40);

        angleS += angle;
        second++;
    }
    
    private String formatDigit(long digit) {
        return String.format("%2d", digit).replace(" ", "0");
    }

    @Override
    public void run() {
        while (start) {

            try {
                repaint();

                th.sleep(sleep);

            } catch (InterruptedException ie) {
                System.out.println("interruption");
            }
        }
    }

    public void start() {
        setHourMinuteSecond(hour, minute, second);
        start = true;
        th = new Thread(this);
        th.start();
    }

    public void stop() {
        start = false;
    }

    public void reset() {
        stop();
        start = true;
        hour = minute = second = 0;
        angleH = Math.PI * 3 / 2;
        angleM = Math.PI * 3 / 2;
        angleS = Math.PI * 3 / 2;

        start();
    }

    public void setHourMinuteSecond(long hour, long minute, long second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    /*pour activer ou desactiver le l'horloge  */
    public void setClock(boolean tf) {
        clock = tf;
    }

    /*pour activer ou desactiver le chronometre numerique*/
    public void setDigit(boolean tf) {
        digit = tf;
    }

    public void setSleep(long newSleep) {
        sleep = newSleep;
    }

}
