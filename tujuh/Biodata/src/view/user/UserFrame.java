package view.user;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import model.*;
import dao.UserDao;
import dao.JenisKelaminDao;

public class UserFrame extends JFrame {
    private List<JenisKelamin> jenisKelaminList;
    private List<User> userList;
    private JTextField textFieldNama;
    private JTextField textFieldHP; 
    private UserTableModel tableModel;
    private JComboBox<String> comboJenis;
    private UserDao userDao;
    private JenisKelaminDao jenisKelaminDao;

    public UserFrame(UserDao userDao, JenisKelaminDao jenisKelaminDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.userDao = userDao;
        this.jenisKelaminDao = jenisKelaminDao;
        this.userList = this.userDao.findAll();
        this.jenisKelaminList = this.jenisKelaminDao.findAll();

        JLabel labelInput1 = new JLabel("Nama:");
        labelInput1.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 20);

        JLabel labelInput2 = new JLabel("Nomor HP:");
        labelInput2.setBounds(15, 90, 350, 10);

        textFieldHP = new JTextField();
        textFieldHP.setBounds(15, 110, 350, 20);

        JLabel labelJenis = new JLabel("Jenis Kelamin:");
        labelJenis.setBounds(15, 140, 100, 20);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 160, 200, 20);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 220, 80, 30);

        this.add(labelInput1);
        this.add(textFieldNama);
        this.add(labelInput2);
        this.add(textFieldHP);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(button);

        this.setSize(400, 500);
        this.setLayout(null);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 270, 350, 150);

        tableModel = new UserTableModel(userList);
        table.setModel(tableModel);

        UserButtonSimpanActionListener actionListener = new UserButtonSimpanActionListener(this, userDao);
        button.addActionListener(actionListener);

        this.add(scrollableTable);
    }

    public void populateComboJenis() {
        this.jenisKelaminList = this.jenisKelaminDao.findAll();
        comboJenis.removeAllItems();
        for (JenisKelamin jenisKelamin : this.jenisKelaminList) {
            comboJenis.addItem(jenisKelamin.getNamaJenis());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public String getNomorHP() {
        return textFieldHP.getText();
    }

    public JenisKelamin getJenisKelamin() {
        return jenisKelaminList.get(comboJenis.getSelectedIndex());
    }

    public void addUser(User user) {
        tableModel.add(user);
        textFieldNama.setText("");
        textFieldHP.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
