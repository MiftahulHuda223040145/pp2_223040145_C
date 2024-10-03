package satu;

import javax.swing.*;
import java.awt.*;

public class HelloMiftahulHuda {
    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Hello, Miftahul Huda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello, Miftahul Huda");
        frame.getContentPane().add(label);
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main (String [] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                createAndShowGUI();
            }
        });
    }
}