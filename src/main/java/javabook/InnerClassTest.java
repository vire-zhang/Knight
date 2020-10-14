package javabook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class InnerClassTest{
    public static void main(String[] args) {
        var clock = new TalkingClock();
        clock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock {

    public void start(int interval, boolean beep) {

        /**
         * 匿名内部类
         */
        var listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is "
                        + Instant.ofEpochMilli(e.getWhen()));
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        };

        /**
         * 局部内部类
         */
        class TimerPrinter implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is "
                        + Instant.ofEpochMilli(e.getWhen()));
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }

        var listener = new TimerPrinter();
        var timer = new Timer(interval, listener);
        timer.start();
    }
}
