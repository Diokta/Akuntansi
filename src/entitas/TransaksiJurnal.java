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
public class TransaksiJurnal {
    
    private String id_akun;
    private String id_jurnal;
    private Date tanggal;
    private int debit;
    private int kredit;
    private String keterangan;
    private String nama_akun;

    public TransaksiJurnal() {
    }

    public String getNama_akun() {
        return nama_akun;
    }

    public void setNama_akun(String nama_akun) {
        this.nama_akun = nama_akun;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public TransaksiJurnal(String id_akun, String id_jurnal, Date tanggal, int debit, int kredit, String keterangan) {
        this.id_akun = id_akun;
        this.id_jurnal = id_jurnal;
        this.tanggal = tanggal;
        this.debit = debit;
        this.kredit = kredit;
        this.keterangan = keterangan;
    }

    public String getId_akun() {
        return id_akun;
    }

    public void setId_akun(String id_akun) {
        this.id_akun = id_akun;
    }

    public String getId_jurnal() {
        return id_jurnal;
    }

    public void setId_jurnal(String id_jurnal) {
        this.id_jurnal = id_jurnal;
    }

    public String getKeterangan(){
        return keterangan;
    }

    public void setKeterangan(String keterangan){
        this.keterangan = keterangan;
    }

    public Object getObject(int index){
        switch(index){
            case 0 : return id_akun;
            case 1 : return id_jurnal != null ? id_jurnal : nama_akun;
            case 2 : return debit;
            case 3 : return kredit;
            case 4 : return keterangan;
            default : return null;
        }
    }
    
    public Object getObjects(int index){
        switch(index){
            case 0 : return id_akun;
            case 1 : return id_jurnal != null ? id_jurnal : nama_akun;
            case 2 : return debit;
            case 3 : return kredit;
            case 4 : return keterangan;
            default : return null;
        }
    }
}
