/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionUtility;
import entitas.ProdukSewa;
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
public class ProdukSewaModel {

    private Connection con;
    private List<ProdukSewa> list;

    public ProdukSewaModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<ProdukSewa> getAll() {
        try {
            String sql = "SELECT * FROM produk_sewa";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                ProdukSewa produk_sewa = new ProdukSewa(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                list.add(produk_sewa);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public ProdukSewa getProdukSewa(String id) {
        ProdukSewa produk_sewa = null;
        try {
            String sql = "SELECT * FROM produk_sewa WHERE id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet resultSet = prepare.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                produk_sewa = new ProdukSewa(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return produk_sewa;
    }

    public String getId() {
        String id = "";
        String sql = "SELECT id FROM produk_sewa ORDER BY id DESC";
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

    public boolean insert(ProdukSewa produk_sewa) {
        String sql = "INSERT INTO produk_sewa (id, nama, spesifikasi, harga_sewa) VALUES (?, ?, ?, ?)";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 0; i < 4; i++) {
                prepare.setObject(i + 1, produk_sewa.getObject(i));
            }
            prepare.executeUpdate();
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

    public boolean update(ProdukSewa produk_sewa) {
        String sql = "UPDATE produk_sewa SET nama=?, spesifikasi=?, harga_sewa=? WHERE id=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 1; i < 4; i++) {
                prepare.setObject(i, produk_sewa.getObject(i));
                prepare.setObject(4, produk_sewa.getId());
            }
            prepare.executeUpdate();
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
        String sql = "DELETE FROM produk_sewa WHERE id=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            prepare.setString(1, id);
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
