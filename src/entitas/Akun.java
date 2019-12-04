/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitas;

/**
 *
 * @author Fadli Hudaya
 */
public class Akun {

    private String id;
    private String nama;
    private String keterangan;

    public Akun(String id, String nama, String keterangan) {
        this.id = id;
        this.nama = nama;
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Object getObject(int index) {
        switch (index) {
            case 0:
                return id;
            case 1:
                return nama;
            case 2:
                return keterangan;
            default:
                return null;
        }
    }
}
