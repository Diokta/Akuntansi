/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.Beban;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.BebanModel;
import tablemodel.BebanTableModel;
import view.BebanView;

/**
 *
 * @author Fadli Hudaya
 */
public class BebanController {

    private BebanView bebanView;
    private BebanModel bebanModel;
    private Beban beban;

    public BebanController(BebanView bebanView, BebanModel bebanModel) {
        this.bebanView = bebanView;
        this.bebanModel = bebanModel;
    }

    public void refreshBebanTable() {
        bebanView.setBebanTableModel(new BebanTableModel());
        bebanView.getBebanTableModel().setListBeban(bebanModel.getAll());
        bebanView.getBebanTable().setModel(bebanView.getBebanTableModel());
        bebanView.getBebanTable().getTableHeader().setFont(new Font("Gill Sans MT", 0, 14));
        // ResizeColumnUtility.dynamicResize(bebanView.getBebanTable());
    }

    public void addValueComponent(String id) {
        beban = bebanModel.getBeban(id);
        bebanView.getIdField().setText(beban.getId());
        bebanView.getNamaField().setText(beban.getNama());
        bebanView.getKeteranganField().setText(beban.getKeterangan());
    }

    private Beban createBeban() {
        beban = new Beban(bebanView.getIdField().getText(), bebanView.getNamaField().getText(), bebanView.getKeteranganField().getText());
        return beban;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (bebanView.getNamaField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(bebanView, "Nama Masih Kosong !!!");
        } else if (bebanView.getKeteranganField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(bebanView, "Keterangan Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (bebanModel.insert(createBeban())) {
                refreshBebanTable();
                resetData();
                JOptionPane.showMessageDialog(bebanView, "Insert Data Beban Sukses.");
            } else {
                JOptionPane.showMessageDialog(bebanView, "Insert Data Beban Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (bebanModel.update(createBeban())) {
                refreshBebanTable();
                resetData();
                JOptionPane.showMessageDialog(bebanView, "Update Data Beban Sukses.");
            } else {
                JOptionPane.showMessageDialog(bebanView, "Update Data Beban Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id) {
        if (bebanView.getBebanTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(bebanView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (bebanModel.delete(id)) {
                    resetData();
                    JOptionPane.showMessageDialog(bebanView, "Delete Data Beban Sukses.");
                } else {
                    JOptionPane.showMessageDialog(bebanView, "Delete Data Beban Gagal !!!");
                }
            }
        }
    }

    public String autoNumber() {
        String number = bebanModel.getId();
        if (number.equals("")) {
            number = "5001";
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
            number = "5" + number;
        }
        return number;
    }

    public void newData() {
        if (bebanView.getBaruButton().getText().equals("Baru")) {
            bebanView.getBaruButton().setText("Batal");
            bebanView.getTambahButton().setEnabled(true);
            bebanView.getUpdateButton().setEnabled(false);
            bebanView.getHapusButton().setEnabled(false);
            bebanView.getIdField().setText(autoNumber());
            bebanView.getNamaField().setEnabled(true);
            bebanView.getNamaField().setText("");
            bebanView.getKeteranganField().setEnabled(true);
            bebanView.getKeteranganField().setText("");
        } else {
            resetData();
        }
    }

    public void resetData() {
        bebanView.getBaruButton().setText("Baru");
        bebanView.getUpdateButton().setText("Update");
        bebanView.getTambahButton().setEnabled(false);
        bebanView.getUpdateButton().setEnabled(true);
        bebanView.getHapusButton().setEnabled(true);
        bebanView.getIdField().setEnabled(false);
        bebanView.getIdField().setText("");
        bebanView.getNamaField().setEnabled(false);
        bebanView.getNamaField().setText("");
        bebanView.getKeteranganField().setEnabled(false);
        bebanView.getKeteranganField().setText("");
        refreshBebanTable();
    }

    public void updateData() {
        if (bebanView.getUpdateButton().getText().equals("Update")) {
            bebanView.getBaruButton().setText("Batal");
            bebanView.getTambahButton().setEnabled(false);
            bebanView.getUpdateButton().setText("Simpan");
            bebanView.getHapusButton().setEnabled(false);
            bebanView.getIdField().setEnabled(true);
            bebanView.getNamaField().setEnabled(true);
            bebanView.getKeteranganField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    public void setAction() {
        bebanView.getBebanTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (bebanView.getBebanTable().getSelectedRow() != -1) {
                    bebanView.setId(bebanView.getBebanTable().getValueAt(
                            bebanView.getBebanTable().getSelectedRow(), 0).toString());
                    addValueComponent(bebanView.getId());
                }
            }
        });
    }

}
