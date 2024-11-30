package view.member;

import javax.swing.*;
import java.util.List;
import model.*;
import dao.*;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 20);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 100, 20);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 200, 20);

        // Tombol Simpan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 150, 80, 30);

        // Tombol Hapus
        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(110, 150, 80, 30);

        // Tombol Update
        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(210, 150, 80, 30);

        this.add(labelInput);
        this.add(textFieldNama);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(button);
        this.add(buttonDelete);
        this.add(buttonUpdate);

        this.setSize(400, 500);
        this.setLayout(null);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        // Tambahkan listener ke tombol yang sesuai
        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        MemberButtonDeleteActionListener actionListenerDelete = new MemberButtonDeleteActionListener(this, memberDao);
        buttonDelete.addActionListener(actionListenerDelete);

        MemberButtonUpdateActionListener actionListenerUpdate = new MemberButtonUpdateActionListener(this, memberDao);
        buttonUpdate.addActionListener(actionListenerUpdate);

        this.add(scrollableTable);
    }

    public JTable getTable() {
        return table;
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();  
        comboJenis.removeAllItems();  
        for (JenisMember jenisMember : this.jenisMemberList) {  
            comboJenis.addItem(jenisMember.getNama());  
        } 
    }

    public MemberTableModel getTableModel() {
        return tableModel;
    }
}
