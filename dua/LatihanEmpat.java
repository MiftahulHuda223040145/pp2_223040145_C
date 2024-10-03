package dua;

import java.awt.event.*;
import javax.swing.*;

public class LatihanEmpat extends JFrame {
    
    public LatihanEmpat() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel labelNama = new JLabel("Input Nama:");
        labelNama.setBounds(130,40,100,10);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(130,60,100,30);
        
        JLabel labelNomor = new JLabel("Input Nomor:");
        labelNomor.setBounds(330,40,100,10);

        JTextField textFieldNomor = new JTextField();
        textFieldNomor.setBounds(330,60,100,30);

        JButton button = new JButton("Klik");
        button.setBounds(130,100,300,40);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(130,150,300,400);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String nomor = textFieldNomor.getText();
                txtOutput.append("Hello "+ nama + "\n");
                txtOutput.append(nomor + "\n");
                txtOutput.append("=======================================");
                textFieldNama.setText("");
                textFieldNomor.setText("");
            }
        });

        this.add(button);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(textFieldNomor);
        this.add(labelNomor);
        this.add(txtOutput);

        this.setSize(1080,1080);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LatihanEmpat h = new LatihanEmpat();
                h.setVisible(true); 
            }
        });
    }
}
