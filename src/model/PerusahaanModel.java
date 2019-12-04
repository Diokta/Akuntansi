/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fadli Hudaya
 */
import connection.ConnectionUtility;
import entitas.Perusahaan;
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
public class PerusahaanModel {

    private Connection con;
    private List<Perusahaan> list;

    public PerusahaanModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<Perusahaan> getAll() {
        try {
            String sql = "SELECT * FROM perusahaan";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                Perusahaan perusahaan = new Perusahaan(resultSet.getString(1), resultSet.getString(2), 
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), 
                        resultSet.getString(9), resultSet.getInt(10));
                list.add(perusahaan);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public Perusahaan getPerusahaan(String id) {
        Perusahaan perusahaan = null;
        try {
            String sql = "SELECT * FROM perusahaan WHERE id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet resultSet = prepare.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                perusahaan = new Perusahaan(resultSet.getString(1), resultSet.getString(2), 
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), 
                        resultSet.getString(9), resultSet.getInt(10));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return perusahaan;
    }

    public String getId() {
        String id = "";
        String sql = "SELECT id FROM perusahaan ORDER BY id DESC";
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

    public boolean insert(Perusahaan perusahaan) {
        String sql = "INSERT INTO perusahaan (id, nama, email, noTelp, alamat, thnBuku, blnAkhir, blnAwal, password, periode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 0; i < 10; i++) {
                prepare.setObject(i + 1, perusahaan.getObject(i));
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

    public boolean update(Perusahaan perusahaan) {
        String sql = "UPDATE perusahaan SET nama=?, email=?, noTelp=?, alamat=?, thnBuku=?, blnAkhir=?, blnAwal=?, password=?, periode=? WHERE id=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 1; i < 10; i++) {
                prepare.setObject(i, perusahaan.getObject(i));
                prepare.setObject(10, perusahaan.getId());
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
        String sql = "DELETE FROM perusahaan WHERE id=?";
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

    public Perusahaan login(String email) {
        Perusahaan user = null;
        String sql = "SELECT * FROM perusahaan WHERE email=?";
        try {
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, email);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                user = new Perusahaan(resultSet.getString(1), resultSet.getString(2), 
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), 
                        resultSet.getString(9), resultSet.getInt(10));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
