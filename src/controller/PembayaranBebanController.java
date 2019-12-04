/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.PembayaranBeban;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.PembayaranBebanModel;
import tablemodel.PembayaranBebanTableModel;
import view.PembayaranBebanView;

/**
 *
 * @author Fadli Hudaya
 */
public class PembayaranBebanController {

    private PembayaranBebanView pembayaranBebanView;
    private PembayaranBebanModel pembayaranBebanModel;
    private PembayaranBeban pembayaran_beban;

    public PembayaranBebanController(PembayaranBebanView pembayaranBebanView, PembayaranBebanModel pembayaranBebanModel) {
        this.pembayaranBebanView = pembayaranBebanView;
        this.pembayaranBebanModel = pembayaranBebanModel;
    }

    public void refreshPembayaranBebanTable() {
        pembayaranBebanView.setPembayaranBebanTableModel(new PembayaranBebanTableModel());
        pembayaranBebanView.getPembayaranBebanTableModel().setListPembayaran_beban(pembayaranBebanModel.getAll());
        pembayaranBebanView.getPembayaranBebanTable().setModel(pembayaranBebanView.getPembayaranBebanTableModel());
        pembayaranBebanView.getPembayaranBebanTable().getTableHeader().setFont(new Font("Gill Sans MT", 0, 14));
        // ResizeColumnUtility.dynamicResize(pembayaranBebanView.getPembayaranBebanTable());
    }

    public void addValueComponent(String id) {
        pembayaran_beban = pembayaranBebanModel.getPembayaranBeban(id);
        pembayaranBebanView.getIdField().setText(pembayaran_beban.getId());
        pembayaranBebanView.getTanggalField().setDate(pembayaran_beban.getTanggal());
        pembayaranBebanView.getBebanField().setSelectedItem(pembayaran_beban.getNama_beban());
        pembayaranBebanView.getNominalField().setText(String.valueOf(pembayaran_beban.getNominal()));
        pembayaranBebanView.getKeteranganField().setText(pembayaran_beban.getKeterangan());
    }

