package enam;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

public class peminjamanBank extends JFrame {

    private boolean checkBoxSelected;
    private DefaultTableModel model;
    private JTable table;
    private int rowCount = 1;

    public peminjamanBank() {
        this.checkBoxSelected = false;
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

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Header label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 50, 0);
        JLabel headerLabel = new JLabel("Peminjaman Uang", JLabel.CENTER);
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
        gbc.insets = new Insets(0, 10, 30, 0);
        JTextField textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(100, 25));
        panel.add(textField1, gbc);

        // Jenis Kelamin
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel labelRadio = new JLabel("Jenis Kelamin: ");
        panel.add(labelRadio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        panel.add(radioButton1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 10, 30, 0);
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        panel.add(radioButton2, gbc);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Input NIK
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel labelInput2 = new JLabel("NIK: ");
        panel.add(labelInput2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 10, 30, 0);
        JTextField textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(100, 25));
        panel.add(textField2, gbc);

        // Input Nomor HP
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel labelInput3 = new JLabel("Nomor HP: ");
        panel.add(labelInput3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 30, 0);
        JTextField textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(100, 25));
        panel.add(textField3, gbc);

        // Input Alamat
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel labelInput4 = new JLabel("Alamat: ");
        panel.add(labelInput4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 30, 0);
        JTextField textField4 = new JTextField();
        textField4.setPreferredSize(new Dimension(100, 25));
        panel.add(textField4, gbc);

        // Input Jumlah Peminjaman
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel labelJP = new JLabel("Jumlah Peminjaman");
        panel.add(labelJP, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 30, 0);
        SpinnerNumberModel modelJP = new SpinnerNumberModel(100000, 100000, 100000000, 50000);
        JSpinner spinnerJP = new JSpinner(modelJP);
        panel.add(spinnerJP, gbc);

        // Input Tenor
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel inputTenor = new JLabel("Tenor");
        panel.add(inputTenor, gbc);

        String[] items = { "1 Bulan", "12 Bulan", "18 Bulan", "24 Bulan", "36 Bulan", "48 Bulan", "60 Bulan" };
        JList<String> list = new JList<>(items);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 30, 0);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(100, 25));
        panel.add(scrollPane, gbc);

        // Input Penghasilan Bulanan
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 10, 0, 0);
        DecimalFormat rupiahFormat = new DecimalFormat("Rp #,##0.00");
        JLabel valueLabel = new JLabel("Penghasilan Bulanan: -+ Rp 0.00");
        panel.add(valueLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 10, 30, 0);
        JSlider slider = new JSlider(0, 10000000, 0);
        slider.setMajorTickSpacing(1000000);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        panel.add(slider, gbc);

        // Tujuan Peminjaman
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel inputTujuanPeminjaman = new JLabel("Tujuan Peminjaman");
        panel.add(inputTujuanPeminjaman, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 30, 0);
        String[] tujuanPeminjamanOptions = { "Pembelian Aset", "Modal Usaha", "Haji"};
        JList<String> listTujuanPeminjaman = new JList<>(tujuanPeminjamanOptions);
        listTujuanPeminjaman.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tujuanScrollPane = new JScrollPane(listTujuanPeminjaman);
        tujuanScrollPane.setPreferredSize(new Dimension(100, 25));
        panel.add(tujuanScrollPane, gbc);

        // Checkbox Persyaratan
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 10, 30, 0);
        JCheckBox checkBox = new JCheckBox("Peminjam Menyetujui Syarat dan Ketentuan");
        panel.add(checkBox, gbc);

        // Button Simpan
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        JButton button = new JButton("Simpan");
        button.setPreferredSize(new Dimension(100, 25));
        panel.add(button, gbc);
        
        // Catatan Tambahan
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 0, 0);
        JLabel labelCatatan = new JLabel("Catatan Tambahan:");
        panel.add(labelCatatan, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 10, 30, 0);
        JTextArea textAreaCatatan = new JTextArea(4, 20);
        textAreaCatatan.setLineWrap(true);
        textAreaCatatan.setWrapStyleWord(true);
        JScrollPane scrollPaneCatatan = new JScrollPane(textAreaCatatan);
        panel.add(scrollPaneCatatan, gbc);


        // Menu Reset dan Exit Actions
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                bg.clearSelection();
                list.clearSelection();
                listTujuanPeminjaman.clearSelection();
                spinnerJP.setValue(100000);
                slider.setValue(0);
                valueLabel.setText("Nilai: 0");
            }
        });

        // JSlider Listener
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                double value = slider.getValue();
                valueLabel.setText("Penghasilan Bulanan: -+ " + rupiahFormat.format(value));
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Data get
        String[][] data = {
            { "Nama", textField1.getText() },
            { "Jenis Kelamin", radioButton1.isSelected() ? "Laki-laki" : "Perempuan" },
            { "NIK", textField2.getText() },
            { "Nomor HP", textField3.getText() },
            { "Alamat", textField4.getText() },
            { "Jumlah Peminjaman", String.valueOf(spinnerJP.getValue()) },
            { "Tenor", list.getSelectedValue() },
            { "Penghasilan Bulanan", rupiahFormat.format(slider.getValue()) },
            { "Tujuan Peminjaman", listTujuanPeminjaman.getSelectedValue() }
        };

        //JTable dengan data
        String[] columns = { "No", "Keterangan", "Detail" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(780, 150));
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!checkBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "peminjam harus menyetujui syarat dan ketentuan!");
                    return;
                }
                        
                String nama = textField1.getText();
                String jenisKelamin = radioButton1.isSelected() ? "Laki-laki" : "Perempuan";
                String nik = textField2.getText();
                String nomorHP = textField3.getText();
                String alamat = textField4.getText();
                String jumlahPeminjaman = String.valueOf(spinnerJP.getValue());
                String tenor = list.getSelectedValue();
                String penghasilanBulanan = rupiahFormat.format(slider.getValue());
                String tujuanPeminjaman = listTujuanPeminjaman.getSelectedValue();
                String catatanTambahan = textAreaCatatan.getText();

                // Memasukkan data ke tabel
                model.addRow(new Object[] { rowCount++, "Nama", nama });
                model.addRow(new Object[] { "", "Jenis Kelamin", jenisKelamin });
                model.addRow(new Object[] { "", "NIK", nik });
                model.addRow(new Object[] { "", "Nomor HP", nomorHP });
                model.addRow(new Object[] { "", "Alamat", alamat });
                model.addRow(new Object[] { "", "Jumlah Peminjaman", jumlahPeminjaman });
                model.addRow(new Object[] { "", "Tenor", tenor });
                model.addRow(new Object[] { "", "Penghasilan Bulanan", penghasilanBulanan });
                model.addRow(new Object[] { "", "Tujuan Peminjaman", tujuanPeminjaman });
                model.addRow(new Object[] { "", "Catatan Tambahan", catatanTambahan });
                model.addRow(new Object[] { "", "", "" });
            }
        });

        // Menambahkan panel ke frame
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        peminjamanBank.this.add(tableScrollPane, BorderLayout.SOUTH);
        this.add(tableScrollPane, BorderLayout.SOUTH);

        this.setSize(800, 600);
        this.setVisible(true);
    }
    


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                peminjamanBank h = new peminjamanBank();
                h.setVisible(true);
            }
        });
    }
}
