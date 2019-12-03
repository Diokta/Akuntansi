/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionUtility;
import entitas.Pelanggan;
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
public class PelangganModel {

    private Connection con;
    private List<Pelanggan> list;

    public PelangganModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<Pelanggan> getAll() {
        try {
            String sql = "SELECT * FROM pelanggan";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                Pelanggan pelanggan = new Pelanggan(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                list.add(pelanggan);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public Pelanggan getPelanggan(String id) {
        Pelanggan pelanggan = null;
        try {
            String sql = "SELECT * FROM pelanggan WHERE id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet resultSet = prepare.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                pelanggan = new Pelanggan(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return pelanggan;
    }

    public String getId() {
        String id = "";
        String sql = "SELECT id FROM pelanggan ORDER BY id DESC";
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

    public boolean insert(Pelanggan pelanggan) {
        String sql = "INSERT INTO pelanggan (id, nama, jenis_kelamin, alamat, no_ktp) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 0; i < 5; i++) {
                prepare.setObject(i + 1, pelanggan.getObject(i));
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

    public boolean update(Pelanggan pelanggan) {
        String sql = "UPDATE pelanggan SET nama=?, jenis_kelamin=?, alamat=?, no_ktp=? WHERE id=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 1; i < 5; i++) {
                prepare.setObject(i, pelanggan.getObject(i));
                prepare.setObject(5, pelanggan.getId());
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
        String sql = "DELETE FROM pelanggan WHERE id=?";
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