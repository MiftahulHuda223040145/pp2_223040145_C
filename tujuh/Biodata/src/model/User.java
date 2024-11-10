package model;

public class User {
    private String id;
    private String nama;
    private String nomorHP;
    private JenisKelamin jenisKelamin;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNomorHP() {
        return nomorHP;
    }
    public void setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
    }
    public JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }
    public void setJenisKelamin(JenisKelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
