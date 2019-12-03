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
public class Penyewaan {

    private String no_faktur;
    private Date tanggal;
    private String id_pelanggan;
    private String nama_pelanggan;
    private String id_produk_sewa;
    private String nama_produk;
    private Integer lama_sewa;
    private Integer total;

    public Penyewaan() {
    }

    public Penyewaan(String no_faktur, Date tanggal, String id_pelanggan, String id_produk_sewa, Integer lama_sewa, Integer total) {
        this.no_faktur = no_faktur;
        this.tanggal = tanggal;
        this.id_pelanggan = id_pelanggan;
        this.id_produk_sewa = id_produk_sewa;
        this.lama_sewa = lama_sewa;
        this.total = total;
    }

    public String getNoFaktur() {
        return no_faktur;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }
    
    public String getIdPelanggan() {
        return id_pelanggan;
    }

    public String getIdProduk_sewa() {
        return id_produk_sewa;
    }

    public Integer getLamaSewa() {
        return lama_sewa;
    }

    public Integer getTotal() {
        return total;
    }

    public void setNoFaktur(String no_faktur) {
        this.no_faktur = no_faktur;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setIdPelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public void setIdProduk_sewa(String id_produk_sewa) {
        this.id_produk_sewa = id_produk_sewa;
    }

    public void setLamaSewa(Integer lama_sewa) {
        this.lama_sewa = lama_sewa;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getObject(int index) {
        switch (index) {
            case 0:
                return no_faktur;
            case 1:
                return tanggal;
            case 2:
                return id_pelanggan;
            case 3:
                return id_produk_sewa;
            case 4:
                return lama_sewa;
            case 5:
                return total;
            default:
                return null;
        }
    }
    
    public Object getObjects(int index) {
        switch (index) {
            case 0:
                return no_faktur;
            case 1:
                return tanggal;
            case 2:
                return nama_pelanggan;
            case 3:
                return nama_produk;
            case 4:
                return lama_sewa;
            case 5:
                return total;
            default:
                return null;
        }
    }
}
