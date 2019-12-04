/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitas;

import java.util.Date;

/**
 *
 * @author Fadli Hudaya
 */
public class Perusahaan {

    private String id;
    private String nama;
    private String email;
    private String noTelp;
    private String alamat;
    private int thnBuku;
    private int blnAkhir;
    private int blnAwal;
    private String password;
    private int periode;

    public Perusahaan(String id, String nama, String email, String noTelp, String alamat, 
            int thnBuku, int blnAkhir, int blnAwal, int periode, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.thnBuku = thnBuku;
        this.blnAkhir = blnAkhir;
        this.blnAwal = blnAwal;
        this.periode = periode;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNoTelp() {
        return noTelp;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public int getThnBuku() {
        return thnBuku;
    }
    
    public int getBlnAkhir() {
        return blnAkhir;
    }
    
    public int getBlnAwal() {
        return blnAwal;
    }
    
    public String getPassword() {
        return password;
    }
    
    public int getPeriode() {
        return periode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setThnBuku(int thnBuku) {
        this.thnBuku = thnBuku;
    }

    public void setBlnAkhir(int blnAkhir) {
        this.blnAkhir = blnAkhir;
    }

    public void setBlnAwal(int blnAwal) {
        this.blnAwal = blnAwal;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public Object getObject(int index) {
        switch (index) {
            case 0:
                return id;
            case 1:
                return nama;
            case 2:
                return email;
            case 3:
                return noTelp;
            case 4:
                return alamat;
            case 5:
                return thnBuku;
            case 6:
                return blnAkhir;
            case 7:
                return blnAwal;
            case 8:
                return periode;
            case 9:
                return password;
            default:
                return null;
        }
    }

    public Object getObjects(int index) {
        switch (index) {
            case 0:
                return id;
            case 1:
                return nama;
            case 2:
                return email;
            case 3:
                return noTelp;
            case 4:
                return alamat;
            case 5:
                return thnBuku;
            case 6:
                return blnAkhir;
            case 7:
                return blnAwal;
            case 8:
                return periode;
            case 9:
                return password;
            default:
                return null;
        }
    }
}
