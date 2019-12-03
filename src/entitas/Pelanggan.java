/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitas;

public class Pelanggan {

    private String id;
    private String nama;
    private String jenis_kelamin;
    private String alamat;
    private String no_ktp;

    public Pelanggan(String id, String nama, String jenis_kelamin, String alamat, String no_ktp) {
        this.id = id;
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.no_ktp = no_ktp;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKelamin() {
        return jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoKtp() {
        return no_ktp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoKtp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public Object getObject(int index) {
        switch (index) {
            case 0:
                return id;
            case 1:
                return nama;
            case 2:
                return jenis_kelamin;
            case 3:
                return alamat;
            case 4:
                return no_ktp;
            default:
                return null;
        }
    }
}
