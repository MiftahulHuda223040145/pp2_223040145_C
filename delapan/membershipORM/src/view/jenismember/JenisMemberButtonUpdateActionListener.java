package view.jenismember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.JenisMember;
import dao.JenisMemberDao;

public class JenisMemberButtonUpdateActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonUpdateActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jenisMemberFrame.getTable().getSelectedRow();
        if (selectedRow == -1) {
            jenisMemberFrame.showAlert("Pilih member yang ingin diupdate!");
            return;
        }

        String nama = jenisMemberFrame.getNama();
        if (nama.isEmpty()) {
            jenisMemberFrame.showAlert("Masukkan nama member!");
            return;
        }

        JenisMember selectedJenisMember = jenisMemberFrame.getTableModel().get(selectedRow);

        selectedJenisMember.setNama(nama);
        jenisMemberFrame.getTableModel().update(selectedRow, selectedJenisMember);
        jenisMemberDao.update(selectedJenisMember);

        jenisMemberFrame.showAlert("Jenis Member berhasil diupdate!");
    }
}
