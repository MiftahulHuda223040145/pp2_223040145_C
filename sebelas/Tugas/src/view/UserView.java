package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class UserView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtNRP = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnAdd = new JButton("Add User");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private JTable userTable = new JTable();
    private DefaultTableModel tableModel;

    public UserView() {
        setTitle("Presensi");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel atas untuk input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name :"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("NRP :"));
        inputPanel.add(txtNRP);
        inputPanel.add(new JLabel("Email :"));
        inputPanel.add(txtEmail);

        // Panel bawah untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "NRP", "Email"}, 0);
        userTable.setModel(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(userTable);

        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow != -1) {
                    txtName.setText((String) tableModel.getValueAt(selectedRow, 1));
                    txtNRP.setText((String) tableModel.getValueAt(selectedRow, 2));
                    txtEmail.setText((String) tableModel.getValueAt(selectedRow, 3));
                }
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public String getNameInput() {
        return txtName.getText();
    }

    public String getNRPInput() {
        return txtNRP.getText();
    }

    public String getEmailInput() {
        return txtEmail.getText();
    }

    public void setUserList(Object[][] data) {
        tableModel.setRowCount(0); // Clear table
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public int getSelectedRow() {
        return userTable.getSelectedRow();
    }

    public Object getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }

    public void addAddUserListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshUserListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addUpdateUserListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteUserListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
}
