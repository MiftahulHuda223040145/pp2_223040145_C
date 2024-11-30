package view.jenismember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dao.JenisMemberDao;
import model.JenisMember;

public class JenisMemberButtonDeleteActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonDeleteActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

@Override
public void actionPerformed(ActionEvent e) {
    int selectedRow = jenisMemberFrame.getTable().getSelectedRow();
    if (selectedRow == -1) {
        jenisMemberFrame.showAlert("Pilih data yang ingin dihapus.");
        return;
    }

    // Ambil member dari baris yang dipilih
    JenisMember jenisMember = jenisMemberFrame.getTableModel().get(selectedRow);

    // Konfirmasi Penghapusan
    int confirm = JOptionPane.showConfirmDialog(
        jenisMemberFrame,
        "Apakah anda yakin ingin menghapusnya?",
        "Message",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        // Hapus dari database
        jenisMemberDao.delete(jenisMember);

        // Hapus dari model tabel
        jenisMemberFrame.getTableModel().removeRow(selectedRow);
        jenisMemberFrame.showAlert("Data berhasil dihapus.");
    }
}
}