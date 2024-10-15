package tiga;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LatihanLima extends JFrame {

    private boolean checkBoxSelected;

    public LatihanLima() {
        this.checkBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama dengan GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Header label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 50, 0);
        JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);
        panel.add(headerLabel, gbc);
        
        
        // Input Nama
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 0, 10);
        JLabel labelInput1 = new JLabel("Nama: ");
        panel.add(labelInput1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextField textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(100, 25)); 
        panel.add(textField1, gbc);

        // Input Nomor HP
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel labelInput2 = new JLabel("Nomor HP: ");
        panel.add(labelInput2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JTextField textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(100, 25)); 
        panel.add(textField2, gbc);

        // Jenis Kelamin
        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel labelRadio = new JLabel("Jenis Kelamin: ");
        panel.add(labelRadio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        panel.add(radioButton1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        panel.add(radioButton2, gbc);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Warga Negara Asing Checkbox
        gbc.gridx = 1;
        gbc.gridy = 4;
        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        panel.add(checkBox, gbc);

        // Button Simpan
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Menempatkan tombol di tengah
        gbc.fill = GridBagConstraints.NONE;  // Mencegah tombol mengisi ruang tambahan
        gbc.insets = new Insets(50, 0, 0, 0);
        JButton button = new JButton("Simpan");
        button.setPreferredSize(new Dimension(100, 25)); 
        panel.add(button, gbc);

        // Output
        JTextArea txtOutput = new JTextArea(5, 20);
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollPane.setPreferredSize(new Dimension(350, 150));

        // Action button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = radioButton1.isSelected() ? radioButton1.getText() : radioButton2.getText();
                String nama = textField1.getText();
                String nomor = textField2.getText();
                txtOutput.append("Nama:     " + nama + "\n");
                txtOutput.append("Nomor HP:     " + nomor + "\n");
                txtOutput.append("Jenis Kelamin:    " + jenisKelamin + "\n");

                if (checkBoxSelected) {
                    txtOutput.append("WNA:  Iya\n");
                } else {
                    txtOutput.append("WNA:  Bukan\n");
                }
                txtOutput.append("==========================================\n");
            }
        });

        // Checkbox listener
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange() == 1;
            }
        });

        // Menambahkan panel dan header ke frame
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.SOUTH);
        
        this.setSize(400, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LatihanLima h = new LatihanLima();
                h.setVisible(true);
            }
        });
    }
}
