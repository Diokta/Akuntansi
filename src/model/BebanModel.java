/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionUtility;
import entitas.Beban;
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
public class BebanModel {

    private Connection con;
    private List<Beban> list;

    public BebanModel() {
        con = ConnectionUtility.getConnection();
        list = new ArrayList<>();
    }

    public List<Beban> getAll() {
        try {
            String sql = "SELECT * FROM beban";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                Beban beban = new Beban(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                list.add(beban);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error When Retrieve Data\n" + ex);
        }
        return list;
    }

    public Beban getBeban(String id) {
        Beban beban = null;
        try {
            String sql = "SELECT * FROM beban WHERE id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet resultSet = prepare.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                beban = new Beban(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return beban;
    }

    public String getId() {
        String id = "";
        String sql = "SELECT id FROM beban ORDER BY id DESC";
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

    public boolean insert(Beban beban) {
        String sql = "INSERT INTO beban (id, nama, keterangan) VALUES (?, ?, ?)";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 0; i < 3; i++) {
                prepare.setObject(i + 1, beban.getObject(i));
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

    public boolean update(Beban beban) {
        String sql = "UPDATE beban SET nama=?, keterangan=? WHERE id=?";
        PreparedStatement prepare = null;
        try {
            prepare = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 1; i < 3; i++) {
                prepare.setObject(i, beban.getObject(i));
                prepare.setObject(3, beban.getId());
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
        String sql = "DELETE FROM beban WHERE id=?";
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