    private PembayaranBeban createPembayaranBeban() {
        pembayaran_beban = new PembayaranBeban(pembayaranBebanView.getIdField().getText(),
                pembayaranBebanView.getTanggalField().getDate(), pembayaranBebanView.getId_beban(),
                Integer.parseInt(pembayaranBebanView.getNominalField().getText()), pembayaranBebanView.getKeteranganField().getText());
        return pembayaran_beban;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (pembayaranBebanView.getTanggalField().getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(pembayaranBebanView, "Tanggal Masih Kosong !!!");
        } else if (pembayaranBebanView.getBebanField().getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(pembayaranBebanView, "Beban Masih Kosong !!!");
        } else if (pembayaranBebanView.getNominalField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pembayaranBebanView, "Nominal Masih Kosong !!!");
        } else if (pembayaranBebanView.getKeteranganField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pembayaranBebanView, "Keterangan Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (pembayaranBebanModel.insert(createPembayaranBeban())) {
                refreshPembayaranBebanTable();
                resetData();
                JOptionPane.showMessageDialog(pembayaranBebanView, "Insert Data PembayaranBeban Sukses.");
            } else {
                JOptionPane.showMessageDialog(pembayaranBebanView, "Insert Data PembayaranBeban Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (pembayaranBebanModel.update(createPembayaranBeban())) {
                refreshPembayaranBebanTable();
                resetData();
                JOptionPane.showMessageDialog(pembayaranBebanView, "Update Data PembayaranBeban Sukses.");
            } else {
                JOptionPane.showMessageDialog(pembayaranBebanView, "Update Data PembayaranBeban Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id) {
        if (pembayaranBebanView.getPembayaranBebanTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(pembayaranBebanView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (pembayaranBebanModel.delete(id)) {
                    resetData();
                    JOptionPane.showMessageDialog(pembayaranBebanView, "Delete Data PembayaranBeban Sukses.");
                } else {
                    JOptionPane.showMessageDialog(pembayaranBebanView, "Delete Data PembayaranBeban Gagal !!!");
                }
            }
        }
    }
    
    public void loadNamaBeban() {
        String[] nama = pembayaranBebanModel.getNamaBeban();
        pembayaranBebanView.getBebanField().removeAllItems();
        for (int i = 0; i < nama.length; i++) {
            pembayaranBebanView.getBebanField().addItem(nama[i]);
        }
        pembayaranBebanView.getBebanField().setSelectedIndex(-1);
    }

    public void setIdBeban() {
        String nama = pembayaranBebanView.getBebanField().getSelectedItem().toString();
        String kode = pembayaranBebanModel.getIdBeban(nama);
        pembayaranBebanView.setId_beban(kode);
    }

    public String autoNumber() {
        String number = pembayaranBebanModel.getId();
        if (number.equals("")) {
            number = "PB001";
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
            number = "PB" + number;
        }
        return number;
    }

    public void newData() {
        if (pembayaranBebanView.getBaruButton().getText().equals("Baru")) {
            pembayaranBebanView.getBaruButton().setText("Batal");
            pembayaranBebanView.getTambahButton().setEnabled(true);
            pembayaranBebanView.getUpdateButton().setEnabled(false);
            pembayaranBebanView.getHapusButton().setEnabled(false);
            //pembayaranBebanView.getIdField().setEnabled(true);
            pembayaranBebanView.getIdField().setText(autoNumber());
            pembayaranBebanView.getTanggalField().setEnabled(true);
            pembayaranBebanView.getTanggalField().setDate(null);
            pembayaranBebanView.getBebanField().setEnabled(true);
            pembayaranBebanView.getBebanField().setSelectedIndex(-1);
            pembayaranBebanView.getNominalField().setEnabled(true);
            pembayaranBebanView.getNominalField().setText("");
            pembayaranBebanView.getKeteranganField().setEnabled(true);
            pembayaranBebanView.getKeteranganField().setText("");
            loadNamaBeban();
        } else {
            resetData();
        }
    }

    public void resetData() {
        pembayaranBebanView.getBaruButton().setText("Baru");
        pembayaranBebanView.getUpdateButton().setText("Update");
        pembayaranBebanView.getTambahButton().setEnabled(false);
        pembayaranBebanView.getUpdateButton().setEnabled(true);
        pembayaranBebanView.getHapusButton().setEnabled(true);
        pembayaranBebanView.getIdField().setEnabled(false);
        pembayaranBebanView.getIdField().setText("");
        pembayaranBebanView.getTanggalField().setEnabled(false);
        pembayaranBebanView.getTanggalField().setDate(null);
        pembayaranBebanView.getBebanField().setEnabled(false);
        pembayaranBebanView.getBebanField().setSelectedIndex(-1);
        pembayaranBebanView.getNominalField().setEnabled(false);
        pembayaranBebanView.getNominalField().setText("");
        pembayaranBebanView.getKeteranganField().setEnabled(false);
        pembayaranBebanView.getKeteranganField().setText("");
        refreshPembayaranBebanTable();
    }

    public void updateData() {
        if (pembayaranBebanView.getUpdateButton().getText().equals("Update")) {
            pembayaranBebanView.getBaruButton().setText("Batal");
            pembayaranBebanView.getTambahButton().setEnabled(false);
            pembayaranBebanView.getUpdateButton().setText("Simpan");
            pembayaranBebanView.getHapusButton().setEnabled(false);
            //pembayaranBebanView.getIdField().setEnabled(true);
            pembayaranBebanView.getTanggalField().setEnabled(true);
            pembayaranBebanView.getBebanField().setEnabled(true);
            pembayaranBebanView.getNominalField().setEnabled(true);
            pembayaranBebanView.getKeteranganField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    public void setAction() {
        pembayaranBebanView.getPembayaranBebanTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (pembayaranBebanView.getPembayaranBebanTable().getSelectedRow() != -1) {
                    pembayaranBebanView.setId(pembayaranBebanView.getPembayaranBebanTable().getValueAt(
                            pembayaranBebanView.getPembayaranBebanTable().getSelectedRow(), 0).toString());
                    addValueComponent(pembayaranBebanView.getId());
                }
            }
        });
        pembayaranBebanView.getBebanField().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(pembayaranBebanView.getBebanField().getSelectedIndex() != -1){
                    pembayaranBebanView.setId_beban(pembayaranBebanModel.getIdBeban(pembayaranBebanView.getBebanField().getSelectedItem().toString()));
                }
            }
        });
    }

}
