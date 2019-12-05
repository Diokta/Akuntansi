/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Fadly.CustomComponents.component.DesktopPane;
import entitas.Perusahaan;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.PerusahaanModel;
import tablemodel.PerusahaanTableModel;
import view.PerusahaanView;
import view.DaftarView;
import view.LoginView;
import view.MenuUtama;

/**
 *
 * @author Fadli Hudaya
 */
public class PerusahaanController {
    public String id;
    private PerusahaanView perusahaanView;
    private DaftarView DaftarView;
    private PerusahaanModel perusahaanModel;
    private LoginView loginView;
    private MenuUtama menuUtama;
    private Perusahaan perusahaan;

    public PerusahaanController(PerusahaanView perusahaanView, PerusahaanModel perusahaanModel) {
        this.perusahaanView = perusahaanView;
        this.perusahaanModel = perusahaanModel;
    }
    
    public PerusahaanController(DaftarView daftarView, PerusahaanModel perusahaanModel) {
        this.DaftarView = daftarView;
        this.perusahaanModel = perusahaanModel;
    }
    public PerusahaanController(PerusahaanModel perusahaanModel, LoginView loginView, MenuUtama menuUtama) {
        this.perusahaanModel = perusahaanModel;
        this.loginView = loginView;
        this.menuUtama = menuUtama;
    }

//    public void refreshPerusahaanTable() {
//        perusahaanView.setPerusahaanTableModel(new PerusahaanTableModel());
//        perusahaanView.getPerusahaanTableModel().setListPerusahaan(perusahaanModel.getAll());
//        perusahaanView.getPerusahaanTable().setModel(perusahaanView.getPerusahaanTableModel());
//        perusahaanView.getPerusahaanTable().getTableHeader().setFont(new Font("Segoe UI", 0, 14));
//        // ResizeColumnUtility.dynamicResize(perusahaanView.getPerusahaanTable());
//    }

    public void addValueComponent() {
        perusahaanView.getNamaField().setText(MenuUtama.DataPerusahaan.getNama());
        perusahaanView.getEmailField().setText(MenuUtama.DataPerusahaan.getEmail());
        perusahaanView.getNoTelpField().setText(MenuUtama.DataPerusahaan.getNoTelp());
        perusahaanView.getPasswordField().setText(MenuUtama.DataPerusahaan.getPassword());
        perusahaanView.getAlamatField().setText(MenuUtama.DataPerusahaan.getAlamat());
    }

    private Perusahaan createPerusahaan() {
        perusahaan = new Perusahaan(DaftarView.getId(), DaftarView.getNamaField().getText(),
                DaftarView.getEmailField().getText(), DaftarView.getNoTelpField().getText(),
                DaftarView.getAlamatField().getText(),Integer.parseInt(DaftarView.getThnBukuField().getSelectedItem().toString()),
                DaftarView.getblnAkhirField().getSelectedIndex()+1, DaftarView.getblnAwalField().getSelectedIndex()+1, 
                Integer.parseInt(DaftarView.getPeriodeField().getSelectedItem().toString()),
                String.valueOf(DaftarView.getPasswordField().getPassword()));
        return perusahaan;
    }

