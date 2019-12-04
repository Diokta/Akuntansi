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
public class Pengembalian {
    
    private String id;
    private String id_penyewaan;
    private Date tanggal_dikembalikan;
    private Integer lama_telat;
    private Integer total_denda;

    public Pengembalian(String id, String id_penyewaan, Date tanggal_dikembalikan, Integer lama_telat, Integer total_denda) {
        this.id = id;
        this.id_penyewaan = id_penyewaan;
        this.tanggal_dikembalikan = tanggal_dikembalikan;
        this.lama_telat = lama_telat;
        this.total_denda = total_denda;
    }
    
    public String getId(){
        return id;
    }

    public String getIdPenyewaan(){
        return id_penyewaan;
    }

    public Date getTanggalDikembalikan(){
        return tanggal_dikembalikan;
    }

    public Integer getLamaTelat(){
        return lama_telat;
    }

    public Integer getTotalDenda(){
        return total_denda;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setIdPenyewaan(String id_penyewaan){
        this.id_penyewaan = id_penyewaan;
    }

    public void setTanggalDikembalikan(Date tanggal_dikembalikan){
        this.tanggal_dikembalikan = tanggal_dikembalikan;
    }

    public void setLamaTelat(Integer lama_telat){
        this.lama_telat = lama_telat;
    }

    public void setTotalDenda(Integer total_denda){
        this.total_denda = total_denda;
    }

    public Object getObject(int index){
        switch(index){
            case 0 : return id;
            case 1 : return id_penyewaan;
            case 2 : return tanggal_dikembalikan;
            case 3 : return lama_telat;
            case 4 : return total_denda;
            default : return null;
        }
    }
}
