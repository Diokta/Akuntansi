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
public class ProdukSewa {
    
    private String id;
    private String nama;
    private String spesifikasi;
    private Integer harga_sewa;

    public ProdukSewa(String id, String nama, String spesifikasi, Integer harga_sewa) {
        this.id = id;
        this.nama = nama;
        this.spesifikasi = spesifikasi;
        this.harga_sewa = harga_sewa;
    }
    
    public String getId(){
        return id;
    }

    public String getNama(){
        return nama;
    }

    public String getSpesifikasi(){
        return spesifikasi;
    }

    public Integer getHargaSewa(){
        return harga_sewa;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setSpesifikasi(String spesifikasi){
        this.spesifikasi = spesifikasi;
    }

    public void setHargaSewa(Integer harga_sewa){
        this.harga_sewa = harga_sewa;
    }

    public Object getObject(int index){
        switch(index){
            case 0 : return id;
            case 1 : return nama;
            case 2 : return spesifikasi;
            case 3 : return harga_sewa;
            default : return null;
        }
    }
}
