/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.Admin;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.AdminModel;
import tablemodel.AdminTableModel;
import view.AdminView;
import view.DaftarView;
import view.LoginView;
import view.MenuUtama;

/**
 *
 * @author Fadli Hudaya
 */
public class AdminController {

    private AdminView adminView;
    private AdminModel adminModel;
    private LoginView loginView;
    private MenuUtama menuUtama;
    private Admin admin;
    private DaftarView daftarView;

    public AdminController(AdminView adminView, AdminModel adminModel) {
        this.adminView = adminView;
        this.adminModel = adminModel;
    }
    
    public AdminController(DaftarView daftarView, AdminModel adminModel) {
        this.daftarView = daftarView;
        this.adminModel = adminModel;
    }

    public AdminController(AdminModel adminModel, LoginView loginView, MenuUtama menuUtama) {
        this.adminModel = adminModel;
        this.loginView = loginView;
        this.menuUtama = menuUtama;
    }

    public void refreshAdminTable() {
        adminView.setAdminTableModel(new AdminTableModel());
        adminView.getAdminTableModel().setListAdmin(adminModel.getAll());
        adminView.getAdminTable().setModel(adminView.getAdminTableModel());
        adminView.getAdminTable().getTableHeader().setFont(new Font("Segoe UI", 0, 14));
        // ResizeColumnUtility.dynamicResize(adminView.getAdminTable());
    }

    public void addValueComponent(String id) {
        admin = adminModel.getAdmin(id);
        adminView.getIdField().setText(admin.getId());
        adminView.getNamaLengkapField().setText(admin.getNamaLengkap());
        adminView.getUsernameField().setText(admin.getUsername());
        adminView.getPasswordField().setText(admin.getPassword());
        adminView.getLevelField().setSelectedItem(admin.getLevel());
    }

    private Admin createAdmin() {
        if (adminView != null) {
            admin = new Admin(adminView.getIdField().getText(), adminView.getNamaLengkapField().getText(),
                adminView.getUsernameField().getText(), String.valueOf(adminView.getPasswordField().getPassword()),
                adminView.getLevelField().getSelectedItem().toString());
        } else {
            admin = new Admin(daftarView.getIdField().getText(), daftarView.getNamaField().getText(),
                daftarView.getNamaField().getText(), String.valueOf(daftarView.getPasswordField().getPassword()),
                "Admin Keuangan");
        }
        
        return admin;
    }

