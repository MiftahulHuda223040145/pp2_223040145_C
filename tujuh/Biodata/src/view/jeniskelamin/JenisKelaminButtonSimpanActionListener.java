package view.jeniskelamin;

import java.awt.event.*;  
import java.util.UUID;  
import model.JenisKelamin;  
import dao.JenisKelaminDao;  

public class JenisKelaminButtonSimpanActionListener implements ActionListener {  
    private JenisKelaminFrame jenisKelaminFrame;  
    private JenisKelaminDao jenisKelaminDao;  

    public JenisKelaminButtonSimpanActionListener(JenisKelaminFrame jenisKelaminFrame, JenisKelaminDao jenisKelaminDao) 
    {  
        this.jenisKelaminFrame = jenisKelaminFrame;  
        this.jenisKelaminDao = jenisKelaminDao;  
    }  

    @Override  
    public void actionPerformed(ActionEvent e) {  
        String nama = this.jenisKelaminFrame.getNama();  
        JenisKelamin jenisKelamin = new JenisKelamin();  
        jenisKelamin.setId(UUID.randomUUID().toString());  
        jenisKelamin.setNamaJenis(nama);  

        this.jenisKelaminFrame.addJenisKelamin(jenisKelamin);  
        this.jenisKelaminDao.insert(jenisKelamin);  
    }  
}
    