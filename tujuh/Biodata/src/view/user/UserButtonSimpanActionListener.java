package view.user;

import java.awt.event.*;
import java.util.UUID;
import model.*;
import dao.UserDao;

public class UserButtonSimpanActionListener implements ActionListener {
    private UserFrame userFrame;
    private UserDao userDao;

    public UserButtonSimpanActionListener(UserFrame userFrame, UserDao userDao) {
        this.userFrame = userFrame;
        this.userDao = userDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.userFrame.getNama();
        String nomorHP = this.userFrame.getNomorHP();

        if (nama.isEmpty()) {
            this.userFrame.showAlert("Nama tidak boleh kosong");
        } else if (nomorHP.isEmpty()) {
            this.userFrame.showAlert("Nomor HP tidak boleh kosong");
        } else {
            JenisKelamin jenisKelamin = this.userFrame.getJenisKelamin();
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setNama(nama);
            user.setJenisKelamin(jenisKelamin);
            user.setNomorHP(nomorHP);

            this.userFrame.addUser(user);
            this.userDao.insert(user);
        }
    }
}