    private boolean isEmptyField() {
        boolean result = true;
        
        if (adminView != null) {
            if (adminView.getNamaLengkapField().getText().isEmpty()) {
                JOptionPane.showMessageDialog(adminView, "Nama lengkap Masih Kosong !!!");
            } else if (adminView.getUsernameField().getText().isEmpty()) {
                JOptionPane.showMessageDialog(adminView, "Nama Perusahaan Masih Kosong !!!");
            } else if (adminView.getPasswordField().getText().isEmpty()) {
                JOptionPane.showMessageDialog(adminView, "Password Masih Kosong !!!");
            } else {
                result = false;
            }
        } else if (daftarView != null) {
            if (daftarView.getNamaField().getText().isEmpty()) {
                JOptionPane.showMessageDialog(daftarView, "Nama Perusahaan Masih Kosong !!!");
            } else if (daftarView.getPasswordField().getText().isEmpty()) {
                JOptionPane.showMessageDialog(daftarView, "Password Masih Kosong !!!");
            } else {
                result = false;
            }
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (adminView != null) {
                if (adminModel.insert(createAdmin())) {
                    refreshAdminTable();
                    resetData();
                    JOptionPane.showMessageDialog(adminView, "Insert Data Admin Sukses.");
                } else {
                    JOptionPane.showMessageDialog(adminView, "Insert Data Admin Gagal !!!");
                }
            } else if (daftarView != null) {
                if (adminModel.insert(createAdmin())) {
                    JOptionPane.showMessageDialog(daftarView, "Insert Data Admin Sukses.");
                } else {
                    JOptionPane.showMessageDialog(daftarView, "Insert Data Admin Gagal !!!");
                }
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (adminModel.update(createAdmin())) {
                refreshAdminTable();
                resetData();
                JOptionPane.showMessageDialog(adminView, "Update Data Admin Sukses.");
            } else {
                JOptionPane.showMessageDialog(adminView, "Update Data Admin Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id) {
        if (adminView.getAdminTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(adminView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (adminModel.delete(id)) {
                    resetData();
                    JOptionPane.showMessageDialog(adminView, "Delete Data Admin Sukses.");
                } else {
                    JOptionPane.showMessageDialog(adminView, "Delete Data Admin Gagal !!!");
                }
            }
        }
    }

    public String autoNumber() {
        String number = adminModel.getId();
        if (number.equals("")) {
            number = "AD001";
        } else {
            number = number.substring(2);
            int angka = Integer.parseInt(number);
            angka++;
            if (angka < 10) {
                number = "00" + angka;
            } else if (angka >= 10 && angka < 100) {
                number = "0" + angka;
            } else if (angka >= 100 && angka < 1000) {
                number = "" + angka;
            } else {
                number = String.valueOf(angka);
            }
            if (number.length() > 5) {
                number = number.substring(number.length() - 5, number.length());
            }
            number = "AD" + number;
        }
        return number;
    }

    public void newData() {
        if (adminView.getBaruButton().getText().equals("Baru")) {
            adminView.getBaruButton().setText("Batal");
            adminView.getTambahButton().setEnabled(true);
            adminView.getUpdateButton().setEnabled(false);
            adminView.getHapusButton().setEnabled(false);
            //adminView.getIdField().setEnabled(true);
            adminView.getIdField().setText(autoNumber());
            adminView.getNamaLengkapField().setEnabled(true);
            adminView.getNamaLengkapField().setText("");
            adminView.getUsernameField().setEnabled(true);
            adminView.getUsernameField().setText("");
            adminView.getPasswordField().setEnabled(true);
            adminView.getPasswordField().setText("");
            adminView.getLevelField().setEnabled(true);
            adminView.getLevelField().setSelectedIndex(-1);
        } else {
            resetData();
        }
    }

    public void resetData() {
        adminView.getBaruButton().setText("Baru");
        adminView.getUpdateButton().setText("Update");
        adminView.getTambahButton().setEnabled(false);
        adminView.getUpdateButton().setEnabled(true);
        adminView.getHapusButton().setEnabled(true);
        adminView.getIdField().setEnabled(false);
        adminView.getIdField().setText("");
        adminView.getNamaLengkapField().setEnabled(false);
        adminView.getNamaLengkapField().setText("");
        adminView.getUsernameField().setEnabled(false);
        adminView.getUsernameField().setText("");
        adminView.getPasswordField().setEnabled(false);
        adminView.getPasswordField().setText("");
        adminView.getLevelField().setEnabled(false);
        adminView.getLevelField().setSelectedIndex(-1);
        refreshAdminTable();
    }

    public void updateData() {
        if (adminView.getUpdateButton().getText().equals("Update")) {
            adminView.getBaruButton().setText("Batal");
            adminView.getTambahButton().setEnabled(false);
            adminView.getUpdateButton().setText("Simpan");
            adminView.getHapusButton().setEnabled(false);
            //adminView.getIdField().setEnabled(true);
            adminView.getNamaLengkapField().setEnabled(true);
            adminView.getUsernameField().setEnabled(true);
            adminView.getPasswordField().setEnabled(true);
            adminView.getLevelField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    public void setAction() {
        adminView.getAdminTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (adminView.getAdminTable().getSelectedRow() != -1) {
                    adminView.setId(adminView.getAdminTable().getValueAt(
                            adminView.getAdminTable().getSelectedRow(), 0).toString());
                    addValueComponent(adminView.getId());
                }
            }
        });
    }

    public void loginAdmin() {
        if (!loginView.getUsernameField().getText().isEmpty() && !loginView.getPasswordField().getPassword().toString().isEmpty()) {
            admin = adminModel.login(loginView.getUsernameField().getText());
            if (admin != null) {
                if (loginView.getUsernameField().getText().equals(admin.getUsername())
                        && String.valueOf(loginView.getPasswordField().getPassword()).equals(admin.getPassword())) {
                    loginView.dispose();
                    menuUtama.setActive(admin.getLevel());
                } else {
                    JOptionPane.showMessageDialog(loginView, "Nama Perusahaan / Password Anda Tidak Cocok");
                }
            }
        } else {
            JOptionPane.showMessageDialog(loginView, "Nama Perusahaan / Password Anda Masih Kosong");
        }
    }
}
