package pkg2048;

import java.awt.Dimension;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Pham Nhat Quang
 */
public class Clock {

    private final JLabel time;
    private final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    private int currentHour;
    private int currentMinute;
    private int currentSecond;
    private Calendar calendar;
    java.util.Timer timer;

    /**
     *
     * @param time
     */
    public Clock(JLabel time) {
        String times[] = time.getText().split(":");
        this.currentHour = Integer.parseInt(times[0]);
        this.currentMinute = Integer.parseInt(times[1]);
        this.currentSecond = Integer.parseInt(times[2]);
        this.time = time;

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Clock clock = new Clock(new JLabel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(clock.time);
        frame.pack();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setVisible(true);
        clock.start();
    }

    /**
     *
     */
    public void reset() {
        currentSecond = 0;
        currentMinute = 0;
        currentHour = 0;
    }

    /**
     *
     */
    public void start() {
        reset();
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentSecond == 60) {
                    currentMinute++;
                    if (currentMinute == 60) {
                        currentHour++;
                        currentMinute = 0;
                    }
                    currentSecond = 0;
                }
                time.setText(String.format("%02d:%02d:%02d", currentHour, currentMinute, currentSecond));
                currentSecond++;
            }

        }, 0, 1000);
    }

    /**
     *
     */
    public void pause() {
        try {
            timer.cancel();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     *
     */
    public void resume() {
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentSecond == 60) {
                    currentMinute++;
                    if (currentMinute == 60) {
                        currentHour++;
                        currentMinute = 0;
                    }
                    currentSecond = 0;
                }
                time.setText(String.format("%02d:%02d:%02d", currentHour, currentMinute, currentSecond));
                currentSecond++;
            }

        }, 0, 1000);
    }

    /**
     *
     * @return
     */
    public int getTime() {
        return this.currentHour * 3600 + this.currentMinute * 60 + this.currentSecond;
    }

    /**
     *
     * @return
     */
    public JLabel getLabel() {
        return this.time;
    }

}
