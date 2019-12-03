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
    private Date tglMulai;
    private Date tglAkhir;
    private String password;

    public Perusahaan(String id, String nama, String email, String noTelp, String alamat, Date tglMulai, Date tglAkhir, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.tglMulai = tglMulai;
        this.tglAkhir = tglAkhir;
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
    
    public Date getTglMulai() {
        return tglMulai;
    }
    
    public Date getTglAkhir() {
        return tglAkhir;
    }
    
    public String getPassword() {
        return password;
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

    public void setTglMulai(Date tglMulai) {
        this.tglMulai = tglMulai;
    }

    public void setTglAkhir(Date tglAkhir) {
        this.tglAkhir = tglAkhir;
    }

    public void setPassword(String password) {
        this.password = password;
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
                return tglMulai;
            case 6:
                return tglAkhir;
            case 7:
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
                return tglMulai;
            case 6:
                return tglAkhir;
            case 7:
                return password;
            default:
                return null;
        }
    }
}
