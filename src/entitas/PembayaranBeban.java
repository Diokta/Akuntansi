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
public class PembayaranBeban {
    
    private String id;
    private Date tanggal;
    private String id_beban;
    private String nama_beban;
    private Integer nominal;
    private String keterangan;

    public PembayaranBeban() {
    }
    
    public PembayaranBeban(String id, Date tanggal, String id_beban, Integer nominal, String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.id_beban = id_beban;
        this.nominal = nominal;
        this.keterangan = keterangan;
    }
    
    public String getId(){
        return id;
    }

    public String getNama_beban() {
        return nama_beban;
    }

    public void setNama_beban(String nama_beban) {
        this.nama_beban = nama_beban;
    }
    
    public Date getTanggal(){
        return tanggal;
    }

    public String getIdBeban(){
        return id_beban;
    }

    public Integer getNominal(){
        return nominal;
    }

    public String getKeterangan(){
        return keterangan;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setTanggal(Date tanggal){
        this.tanggal = tanggal;
    }

    public void setIdBeban(String id_beban){
        this.id_beban = id_beban;
    }

    public void setNominal(Integer nominal){
        this.nominal = nominal;
    }

    public void setKeterangan(String keterangan){
        this.keterangan = keterangan;
    }

    public Object getObject(int index){
        switch(index){
            case 0 : return id;
            case 1 : return tanggal;
            case 2 : return id_beban;
            case 3 : return nominal;
            case 4 : return keterangan;
            default : return null;
        }
    }
    
    public Object getObjects(int index){
        switch(index){
            case 0 : return id;
            case 1 : return tanggal;
            case 2 : return nama_beban;
            case 3 : return nominal;
            case 4 : return keterangan;
            default : return null;
        }
    }
}
