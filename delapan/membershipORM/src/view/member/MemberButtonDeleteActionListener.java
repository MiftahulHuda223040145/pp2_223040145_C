package view.member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Member;
import dao.MemberDao;

public class MemberButtonDeleteActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonDeleteActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = memberFrame.getTable().getSelectedRow();
        if (selectedRow == -1) {
            memberFrame.showAlert("Pilih data yang ingin dihapus.");
            return;
        }

        // Ambil member dari baris yang dipilih
        Member member = memberFrame.getTableModel().get(selectedRow);

        // Konfirmasi Penghapusan
        int confirm = JOptionPane.showConfirmDialog(
            memberFrame,
            "Apakah anda yakin ingin menghapusnya?",
            "Message",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Hapus dari database
            memberDao.delete(member);

            // Hapus dari model tabel
            memberFrame.getTableModel().removeRow(selectedRow);
            memberFrame.showAlert("Data berhasil dihapus.");
        }
    }
}