    private boolean isEmptyPerusahaanField() {
        boolean result = true;
        if (perusahaanView.getNamaField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(perusahaanView, "Nama Perusahaan Masih Kosong !!!");
        } else if (perusahaanView.getEmailField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(perusahaanView, "Email Masih Kosong !!!");
        } else if (perusahaanView.getPasswordField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(perusahaanView, "Password Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }
    
    private boolean isEmptyDaftarField() {
//        if (DaftarView == null) {
//            JOptionPane.showMessageDialog(DaftarView, "Null");
//            return true;
//        }
        
        boolean result = true;
        if (DaftarView.getNamaField().getText()== "") {
            JOptionPane.showMessageDialog(DaftarView, "Nama Perusahaan Masih Kosong !!!");
        } else if (DaftarView.getEmailField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(DaftarView, "Email Masih Kosong !!!");
        } else if (DaftarView.getPasswordField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(DaftarView, "Password Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyDaftarField()) {
            if (perusahaanModel.insert(createPerusahaan())) {
//                resetData();
                DaftarView.dispose();
                JOptionPane.showMessageDialog(DaftarView, "Insert Data Perusahaan Sukses.");
            } else {
                JOptionPane.showMessageDialog(DaftarView, "Insert Data Perusahaan Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyPerusahaanField()) {
            if (perusahaanModel.update(createPerusahaan())) {
//                resetData();
                JOptionPane.showMessageDialog(perusahaanView, "Update Data Perusahaan Sukses.");
            } else {
                JOptionPane.showMessageDialog(perusahaanView, "Update Data Perusahaan Gagal !!!");
            }
        }
    }

//    public void saveOrDelete(String id) {
//        if (perusahaanView.getPerusahaanTable().getSelectedRow() != -1) {
//            if (JOptionPane.showConfirmDialog(perusahaanView, "Anda yakin ingin menghapus data ini?", "Hapus data",
//                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                if (perusahaanModel.delete(id)) {
//                    resetData();
//                    JOptionPane.showMessageDialog(perusahaanView, "Delete Data Perusahaan Sukses.");
//                } else {
//                    JOptionPane.showMessageDialog(perusahaanView, "Delete Data Perusahaan Gagal !!!");
//                }
//            }
//        }
//    }

    public String autoNumber() {
        String number = perusahaanModel.getId();
        if (number.equals("")) {
            number = "PS001";
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
            number = "PS" + number;
        }
        return number;
    }

//    public void newData() {
//        if (DaftarView.getSimpanButton().getText().equals("Baru")) {
//            DaftarView.getBaruButton().setText("Batal");
//            DaftarView.getTambahButton().setEnabled(true);
//            DaftarView.getUpdateButton().setEnabled(false);
//            DaftarView.getHapusButton().setEnabled(false);
//            //DaftarView.getIdField().setEnabled(true);
//            DaftarView.getIdField().setText(autoNumber());
//            DaftarView.getNamaLengkapField().setEnabled(true);
//            DaftarView.getNamaLengkapField().setText("");
//            DaftarView.getUsernameField().setEnabled(true);
//            DaftarView.getUsernameField().setText("");
//            DaftarView.getPasswordField().setEnabled(true);
//            DaftarView.getPasswordField().setText("");
//            DaftarView.getLevelField().setEnabled(true);
//            DaftarView.getLevelField().setSelectedIndex(-1);
//        } else {
//            resetData();
//        }
//    }
//
//    public void resetData() {
//        perusahaanView.getBaruButton().setText("Baru");
//        perusahaanView.getUpdateButton().setText("Update");
//        perusahaanView.getTambahButton().setEnabled(false);
//        perusahaanView.getUpdateButton().setEnabled(true);
//        perusahaanView.getHapusButton().setEnabled(true);
//        perusahaanView.getIdField().setEnabled(false);
//        perusahaanView.getIdField().setText("");
//        perusahaanView.getNamaLengkapField().setEnabled(false);
//        perusahaanView.getNamaLengkapField().setText("");
//        perusahaanView.getUsernameField().setEnabled(false);
//        perusahaanView.getUsernameField().setText("");
//        perusahaanView.getPasswordField().setEnabled(false);
//        perusahaanView.getPasswordField().setText("");
//        perusahaanView.getLevelField().setEnabled(false);
//        perusahaanView.getLevelField().setSelectedIndex(-1);
//        refreshPerusahaanTable();
//    }

    public void updateData() {
        if (perusahaanView.getUpdateButton().getText().equals("Update")) {
            perusahaanView.getBatalButton().setVisible(false);
            perusahaanView.getUpdateButton().setText("Simpan");
        } else {
            saveOrUpdate();
        }
    }
    
    

//    public void setAction() {
//        perusahaanView.getPerusahaanTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (perusahaanView.getPerusahaanTable().getSelectedRow() != -1) {
//                    perusahaanView.setId(perusahaanView.getPerusahaanTable().getValueAt(
//                            perusahaanView.getPerusahaanTable().getSelectedRow(), 0).toString());
//                    addValueComponent(perusahaanView.getId());
//                }
//            }
//        });
//    }

    public void loginPerusahaan() {
        if (!loginView.getUsernameField().getText().isEmpty() && !loginView.getPasswordField().getPassword().toString().isEmpty()) {
            perusahaan = perusahaanModel.login(loginView.getUsernameField().getText());
            if (perusahaan != null) {
                if (loginView.getUsernameField().getText().equals(perusahaan.getNama())
                        && String.valueOf(loginView.getPasswordField().getPassword()).equals(perusahaan.getPassword())) {
                    loginView.dispose();
                    this.id = perusahaan.getId();
                    menuUtama.setActive();
                    MenuUtama.DataPerusahaan = perusahaan;
                } else {
                    JOptionPane.showMessageDialog(loginView, "Username / Password Anda Tidak Cocok");
                }
            }
        } else {
            JOptionPane.showMessageDialog(loginView, "Username / Password Anda Masih Kosong");
        }
    }
}
