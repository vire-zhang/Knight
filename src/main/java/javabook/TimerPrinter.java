package javabook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;

public class TimerPrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is "
            + Instant.ofEpochMilli(e.getWhen()));
        Toolkit.getDefaultToolkit().beep();
    }

    public static void main(String[] args) {
        var listener = new TimerPrinter();

        ActionListener listener1 = e -> System.out.println("At the tone, the time is "
                + Instant.ofEpochMilli(e.getWhen()));

        var timer = new Timer(1000, e -> System.out.println("The time is "
                + new Date()));
        timer.start();

        var timer1 = new Timer(1000, System.out::println);
        timer1.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
