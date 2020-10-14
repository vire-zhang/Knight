package javabook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Instant;

/**
 * 方法引用中使用this、super
 */
public class Greeter {
    public void greet(ActionEvent e) {
        System.out.println("Hello, the time is " + Instant.ofEpochMilli(e.getWhen()));
    }

    public static void main(String[] args) {
        new RepeatedGreeter();
    }
}

class RepeatedGreeter extends Greeter {
    public void greet(ActionEvent e) {
        var timer = new Timer(1000, super::greet);
        timer.start();
    }
}
