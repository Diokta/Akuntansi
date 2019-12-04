/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.Akun;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.AkunModel;
import tablemodel.BebanTableModel;
import view.AkunView;

/**
 *
 * @author Fadli Hudaya
 */
public class AkunController {

    private AkunView akunView;
    private AkunModel akunModel;
    private Akun akun;

    public AkunController(AkunView akunView, AkunModel akunModel) {
        this.akunView = akunView;
        this.akunModel = akunModel;
    }

    public void refreshBebanTable() {
        akunView.setBebanTableModel(new BebanTableModel());
        akunView.getBebanTableModel().setListBeban(akunModel.getAll());
        akunView.getBebanTable().setModel(akunView.getBebanTableModel());
        akunView.getBebanTable().getTableHeader().setFont(new Font("Gill Sans MT", 0, 14));
        // ResizeColumnUtility.dynamicResize(bebanView.getBebanTable());
    }

    public void addValueComponent(String id) {
        akun = akunModel.getBeban(id);
        akunView.getIdField().setText(akun.getId());
        akunView.getNamaField().setText(akun.getNama());
        akunView.getKeteranganField().setText(akun.getKeterangan());
    }

    private Akun createBeban() {
        akun = new Akun(akunView.getIdField().getText(), akunView.getNamaField().getText(), akunView.getKeteranganField().getText());
        return akun;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (akunView.getNamaField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(akunView, "Nama Masih Kosong !!!");
        } else if (akunView.getKeteranganField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(akunView, "Keterangan Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (akunModel.insert(createBeban())) {
                refreshBebanTable();
                resetData();
                JOptionPane.showMessageDialog(akunView, "Insert Data Beban Sukses.");
            } else {
                JOptionPane.showMessageDialog(akunView, "Insert Data Beban Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (akunModel.update(createBeban())) {
                refreshBebanTable();
                resetData();
                JOptionPane.showMessageDialog(akunView, "Update Data Beban Sukses.");
            } else {
                JOptionPane.showMessageDialog(akunView, "Update Data Beban Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id) {
        if (akunView.getBebanTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(akunView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (akunModel.delete(id)) {
                    resetData();
                    JOptionPane.showMessageDialog(akunView, "Delete Data Beban Sukses.");
                } else {
                    JOptionPane.showMessageDialog(akunView, "Delete Data Beban Gagal !!!");
                }
            }
        }
    }

    public String autoNumber() {
        String number = akunModel.getId(akunView.getLabelAkun().getText());
        if (number.equals("")) {
            number = "001";
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
            if (number.length() > 4) {
                number = number.substring(number.length() - 4, number.length());
            }
            number = number;
        }
        return number;
    }

    public void newData() {
        if (akunView.getBaruButton().getText().equals("Baru")) {
            akunView.getBaruButton().setText("Batal");
            akunView.getTambahButton().setEnabled(true);
            akunView.getUpdateButton().setEnabled(false);
            akunView.getHapusButton().setEnabled(false);
            akunView.getIdField().setText(autoNumber());
            akunView.getNamaField().setEnabled(true);
            akunView.getNamaField().setText("");
            akunView.getKeteranganField().setEnabled(true);
            akunView.getKeteranganField().setText("");
        } else {
            resetData();
        }
    }

    public void resetData() {
        akunView.getBaruButton().setText("Baru");
        akunView.getUpdateButton().setText("Update");
        akunView.getTambahButton().setEnabled(false);
        akunView.getUpdateButton().setEnabled(true);
        akunView.getHapusButton().setEnabled(true);
        akunView.getIdField().setEnabled(false);
        akunView.getIdField().setText("");
        akunView.getNamaField().setEnabled(false);
        akunView.getNamaField().setText("");
        akunView.getKeteranganField().setEnabled(false);
        akunView.getKeteranganField().setText("");
        refreshBebanTable();
    }

    public void updateData() {
        if (akunView.getUpdateButton().getText().equals("Update")) {
            akunView.getBaruButton().setText("Batal");
            akunView.getTambahButton().setEnabled(false);
            akunView.getUpdateButton().setText("Simpan");
            akunView.getHapusButton().setEnabled(false);
            akunView.getIdField().setEnabled(true);
            akunView.getNamaField().setEnabled(true);
            akunView.getKeteranganField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    public void setAction() {
        akunView.getBebanTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (akunView.getBebanTable().getSelectedRow() != -1) {
                    akunView.setId(akunView.getBebanTable().getValueAt(
                            akunView.getBebanTable().getSelectedRow(), 0).toString());
                    addValueComponent(akunView.getId());
                }
            }
        });
    }

}
