/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionUtility;
import entitas.PembayaranBeban;
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
public class PembayaranBebanModel {

    private Connection con;
    private List<PembayaranBeban> list;

    public PembayaranBebanModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<PembayaranBeban> getAll() {
        try {
            String sql = "SELECT pb.id, pb.tanggal, b.nama, pb.nominal, pb.keterangan FROM pembayaran_beban pb, beban b WHERE pb.id_beban=b.id";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                PembayaranBeban pembayaran_beban = new PembayaranBeban();
                pembayaran_beban.setId(resultSet.getString(1));
                pembayaran_beban.setTanggal(resultSet.getDate(2));
                pembayaran_beban.setNama_beban(resultSet.getString(3));
                pembayaran_beban.setNominal(resultSet.getInt(4));
                pembayaran_beban.setKeterangan(resultSet.getString(5));
                list.add(pembayaran_beban);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public PembayaranBeban getPembayaranBeban(String id) {
        PembayaranBeban pembayaran_beban = new PembayaranBeban();
        try {
            String sql = "SELECT pb.id, pb.tanggal, b.nama, pb.nominal, pb.keterangan "
                    + "FROM pembayaran_beban pb, beban b WHERE pb.id_beban=b.id AND pb.id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                pembayaran_beban.setId(resultSet.getString(1));
                pembayaran_beban.setTanggal(resultSet.getDate(2));
                pembayaran_beban.setNama_beban(resultSet.getString(3));
                pembayaran_beban.setNominal(resultSet.getInt(4));
                pembayaran_beban.setKeterangan(resultSet.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println("Error Disni" + ex);
        }
        return pembayaran_beban;
    }

    public String getId() {
        String id = "";
        String sql = "SELECT id FROM pembayaran_beban ORDER BY id DESC";
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

    public String[] getNamaBeban() {
        String[] kode;
        String sql = "SELECT nama FROM beban";
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

    public boolean insert(PembayaranBeban pembayaran_beban) {
        String sql = "INSERT INTO pembayaran_beban (id, tanggal, id_beban, nominal, keterangan) VALUES (?, ?, ?, ?, ?)";
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
            for (int i = 0; i < 5; i++) {
                prepare.setObject(i + 1, pembayaran_beban.getObject(i));
            }
            prepare2.setObject(1, pembayaran_beban.getTanggal());
            prepare2.setObject(2, pembayaran_beban.getId());
            prepare2.setObject(3, "          Kas");
            prepare2.setObject(4, 0);
            prepare2.setObject(5, pembayaran_beban.getNominal());

            prepare3.setObject(1, pembayaran_beban.getTanggal());
            prepare3.setObject(2, pembayaran_beban.getId());
            prepare3.setObject(3, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare3.setObject(4, pembayaran_beban.getNominal());
            prepare3.setObject(5, 0);

            prepare4.setObject(1, pembayaran_beban.getTanggal());
            prepare4.setObject(2, pembayaran_beban.getId());
            prepare4.setObject(3, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare4.setObject(4, "Kas");
            prepare4.setObject(5, 1);
            prepare4.setObject(6, 0);
            prepare4.setObject(7, pembayaran_beban.getNominal());

            prepare5.setObject(1, pembayaran_beban.getTanggal());
            prepare5.setObject(2, pembayaran_beban.getId());
            prepare5.setObject(3, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare5.setObject(4, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare5.setObject(5, 2);
            prepare5.setObject(6, pembayaran_beban.getNominal());
            prepare5.setObject(7, 0);

            prepare6.setObject(1, pembayaran_beban.getTanggal());
            prepare6.setObject(2, pembayaran_beban.getId());
            prepare6.setObject(3, "Beban Operasional");
            prepare6.setObject(4, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare6.setObject(5, pembayaran_beban.getNominal());

            prepare.executeUpdate();
            prepare3.executeUpdate();
            prepare2.executeUpdate();
            prepare4.executeUpdate();
            prepare5.executeUpdate();
            prepare6.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Insert Data\n" + ex);
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

    public boolean update(PembayaranBeban pembayaran_beban) {
        String sql = "UPDATE pembayaran_beban SET tanggal=?, id_beban=?, nominal=?, keterangan=? WHERE id=?";
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
            for (int i = 1; i < 5; i++) {
                prepare.setObject(i, pembayaran_beban.getObject(i));
                prepare.setObject(5, pembayaran_beban.getId());
            }
            prepare2.setObject(1, pembayaran_beban.getTanggal());
            prepare2.setObject(2, pembayaran_beban.getId());
            prepare2.setObject(3, "          Kas");
            prepare2.setObject(4, 0);
            prepare2.setObject(5, pembayaran_beban.getNominal());

            prepare3.setObject(1, pembayaran_beban.getTanggal());
            prepare3.setObject(2, pembayaran_beban.getId());
            prepare3.setObject(3, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare3.setObject(4, pembayaran_beban.getNominal());
            prepare3.setObject(5, 0);

            prepare4.setObject(1, pembayaran_beban.getTanggal());
            prepare4.setObject(2, pembayaran_beban.getId());
            prepare4.setObject(3, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare4.setObject(4, "Kas");
            prepare4.setObject(5, 1);
            prepare4.setObject(6, 0);
            prepare4.setObject(7, pembayaran_beban.getNominal());

            prepare5.setObject(1, pembayaran_beban.getTanggal());
            prepare5.setObject(2, pembayaran_beban.getId());
            prepare5.setObject(3, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare5.setObject(4, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare5.setObject(5, 2);
            prepare5.setObject(6, pembayaran_beban.getNominal());
            prepare5.setObject(7, 0);

            prepare6.setObject(1, pembayaran_beban.getTanggal());
            prepare6.setObject(2, pembayaran_beban.getId());
            prepare6.setObject(3, "Beban Operasional");
            prepare6.setObject(4, getNamaBeban(pembayaran_beban.getIdBeban()));
            prepare6.setObject(5, pembayaran_beban.getNominal());

            prepare.executeUpdate();
            deleteJurnal(pembayaran_beban.getId());
            deleteBukuBesar(pembayaran_beban.getId());
            deleteLabaRugi(pembayaran_beban.getId());
            prepare3.executeUpdate();
            prepare2.executeUpdate();
            prepare4.executeUpdate();
            prepare5.executeUpdate();
            prepare6.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Update Data\n" + ex);
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

    public boolean delete(String id) {
        String sql = "DELETE FROM pembayaran_beban WHERE id=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, id);
            prepare.executeUpdate();
            deleteJurnal(id);
            deleteLabaRugi(id);
            deleteBukuBesar(id);
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
