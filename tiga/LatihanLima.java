package tiga;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LatihanLima extends JFrame{

    private boolean checkBoxSelected;

    public LatihanLima() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        //Label
        JLabel headerLabel = new JLabel("Form Biodata");

        //Panel
        JPanel controPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setSize(800, 800);
        GridBagLayout gbc = new GridBagLayout();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        // Label dan Field Nama
        JLabel labelInput1 = new JLabel("Input Nama:");
        labelInput1.setBounds(15, 40, 350, 10);

        JTextField textField1 = new JTextField();
        textField1.setBounds(15, 60, 350, 30);

        // Label dan Input Nomor HP
        JLabel labelInput2 = new JLabel("Nomor HP:");
        labelInput2.setBounds(15, 100, 350, 10);

        JTextField textField2 = new JTextField();
        textField2.setBounds(15, 120, 350, 30);

        // Label dan Radio pilihan Jenis Kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 160, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        radioButton1.setBounds(15, 180, 100, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(120, 180, 120, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Label dan List Pilihan Jenis Tabungan
        JLabel labelTabungan = new JLabel("Pilih Jenis Tabungan:");
        labelTabungan.setBounds(15, 220, 350, 10);

        String[] tabunganOptions = {"Tabungan Biasa", "Deposito", "Tabungan Haji", "Giro"};
        JList<String> listTabungan = new JList<>(tabunganOptions);
        listTabungan.setBounds(15, 240, 350, 80);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Label dan Inputan Frekuensi transaksi per bulan
        JLabel labelTransaksi = new JLabel("Frekuensi Transaksi per Bulan:");
        labelTransaksi.setBounds(15, 330, 350, 10);

        SpinnerNumberModel transaksiModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner transaksiSpinner = new JSpinner(transaksiModel);
        transaksiSpinner.setBounds(15, 350, 120, 30);

        // Label dan Field Password dan Konfirmasi Password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(15, 390, 350, 10);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(15, 410, 150, 30);

        JLabel labelConfirmPassword = new JLabel("Konfirmasi Password:");
        labelConfirmPassword.setBounds(15, 450, 350, 10);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(15, 470, 150, 30);

        // Label dan Inputan Tanggal Lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 510, 350, 10);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        dateSpinner.setBounds(15, 530, 150, 30);

        // Checkbox
        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15,560,350,30);

        // Button Simpan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 600, 100, 40);

        // Output
        JLabel labelOutput = new JLabel("Output");
        labelOutput.setBounds(500,40, 350, 10);

        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(500, 60, 350, 500);

        // Button Actions
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textField1.getText();
                String nomor = textField2.getText();
                String jenisKelamin = radioButton1.isSelected() ? radioButton1.getText() : radioButton2.getText();
                String jenisTabungan = listTabungan.getSelectedValue();
                int frekuensi = (int) transaksiSpinner.getValue();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                Date tanggalLahir = (Date) dateSpinner.getValue();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                checkBoxSelected = checkBox.isSelected();
                String statusWNA = checkBoxSelected ? "WNA:        Iya" : "WNA:        Bukan";

                if (!password.equals(confirmPassword)) {
                    txtOutput.append("Password dan Konfirmasi Password tidak cocok! \n");
                } else {
                    txtOutput.append("Nama:        " + nama + "\n"
                        + "Nomor HP:        " + nomor + "\n"
                        + "Jenis Kelamin:       " + jenisKelamin + "\n"
                        + "Jenis Tabungan:      " + jenisTabungan + "\n"
                        + "Frekuensi Transaksi:     " + frekuensi + " kali/bulan\n"
                        + "Tanggal Lahir:       " + sdf.format(tanggalLahir) + "\n"
                        + statusWNA + "\n"
                        + "Password:        cocok!\n"
                        + "=============================================================================\n");
                }
            }
        });

        // Menu Reset dan Exit Actions
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                bg.clearSelection();
                listTabungan.clearSelection();
                transaksiSpinner.setValue(1);
                passwordField.setText("");
                confirmPasswordField.setText("");
                dateSpinner.setValue(new Date());
                txtOutput.setText("");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(labelInput1);
        this.add(textField1);
        this.add(labelInput2);
        this.add(textField2);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelTabungan);
        this.add(listTabungan);
        this.add(labelTransaksi);
        this.add(transaksiSpinner);
        this.add(labelPassword);
        this.add(passwordField);
        this.add(labelConfirmPassword);
        this.add(confirmPasswordField);
        this.add(labelTanggalLahir);
        this.add(dateSpinner);
        this.add(checkBox);
        this.add(button);
        this.add(labelOutput);
        this.add(txtOutput);

        this.setSize(900, 800);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LatihanLima t = new LatihanLima();
                t.setVisible(true); 
            }
        });
    }
}

