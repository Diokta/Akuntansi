/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionUtility;
import entitas.Pengembalian;
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
public class PengembalianModel {

    private Connection con;
    private List<Pengembalian> list;

    public PengembalianModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<Pengembalian> getAll() {
        try {
            String sql = "SELECT * FROM pengembalian";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                Pengembalian pengembalian = new Pengembalian(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4),
                        resultSet.getInt(5));
                list.add(pengembalian);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public Pengembalian getPengembalian(String id) {
        Pengembalian pengembalian = null;
        try {
            String sql = "SELECT * FROM pengembalian WHERE id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet resultSet = prepare.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                pengembalian = new Pengembalian(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return pengembalian;
    }

    public Penyewaan getPenyewaan(String id) {
        Penyewaan penyewaan = new Penyewaan();
        try {
            String sql = "SELECT * FROM penyewaan WHERE no_faktur=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet resultSet = prepare.executeQuery();
            list = new ArrayList<>();
            if (resultSet.next()) {
                penyewaan.setNoFaktur(resultSet.getString(1));
                penyewaan.setTanggal(resultSet.getDate(2));
                penyewaan.setIdPelanggan(resultSet.getString(3));
                penyewaan.setIdProduk_sewa(resultSet.getString(4));
                penyewaan.setLamaSewa(resultSet.getInt(5));
                penyewaan.setTotal(resultSet.getInt(6));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return penyewaan;
    }

    public String[] getIdPenyewaan() {
        String[] kode;
        String sql = "SELECT no_faktur FROM penyewaan";
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

    public String getId() {
        String id = "";
        String sql = "SELECT id FROM pengembalian ORDER BY id DESC";
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

    public boolean insert(Pengembalian pengembalian) {
        String sql = "INSERT INTO pengembalian (id, id_penyewaan, tanggal_dikembalikan, lama_telat, total_denda) "
                + "VALUES (?, ?, ?, ?, ?)";
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
                prepare.setObject(i + 1, pengembalian.getObject(i));
            }

            if (pengembalian.getTotalDenda() > 0) {
                prepare2.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare2.setObject(2, pengembalian.getId());
                prepare2.setObject(3, "Kas");
                prepare2.setObject(4, pengembalian.getTotalDenda());
                prepare2.setObject(5, 0);

                prepare3.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare3.setObject(2, pengembalian.getId());
                prepare3.setObject(3, "          Piutang Denda");
                prepare3.setObject(4, 0);
                prepare3.setObject(5, pengembalian.getTotalDenda());

                prepare4.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare4.setObject(2, pengembalian.getId());
                prepare4.setObject(3, "Piutang Denda");
                prepare4.setObject(4, "Kas");
                prepare4.setObject(5, 1);
                prepare4.setObject(6, pengembalian.getTotalDenda());
                prepare4.setObject(7, 0);

                prepare5.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare5.setObject(2, pengembalian.getId());
                prepare5.setObject(3, "Piutang Denda");
                prepare5.setObject(4, "Pendapatan Denda");
                prepare5.setObject(5, 2);
                prepare5.setObject(6, 0);
                prepare5.setObject(7, pengembalian.getTotalDenda());

                prepare6.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare6.setObject(2, pengembalian.getId());
                prepare6.setObject(3, "Pendapatan");
                prepare6.setObject(4, "Pendapatan Denda");
                prepare6.setObject(5, pengembalian.getTotalDenda());
            }

            prepare.executeUpdate();
            if (pengembalian.getTotalDenda() > 0) {
                prepare2.executeUpdate();
                prepare3.executeUpdate();
                prepare4.executeUpdate();
                prepare5.executeUpdate();
                prepare6.executeUpdate();
            }
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

    public boolean update(Pengembalian pengembalian) {
        String sql = "UPDATE pengembalian SET id_penyewaan=?, tanggal_dikembalikan=?, lama_telat=?, total_denda=? WHERE id=?";
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
                prepare.setObject(i, pengembalian.getObject(i));
                prepare.setObject(5, pengembalian.getId());
            }
            if (pengembalian.getTotalDenda() > 0) {
                prepare2.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare2.setObject(2, pengembalian.getId());
                prepare2.setObject(3, "Kas");
                prepare2.setObject(4, pengembalian.getTotalDenda());
                prepare2.setObject(5, 0);

                prepare3.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare3.setObject(2, pengembalian.getId());
                prepare3.setObject(3, "          Piutang Denda");
                prepare3.setObject(4, 0);
                prepare3.setObject(5, pengembalian.getTotalDenda());

                prepare4.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare4.setObject(2, pengembalian.getId());
                prepare4.setObject(3, "Piutang Denda");
                prepare4.setObject(4, "Kas");
                prepare4.setObject(5, 1);
                prepare4.setObject(6, pengembalian.getTotalDenda());
                prepare4.setObject(7, 0);

                prepare5.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare5.setObject(2, pengembalian.getId());
                prepare5.setObject(3, "Piutang Denda");
                prepare5.setObject(4, "Pendapatan Denda");
                prepare5.setObject(5, 2);
                prepare5.setObject(6, 0);
                prepare5.setObject(7, pengembalian.getTotalDenda());

                prepare6.setObject(1, pengembalian.getTanggalDikembalikan());
                prepare6.setObject(2, pengembalian.getId());
                prepare6.setObject(3, "Pendapatan");
                prepare6.setObject(4, "Pendapatan Denda");
                prepare6.setObject(5, pengembalian.getTotalDenda());
            }

            prepare.executeUpdate();
            deleteJurnal(pengembalian.getId());
            deleteBukuBesar(pengembalian.getId());
            deleteLabaRugi(pengembalian.getId());
            if (pengembalian.getTotalDenda() > 0) {
                prepare2.executeUpdate();
                prepare3.executeUpdate();
                prepare4.executeUpdate();
                prepare5.executeUpdate();
                prepare6.executeUpdate();
            }
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
        String sql = "DELETE FROM pengembalian WHERE id=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, id);
            prepare.executeUpdate();
            deleteJurnal(id);
            deleteBukuBesar(id);
            deleteLabaRugi(id);
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
