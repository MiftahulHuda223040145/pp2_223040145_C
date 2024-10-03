package dua;

import java.awt.event.*;
import javax.swing.*;

public class LatihanTujuh extends JFrame {

    private boolean checkBoxSelected;
    
    public LatihanTujuh() {
        this.checkBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelInput1 = new JLabel("Input Nama:");
        labelInput1.setBounds(15,40,350,10);

        JTextField textField1 = new JTextField();
        textField1.setBounds(15,80,350,30);

        JLabel labelInput2 = new JLabel("Nomor HP:");
        labelInput2.setBounds(15,120,350,10);

        JTextField textField2 = new JTextField();
        textField2.setBounds(15,160,350,30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15,200,350,10);

        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        radioButton1.setBounds(15,240,350,30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan", true);
        radioButton2.setBounds(15,280,350,30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15,320,350,30);

        JButton button = new JButton("Simpan");
        button.setBounds(15,360,100,40);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15,420,350,100);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = "";
                if(radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                if(radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }
                String nama = textField1.getText();
                String nomor = textField2.getText();
                txtOutput.append("Nama:     "+ nama + "\n");
                txtOutput.append("Nomor HP:     "+ nomor + "\n");
                txtOutput.append("Jenis Kelamin:    "+ jenisKelamin + "\n");
                textField1.setText("");

                if(checkBoxSelected) {
                    txtOutput.append("WNA:  Iya");
                }else {
                    txtOutput.append("WNA:  Bukan");
                }
                txtOutput.append("\n==========================================\n");
            }
        });

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange()==1;
            }
        });

        this.add(button);
        this.add(textField1);
        this.add(textField2);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelInput1);
        this.add(labelInput2);
        this.add(checkBox);
        this.add(txtOutput);
        this.setSize(400,500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LatihanTujuh h = new LatihanTujuh();
                h.setVisible(true);
            }
        });
    }
}