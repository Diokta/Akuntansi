/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.Pelanggan;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.PelangganModel;
import tablemodel.PelangganTableModel;
import view.PelangganView;

/**
 *
 * @author Fadli Hudaya
 */
public class PelangganController {

    private PelangganView pelangganView;
    private PelangganModel pelangganModel;
    private Pelanggan pelanggan;

    public PelangganController(PelangganView pelangganView, PelangganModel pelangganModel) {
        this.pelangganView = pelangganView;
        this.pelangganModel = pelangganModel;
    }

    public void refreshPelangganTable() {
        pelangganView.setPelangganTableModel(new PelangganTableModel());
        pelangganView.getPelangganTableModel().setListPelanggan(pelangganModel.getAll());
        pelangganView.getPelangganTable().setModel(pelangganView.getPelangganTableModel());
        pelangganView.getPelangganTable().getTableHeader().setFont(new Font("Gill Sans MT", 0, 14));
        // ResizeColumnUtility.dynamicResize(pelangganView.getPelangganTable());
    }

    public void addValueComponent(String id) {
        pelanggan = pelangganModel.getPelanggan(id);
        pelangganView.getIdField().setText(pelanggan.getId());
        pelangganView.getNamaField().setText(pelanggan.getNama());
        pelangganView.getJenisKelaminField().setSelectedItem(pelanggan.getJenisKelamin());
        pelangganView.getAlamatField().setText(pelanggan.getAlamat());
        pelangganView.getNoKtpField().setText(pelanggan.getNoKtp());
    }

    private Pelanggan createPelanggan() {
        pelanggan = new Pelanggan(pelangganView.getIdField().getText(), pelangganView.getNamaField().getText(),
                pelangganView.getJenisKelaminField().getSelectedItem().toString(), pelangganView.getAlamatField().getText(),
                pelangganView.getNoKtpField().getText());
        return pelanggan;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (pelangganView.getNamaField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pelangganView, "Nama Masih Kosong !!!");
        } else if (pelangganView.getJenisKelaminField().getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(pelangganView, "Jenis kelamin Masih Kosong !!!");
        } else if (pelangganView.getAlamatField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pelangganView, "Alamat Masih Kosong !!!");
        } else if (pelangganView.getNoKtpField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pelangganView, "No ktp Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (pelangganModel.insert(createPelanggan())) {
                refreshPelangganTable();
                resetData();
                JOptionPane.showMessageDialog(pelangganView, "Insert Data Pelanggan Sukses.");
            } else {
                JOptionPane.showMessageDialog(pelangganView, "Insert Data Pelanggan Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (pelangganModel.update(createPelanggan())) {
                refreshPelangganTable();
                resetData();
                JOptionPane.showMessageDialog(pelangganView, "Update Data Pelanggan Sukses.");
            } else {
                JOptionPane.showMessageDialog(pelangganView, "Update Data Pelanggan Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id) {
        if (pelangganView.getPelangganTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(pelangganView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (pelangganModel.delete(id)) {
                    resetData();
                    JOptionPane.showMessageDialog(pelangganView, "Delete Data Pelanggan Sukses.");
                } else {
                    JOptionPane.showMessageDialog(pelangganView, "Delete Data Pelanggan Gagal !!!");
                }
            }
        }
    }

    public String autoNumber() {
        String number = pelangganModel.getId();
        if (number.equals("")) {
            number = "PL001";
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
            number = "PL" + number;
        }
        return number;
    }

    public void newData() {
        if (pelangganView.getBaruButton().getText().equals("Baru")) {
            pelangganView.getBaruButton().setText("Batal");
            pelangganView.getTambahButton().setEnabled(true);
            pelangganView.getUpdateButton().setEnabled(false);
            pelangganView.getHapusButton().setEnabled(false);
            pelangganView.getIdField().setText(autoNumber());
            pelangganView.getNamaField().setEnabled(true);
            pelangganView.getNamaField().setText("");
            pelangganView.getJenisKelaminField().setEnabled(true);
            pelangganView.getJenisKelaminField().setSelectedIndex(-1);
            pelangganView.getAlamatField().setEnabled(true);
            pelangganView.getAlamatField().setText("");
            pelangganView.getNoKtpField().setEnabled(true);
            pelangganView.getNoKtpField().setText("");
        } else {
            resetData();
        }
    }

    public void resetData() {
        pelangganView.getBaruButton().setText("Baru");
        pelangganView.getUpdateButton().setText("Update");
        pelangganView.getTambahButton().setEnabled(false);
        pelangganView.getUpdateButton().setEnabled(true);
        pelangganView.getHapusButton().setEnabled(true);
        pelangganView.getIdField().setEnabled(false);
        pelangganView.getIdField().setText("");
        pelangganView.getNamaField().setEnabled(false);
        pelangganView.getNamaField().setText("");
        pelangganView.getJenisKelaminField().setEnabled(false);
        pelangganView.getJenisKelaminField().setSelectedIndex(-1);
        pelangganView.getAlamatField().setEnabled(false);
        pelangganView.getAlamatField().setText("");
        pelangganView.getNoKtpField().setEnabled(false);
        pelangganView.getNoKtpField().setText("");
        refreshPelangganTable();
    }

    public void updateData() {
        if (pelangganView.getUpdateButton().getText().equals("Update")) {
            pelangganView.getBaruButton().setText("Batal");
            pelangganView.getTambahButton().setEnabled(false);
            pelangganView.getUpdateButton().setText("Simpan");
            pelangganView.getHapusButton().setEnabled(false);
            pelangganView.getNamaField().setEnabled(true);
            pelangganView.getJenisKelaminField().setEnabled(true);
            pelangganView.getAlamatField().setEnabled(true);
            pelangganView.getNoKtpField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    public void setAction() {
        pelangganView.getPelangganTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (pelangganView.getPelangganTable().getSelectedRow() != -1) {
                    pelangganView.setId(pelangganView.getPelangganTable().getValueAt(
                            pelangganView.getPelangganTable().getSelectedRow(), 0).toString());
                    addValueComponent(pelangganView.getId());
                }
            }
        });
    }

}
