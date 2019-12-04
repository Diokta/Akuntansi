/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionUtility;
import entitas.Penyewaan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fadli
 */
public class PenyewaanModel {

    private Connection con;
    private List<Penyewaan> list;

    public PenyewaanModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<Penyewaan> getAll() {
        try {
            String sql = "SELECT p.no_faktur, p.tanggal, pe.nama, pr.nama, p.lama_sewa, "
                    + "p.total FROM penyewaan p, pelanggan pe, produk_sewa pr "
                    + "WHERE p.id_produk_sewa = pr.id AND p.id_pelanggan = pe.id ORDER BY p.tanggal";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                Penyewaan penyewaan = new Penyewaan();
                penyewaan.setNoFaktur(resultSet.getString(1));
                penyewaan.setTanggal(resultSet.getDate(2));
                penyewaan.setNama_pelanggan(resultSet.getString(3));
                penyewaan.setNama_produk(resultSet.getString(4));
                penyewaan.setLamaSewa(resultSet.getInt(5));
                penyewaan.setTotal(resultSet.getInt(6));
                list.add(penyewaan);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public Penyewaan getPenyewaan(String no_faktur) {
        Penyewaan penyewaan = new Penyewaan();
        try {
            String sql = "SELECT p.no_faktur, p.tanggal, pe.nama, pr.nama, p.lama_sewa, "
                    + "p.total FROM penyewaan p, pelanggan pe, produk_sewa pr "
                    + "WHERE p.id_produk_sewa = pr.id AND p.id_pelanggan = pe.id AND p.no_faktur='" + no_faktur + "' ORDER BY p.tanggal";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                penyewaan.setNoFaktur(resultSet.getString(1));
                penyewaan.setTanggal(resultSet.getDate(2));
                penyewaan.setNama_pelanggan(resultSet.getString(3));
                penyewaan.setNama_produk(resultSet.getString(4));
                penyewaan.setLamaSewa(resultSet.getInt(5));
                penyewaan.setTotal(resultSet.getInt(6));
            }
        } catch (SQLException ex) {
            System.out.println("Error Disini " + ex.getMessage());
        }
        return penyewaan;
    }

    public String getNo_faktur() {
        String no_faktur = "";
        String sql = "SELECT no_faktur FROM penyewaan ORDER BY no_faktur DESC";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                no_faktur = resultSet.getString(1);
            }
        } catch (SQLException e) {

        }
        return no_faktur;
    }

    public String[] getNamaPelanggan() {
        String[] kode;
        String sql = "SELECT nama FROM pelanggan";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.last();
            kode = new String[resultSet.getRow()];
            resultSet.beforeFirst();
            int i = 0;
            while (resultSet.next()) {
                kode[i] = resultSet.getString(1);
                i++;
            }
            return kode;
        } catch (SQLException e) {
            return null;
        }
    }

    public String[] getNamaProduk() {
        String[] kode;
        String sql = "SELECT nama FROM produk_sewa";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.last();
            kode = new String[resultSet.getRow()];
            resultSet.beforeFirst();
            int i = 0;
            while (resultSet.next()) {
                kode[i] = resultSet.getString(1);
                i++;
            }
            return kode;
        } catch (SQLException e) {
            return null;
        }
    }

    public String getIdPelanggan(String nama) {
        String id = "";
        String sql = "SELECT id FROM pelanggan WHERE nama='" + nama + "'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
        } catch (SQLException e) {

        }
        return id;
    }

    public String getIdProduk(String nama) {
        String id = "";
        String sql = "SELECT id FROM produk_sewa WHERE nama='" + nama + "'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
        } catch (SQLException e) {

        }
        return id;
    }
    
    public String getNamaProduk(String id) {
        String namaProduk = "";
        String sql = "SELECT nama FROM produk_sewa WHERE id='" + id + "'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                namaProduk = resultSet.getString(1);
            }
        } catch (SQLException e) {

        }
        return namaProduk;
    }

    public Integer getHargaSewa(String id) {
        Integer harga = 0;
        String sql = "SELECT harga_sewa FROM produk_sewa WHERE id='" + id + "'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                harga = resultSet.getInt(1);
            }
        } catch (SQLException e) {

        }
        return harga;
    }

    public boolean insert(Penyewaan penyewaan) {
        String sql1 = "INSERT INTO penyewaan (no_faktur, tanggal, id_pelanggan, id_produk_sewa, lama_sewa, total) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO jurnal (tanggal, ref, keterangan, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?)";
        String sql3 = "INSERT INTO buku_besar (tanggal, ref, keterangan, nama_akun, sort, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql4 = "INSERT INTO laba_rugi (tanggal, ref, kelompok, nama_akun, nominal) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement prepare = null;
        PreparedStatement prepare2 = null;
        PreparedStatement prepare3 = null;
        PreparedStatement prepare4 = null;
        PreparedStatement prepare5 = null;
        PreparedStatement prepare6 = null;
        try {
            prepare = con.prepareStatement(sql1);
            prepare2 = con.prepareStatement(sql2);
            prepare3 = con.prepareStatement(sql2);
            prepare4 = con.prepareStatement(sql3);
            prepare5 = con.prepareStatement(sql3);
            prepare6 = con.prepareStatement(sql4);
            con.setAutoCommit(false);
            for (int i = 0; i < 6; i++) {
                prepare.setObject(i + 1, penyewaan.getObject(i));
            }
            prepare2.setObject(1, penyewaan.getTanggal());
            prepare2.setObject(2, penyewaan.getNoFaktur());
            prepare2.setObject(3, "Kas");
            prepare2.setObject(4, penyewaan.getTotal());
            prepare2.setObject(5, 0);
            
            prepare3.setObject(1, penyewaan.getTanggal());
            prepare3.setObject(2, penyewaan.getNoFaktur());
            prepare3.setObject(3, "          Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()) + " " + penyewaan.getLamaSewa() + " bulan");
            prepare3.setObject(4, 0);
            prepare3.setObject(5, penyewaan.getTotal());
            
            prepare4.setObject(1, penyewaan.getTanggal());
            prepare4.setObject(2, penyewaan.getNoFaktur());
            prepare4.setObject(3, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()) + " " + penyewaan.getLamaSewa() + " bulan");
            prepare4.setObject(4, "Kas");
            prepare4.setObject(5, 1);
            prepare4.setObject(6, penyewaan.getTotal());
            prepare4.setObject(7, 0);
            
            prepare5.setObject(1, penyewaan.getTanggal());
            prepare5.setObject(2, penyewaan.getNoFaktur());
            prepare5.setObject(3, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()) + " " + penyewaan.getLamaSewa() + " bulan");
            prepare5.setObject(4, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()));
            prepare5.setObject(5, 2);
            prepare5.setObject(6, 0);
            prepare5.setObject(7, penyewaan.getTotal());
            
            prepare6.setObject(1, penyewaan.getTanggal());
            prepare6.setObject(2, penyewaan.getNoFaktur());
            prepare6.setObject(3, "Pendapatan");
            prepare6.setObject(4, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()));
            prepare6.setObject(5, penyewaan.getTotal());
            
            prepare.executeUpdate();
            prepare2.executeUpdate();
            prepare3.executeUpdate();
            prepare4.executeUpdate();
            prepare5.executeUpdate();
            prepare6.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Insert Data\n" + ex);
            try {
                con.rollback();
                prepare.close();
                prepare2.close();
                prepare3.close();
                prepare4.close();
                prepare5.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
            }
            return false;
        }
        return true;
    }

    public boolean update(Penyewaan penyewaan) {
        String sql = "UPDATE penyewaan SET tanggal=?, id_pelanggan=?, id_produk_sewa=?, lama_sewa=?, total=? WHERE no_faktur=?";
        String sql2 = "INSERT INTO jurnal (tanggal, ref, keterangan, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?)";
        String sql3 = "INSERT INTO buku_besar (tanggal, ref, keterangan, nama_akun, sort, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql4 = "INSERT INTO laba_rugi (tanggal, ref, kelompok, nama_akun, nominal) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement prepare = null;
        PreparedStatement prepare2 = null;
        PreparedStatement prepare3 = null;
        PreparedStatement prepare4 = null;
        PreparedStatement prepare5 = null;
        PreparedStatement prepare6 = null;
        try {
            prepare = con.prepareStatement(sql);
            prepare2 = con.prepareStatement(sql2);
            prepare3 = con.prepareStatement(sql2);
            prepare4 = con.prepareStatement(sql3);
            prepare5 = con.prepareStatement(sql3);
            prepare6 = con.prepareStatement(sql4);
            con.setAutoCommit(false);
            for (int i = 1; i < 6; i++) {
                prepare.setObject(i, penyewaan.getObject(i));
                prepare.setObject(6, penyewaan.getNoFaktur());
            }
            prepare2.setObject(1, penyewaan.getTanggal());
            prepare2.setObject(2, penyewaan.getNoFaktur());
            prepare2.setObject(3, "Kas");
            prepare2.setObject(4, penyewaan.getTotal());
            prepare2.setObject(5, 0);
            
            prepare3.setObject(1, penyewaan.getTanggal());
            prepare3.setObject(2, penyewaan.getNoFaktur());
            prepare3.setObject(3, "          Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()) + " " + penyewaan.getLamaSewa() + " bulan");
            prepare3.setObject(4, 0);
            prepare3.setObject(5, penyewaan.getTotal());
            
            prepare4.setObject(1, penyewaan.getTanggal());
            prepare4.setObject(2, penyewaan.getNoFaktur());
            prepare4.setObject(3, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()) + " " + penyewaan.getLamaSewa() + " bulan");
            prepare4.setObject(4, "Kas");
            prepare4.setObject(5, 1);
            prepare4.setObject(6, penyewaan.getTotal());
            prepare4.setObject(7, 0);
            
            prepare5.setObject(1, penyewaan.getTanggal());
            prepare5.setObject(2, penyewaan.getNoFaktur());
            prepare5.setObject(3, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()) + " " + penyewaan.getLamaSewa() + " bulan");
            prepare5.setObject(4, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()));
            prepare5.setObject(5, 2);
            prepare5.setObject(6, 0);
            prepare5.setObject(7, penyewaan.getTotal());
            
            prepare6.setObject(1, penyewaan.getTanggal());
            prepare6.setObject(2, penyewaan.getNoFaktur());
            prepare6.setObject(3, "Pendapatan");
            prepare6.setObject(4, "Penyewaan " + getNamaProduk(penyewaan.getIdProduk_sewa()));
            prepare6.setObject(5, penyewaan.getTotal());
            
            prepare.executeUpdate();
            deleteJurnal(penyewaan.getNoFaktur());
            deleteBukuBesar(penyewaan.getNoFaktur());
            deleteLabaRugi(penyewaan.getNoFaktur());
            prepare2.executeUpdate();
            prepare3.executeUpdate();
            prepare4.executeUpdate();
            prepare5.executeUpdate();
            prepare6.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Update Data\n" + ex);
            try {
                con.rollback();
                prepare.close();
                prepare2.close();
                prepare3.close();
                prepare4.close();
                prepare5.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
            }
            return false;
        }
        return true;
    }

    public boolean deletePenyewaan(String no_faktur) {
        String sql = "DELETE FROM penyewaan WHERE no_faktur=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, no_faktur);
            prepare.executeUpdate();
            deleteJurnal(no_faktur);
            deleteBukuBesar(no_faktur);
            deleteLabaRugi(no_faktur);
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Delete Data\n" + ex);
            try {
                con.rollback();
                prepare.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
            }
            return false;
        }
        return true;
    }
    
    public boolean deleteJurnal(String ref) {
        String sql = "DELETE FROM jurnal WHERE ref=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, ref);
            prepare.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Delete Data\n" + ex);
            try {
                con.rollback();
                prepare.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
            }
            return false;
        }
        return true;
    }
    
    public boolean deleteBukuBesar(String ref) {
        String sql = "DELETE FROM buku_besar WHERE ref=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, ref);
            prepare.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Delete Data\n" + ex);
            try {
                con.rollback();
                prepare.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
            }
            return false;
        }
        return true;
    }
    
    public boolean deleteLabaRugi(String ref) {
        String sql = "DELETE FROM laba_rugi WHERE ref=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, ref);
            prepare.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Delete Data\n" + ex);
            try {
                con.rollback();
                prepare.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
            }
            return false;
        }
        return true;
    }
}
