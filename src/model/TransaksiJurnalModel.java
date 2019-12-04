/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionUtility;
import entitas.TransaksiJurnal;
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
public class TransaksiJurnalModel {

    private Connection con;
    private List<TransaksiJurnal> list;

    public TransaksiJurnalModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<TransaksiJurnal> getAll(String idJurnal) {
        try {
            String sql = "SELECT t.id_akun, a.nama, t.debit, t.kredit, t.keterangan FROM transaksi t, akun a WHERE t.id_jurnal = '"+idJurnal+"' AND t.id_akun=a.id;";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                TransaksiJurnal transaksi_jurnal = new TransaksiJurnal();
                transaksi_jurnal.setId_akun(resultSet.getString(1));
                transaksi_jurnal.setNama_akun(resultSet.getString(2));
                transaksi_jurnal.setDebit(Integer.parseInt(resultSet.getString(3)));
                transaksi_jurnal.setKredit(Integer.parseInt(resultSet.getString(4)));
                transaksi_jurnal.setKeterangan(resultSet.getString(5));
                list.add(transaksi_jurnal);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public TransaksiJurnal getTransaksiJurnal(String id) {
        TransaksiJurnal pembayaran_beban = new TransaksiJurnal();
//        try {
//            String sql = "SELECT t.id_akun, a.nama, t.debit, t.kredit, t.keterangan "
//                    + "FROM transaksi t, jurnal j, akun a WHERE pb.id_beban=b.id AND pb.id=?";
//            PreparedStatement prepare = con.prepareStatement(sql);
//            prepare.setString(1, id);
//            ResultSet resultSet = prepare.executeQuery();
//            if (resultSet.next()) {
//                pembayaran_beban.setId(resultSet.getString(1));
//                pembayaran_beban.setTanggal(resultSet.getDate(2));
//                pembayaran_beban.setKeterangan(resultSet.getString(3));
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error Disni" + ex);
//        }
        return pembayaran_beban;
    }

    public String getId() {
        String id = "";
        String sql = "SELECT id FROM jurnal ORDER BY id DESC";
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
    
    public String getIdAkun(String nama) {
        String id = "";
        String sql = "SELECT id FROM akun WHERE nama='" + nama + "'";
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

    public String[] getNamaAkun() {
        String[] kode;
        String sql = "SELECT nama FROM akun";
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
    
    public String getNamaAkun(String id) {
        String nama = "";
        String sql = "SELECT nama FROM akun where id = '"+id+"'";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                nama = resultSet.getString(1);
            }
            return nama;
        } catch (SQLException e) {
            return null;
        }
    }

    public String getIdBeban(String nama) {
        String id = "";
        String sql = "SELECT id FROM beban WHERE nama='" + nama + "'";
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

    public String getNamaBeban(String id) {
        String namaProduk = "";
        String sql = "SELECT nama FROM beban WHERE id='" + id + "'";
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

    public boolean insert(TransaksiJurnal transaksi_jurnal) {
        String sql2 = "INSERT INTO jurnal (id, tanggal, keterangan, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?)";
        String sql3 = "INSERT INTO transaksi (id_akun, id_jurnal, debit, kredit, keterangan) "
                + "VALUES (?, ?, ?, ?, ?)";
        String sql4 = "INSERT INTO buku_besar (tanggal, ref, keterangan, nama_akun, sort, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepare2 = null;
        PreparedStatement prepare3 = null;
        PreparedStatement prepare4 = null;
        PreparedStatement prepare5 = null;
        
        try {
            prepare2 = con.prepareStatement(sql2);
            prepare2.setObject(1, transaksi_jurnal.getId_jurnal());
            prepare2.setObject(2, transaksi_jurnal.getTanggal());
            prepare2.setObject(3, (transaksi_jurnal.getKredit() > 0 ? "          " : "") + transaksi_jurnal.getKeterangan());
            prepare2.setObject(4, transaksi_jurnal.getDebit());
            prepare2.setObject(5, transaksi_jurnal.getKredit());

            prepare2.executeUpdate();
        } catch (SQLException e) {
            
        }
        
        try {
            prepare3 = con.prepareStatement(sql3);
            prepare4 = con.prepareStatement(sql4);
            prepare5 = con.prepareStatement(sql4);
            con.setAutoCommit(false);
            
            prepare3.setObject(1, transaksi_jurnal.getId_akun());
            prepare3.setObject(2, transaksi_jurnal.getId_jurnal());
            prepare3.setObject(3, transaksi_jurnal.getDebit());
            prepare3.setObject(4, transaksi_jurnal.getKredit());
            prepare3.setObject(5, transaksi_jurnal.getKeterangan());
            
            /*if (transaksi_jurnal.getId_akun().charAt(0) == '1') {
                prepare4.setObject(1, transaksi_jurnal.getTanggal());
                prepare4.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare4.setObject(3, transaksi_jurnal.getKeterangan());
                prepare4.setObject(4, "Kas");
                prepare4.setObject(5, 1);
                prepare4.setObject(6, transaksi_jurnal.getDebit());
                prepare4.setObject(7, 0);        
                prepare4.executeUpdate();
            } else */
            if (transaksi_jurnal.getId_akun().charAt(0) == '2') {
                prepare4.setObject(1, transaksi_jurnal.getTanggal());
                prepare4.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare4.setObject(3, transaksi_jurnal.getKeterangan());
                prepare4.setObject(4, "Kas");
                prepare4.setObject(5, 1);
                prepare4.setObject(6, transaksi_jurnal.getKredit());
                prepare4.setObject(7, 0);
                
                prepare5.setObject(1, transaksi_jurnal.getTanggal());
                prepare5.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare5.setObject(3, transaksi_jurnal.getKeterangan());
                prepare5.setObject(4, getNamaAkun(transaksi_jurnal.getId_akun()));
                prepare5.setObject(5, 2);
                prepare5.setObject(6, 0);
                prepare5.setObject(7, transaksi_jurnal.getKredit());          
                prepare4.executeUpdate();
                prepare5.executeUpdate();
            } else if (transaksi_jurnal.getId_akun().charAt(0) == '3') {
                prepare4.setObject(1, transaksi_jurnal.getTanggal());
                prepare4.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare4.setObject(3, transaksi_jurnal.getKeterangan());
                prepare4.setObject(4, "Kas");
                prepare4.setObject(5, 1);
                prepare4.setObject(6, transaksi_jurnal.getKredit());
                prepare4.setObject(7, 0);
                
                prepare5.setObject(1, transaksi_jurnal.getTanggal());
                prepare5.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare5.setObject(3, transaksi_jurnal.getKeterangan());
                prepare5.setObject(4, getNamaAkun(transaksi_jurnal.getId_akun()));
                prepare5.setObject(5, 2);
                prepare5.setObject(6, 0);
                prepare5.setObject(7, transaksi_jurnal.getKredit());          
                prepare4.executeUpdate();
                prepare5.executeUpdate();
            } else if (transaksi_jurnal.getId_akun().charAt(0) == '4') {
                prepare4.setObject(1, transaksi_jurnal.getTanggal());
                prepare4.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare4.setObject(3, transaksi_jurnal.getKeterangan());
                prepare4.setObject(4, "Kas");
                prepare4.setObject(5, 1);
                prepare4.setObject(6, 0);
                prepare4.setObject(7, transaksi_jurnal.getKredit());
                
                prepare5.setObject(1, transaksi_jurnal.getTanggal());
                prepare5.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare5.setObject(3, transaksi_jurnal.getKeterangan());
                prepare5.setObject(4, getNamaAkun(transaksi_jurnal.getId_akun()));
                prepare5.setObject(5, 2);
                prepare5.setObject(6, 0);
                prepare5.setObject(7, transaksi_jurnal.getKredit());          
                prepare4.executeUpdate();
                prepare5.executeUpdate();
            } else if (transaksi_jurnal.getId_akun().charAt(0) == '5') {
                prepare4.setObject(1, transaksi_jurnal.getTanggal());
                prepare4.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare4.setObject(3, transaksi_jurnal.getKeterangan());
                prepare4.setObject(4, "Kas");
                prepare4.setObject(5, 1);
                prepare4.setObject(6, 0);
                prepare4.setObject(7, transaksi_jurnal.getDebit());
                
                prepare5.setObject(1, transaksi_jurnal.getTanggal());
                prepare5.setObject(2, transaksi_jurnal.getId_jurnal());
                prepare5.setObject(3, transaksi_jurnal.getKeterangan());
                prepare5.setObject(4, getNamaAkun(transaksi_jurnal.getId_akun()));
                prepare5.setObject(5, 2);
                prepare5.setObject(6, transaksi_jurnal.getDebit());
                prepare5.setObject(7, 0);          
                prepare4.executeUpdate();
                prepare5.executeUpdate();
            }
            
            prepare3.executeUpdate();  
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Insert Data\n" + ex);
            try {
                con.rollback();
                prepare2.close();
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
            }
            return false;
        }
        return true;
    }

    public boolean update(TransaksiJurnal transaksi_jurnal) {
        String sql2 = "INSERT INTO jurnal (tanggal, ref, keterangan, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?)";
        String sql3 = "INSERT INTO buku_besar (tanggal, ref, keterangan, nama_akun, sort, debit, kredit) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql4 = "INSERT INTO laba_rugi (tanggal, ref, kelompok, nama_akun, nominal) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement prepare2 = null;
        PreparedStatement prepare3 = null;
        PreparedStatement prepare4 = null;
        PreparedStatement prepare5 = null;
        PreparedStatement prepare6 = null;
//        try {
//            prepare2 = con.prepareStatement(sql2);
//            prepare3 = con.prepareStatement(sql2);
//            prepare4 = con.prepareStatement(sql3);
//            prepare5 = con.prepareStatement(sql3);
//            prepare6 = con.prepareStatement(sql4);
//            con.setAutoCommit(false);
//            prepare2.setObject(1, transaksi_jurnal.getTanggal());
//            prepare2.setObject(2, transaksi_jurnal.getId());
//            prepare2.setObject(3, "          Kas");
//            prepare2.setObject(4, 0);
//            prepare2.setObject(5, transaksi_jurnal.getNominal());
//
//            prepare3.setObject(1, transaksi_jurnal.getTanggal());
//            prepare3.setObject(2, transaksi_jurnal.getId());
//            prepare3.setObject(3, getNamaAkun(transaksi_jurnal.getIdBeban()));
//            prepare3.setObject(4, transaksi_jurnal.getNominal());
//            prepare3.setObject(5, 0);
//
//            prepare4.setObject(1, transaksi_jurnal.getTanggal());
//            prepare4.setObject(2, transaksi_jurnal.getId());
//            prepare4.setObject(3, getNamaAkun(transaksi_jurnal.getIdBeban()));
//            prepare4.setObject(4, "Kas");
//            prepare4.setObject(5, 1);
//            prepare4.setObject(6, 0);
//            prepare4.setObject(7, transaksi_jurnal.getNominal());
//
//            prepare5.setObject(1, transaksi_jurnal.getTanggal());
//            prepare5.setObject(2, transaksi_jurnal.getId());
//            prepare5.setObject(3, getNamaAkun(transaksi_jurnal.getIdBeban()));
//            prepare5.setObject(4, getNamaAkun(transaksi_jurnal.getIdBeban()));
//            prepare5.setObject(5, 2);
//            prepare5.setObject(6, transaksi_jurnal.getNominal());
//            prepare5.setObject(7, 0);
//
//            prepare6.setObject(1, transaksi_jurnal.getTanggal());
//            prepare6.setObject(2, transaksi_jurnal.getId());
//            prepare6.setObject(3, "Beban Operasional");
//            prepare6.setObject(4, getNamaAkun(transaksi_jurnal.getIdBeban()));
//            prepare6.setObject(5, transaksi_jurnal.getNominal());
//
//            deleteJurnal(transaksi_jurnal.getId());
//            deleteBukuBesar(transaksi_jurnal.getId());
//            deleteLabaRugi(transaksi_jurnal.getId());
//            prepare3.executeUpdate();
//            prepare2.executeUpdate();
//            prepare4.executeUpdate();
//            prepare5.executeUpdate();
//            prepare6.executeUpdate();
//            con.commit();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error When Update Data\n" + ex);
//            try {
//                con.rollback();
//                prepare2.close();
//            } catch (SQLException ex1) {
//                JOptionPane.showMessageDialog(null, "Error When Rollback Connection\n" + ex1);
//            }
//            return false;
//        }
        return true;
    }

    public boolean delete(String id_akun, String id_jurnal) {
        String sql = "DELETE FROM transaksi WHERE id_akun = ? AND id_jurnal = ?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, id_akun);
            prepare.setString(2, id_jurnal);
            prepare.executeUpdate();
//            deleteJurnal(id_akun);
//            deleteLabaRugi(id_akun);
//            deleteBukuBesar(id_akun);
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
