package view.user;

import javax.swing.table.*;
import java.util.List;
import model.User;

class UserTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Kelamin", "No HP", "Status Kewarganegaraan"};
    private List<User> data;

    public UserTableModel(List<User> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        User rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getNama();
            case 1:
                return rowItem.getJenisKelamin().getNamaJenis();
            case 2:
                return rowItem.getNomorHP();
            default:
                return "";
        }
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(User value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
