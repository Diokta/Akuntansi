/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.ProdukSewa;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.ProdukSewaModel;
import tablemodel.ProdukSewaTableModel;
import view.ProdukSewaView;

/**
 *
 * @author Fadli Hudaya
 */
public class ProdukSewaController {

    private ProdukSewaView produkSewaView;
    private ProdukSewaModel produkSewaModel;
    private ProdukSewa produk_sewa;

    public ProdukSewaController(ProdukSewaView produkSewaView, ProdukSewaModel produkSewaModel) {
        this.produkSewaView = produkSewaView;
        this.produkSewaModel = produkSewaModel;
    }

    public void refreshProdukSewaTable() {
        produkSewaView.setProdukSewaTableModel(new ProdukSewaTableModel());
        produkSewaView.getProdukSewaTableModel().setListProdukSewa(produkSewaModel.getAll());
        produkSewaView.getProdukSewaTable().setModel(produkSewaView.getProdukSewaTableModel());
        produkSewaView.getProdukSewaTable().getTableHeader().setFont(new Font("Gill Sans MT", 0, 14));
        // ResizeColumnUtility.dynamicResize(produkSewaView.getProdukSewaTable());
    }

    public void addValueComponent(String id) {
        produk_sewa = produkSewaModel.getProdukSewa(id);
        produkSewaView.getIdField().setText(produk_sewa.getId());
        produkSewaView.getNamaField().setText(produk_sewa.getNama());
        produkSewaView.getSpesifikasiField().setText(produk_sewa.getSpesifikasi());
        produkSewaView.getHargaSewaField().setText(String.valueOf(produk_sewa.getHargaSewa()));
    }

    private ProdukSewa createProdukSewa() {
        produk_sewa = new ProdukSewa(produkSewaView.getIdField().getText(), produkSewaView.getNamaField().getText(),
                produkSewaView.getSpesifikasiField().getText(), Integer.parseInt(produkSewaView.getHargaSewaField().getText()));
        return produk_sewa;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (produkSewaView.getNamaField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(produkSewaView, "Nama Masih Kosong !!!");
        } else if (produkSewaView.getSpesifikasiField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(produkSewaView, "Spesifikasi Masih Kosong !!!");
        } else if (produkSewaView.getHargaSewaField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(produkSewaView, "Harga sewa Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (produkSewaModel.insert(createProdukSewa())) {
                refreshProdukSewaTable();
                resetData();
                JOptionPane.showMessageDialog(produkSewaView, "Insert Data ProdukSewa Sukses.");
            } else {
                JOptionPane.showMessageDialog(produkSewaView, "Insert Data ProdukSewa Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (produkSewaModel.update(createProdukSewa())) {
                refreshProdukSewaTable();
                resetData();
                JOptionPane.showMessageDialog(produkSewaView, "Update Data ProdukSewa Sukses.");
            } else {
                JOptionPane.showMessageDialog(produkSewaView, "Update Data ProdukSewa Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id) {
        if (produkSewaView.getProdukSewaTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(produkSewaView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (produkSewaModel.delete(id)) {
                    resetData();
                    JOptionPane.showMessageDialog(produkSewaView, "Delete Data ProdukSewa Sukses.");
                } else {
                    JOptionPane.showMessageDialog(produkSewaView, "Delete Data ProdukSewa Gagal !!!");
                }
            }
        }
    }

    public String autoNumber() {
        String number = produkSewaModel.getId();
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

    public void newData() {
        if (produkSewaView.getBaruButton().getText().equals("Baru")) {
            produkSewaView.getBaruButton().setText("Batal");
            produkSewaView.getTambahButton().setEnabled(true);
            produkSewaView.getUpdateButton().setEnabled(false);
            produkSewaView.getHapusButton().setEnabled(false);
            produkSewaView.getIdField().setText(autoNumber());
            produkSewaView.getNamaField().setEnabled(true);
            produkSewaView.getNamaField().setText("");
            produkSewaView.getSpesifikasiField().setEnabled(true);
            produkSewaView.getSpesifikasiField().setText("");
            produkSewaView.getHargaSewaField().setEnabled(true);
            produkSewaView.getHargaSewaField().setText("");
        } else {
            resetData();
        }
    }

    public void resetData() {
        produkSewaView.getBaruButton().setText("Baru");
        produkSewaView.getUpdateButton().setText("Update");
        produkSewaView.getTambahButton().setEnabled(false);
        produkSewaView.getUpdateButton().setEnabled(true);
        produkSewaView.getHapusButton().setEnabled(true);
        produkSewaView.getIdField().setEnabled(false);
        produkSewaView.getIdField().setText("");
        produkSewaView.getNamaField().setEnabled(false);
        produkSewaView.getNamaField().setText("");
        produkSewaView.getSpesifikasiField().setEnabled(false);
        produkSewaView.getSpesifikasiField().setText("");
        produkSewaView.getHargaSewaField().setEnabled(false);
        produkSewaView.getHargaSewaField().setText("");
        refreshProdukSewaTable();
    }

    public void updateData() {
        if (produkSewaView.getUpdateButton().getText().equals("Update")) {
            produkSewaView.getBaruButton().setText("Batal");
            produkSewaView.getTambahButton().setEnabled(false);
            produkSewaView.getUpdateButton().setText("Simpan");
            produkSewaView.getHapusButton().setEnabled(false);
            produkSewaView.getIdField().setEnabled(true);
            produkSewaView.getNamaField().setEnabled(true);
            produkSewaView.getSpesifikasiField().setEnabled(true);
            produkSewaView.getHargaSewaField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    public void setAction() {
        produkSewaView.getProdukSewaTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (produkSewaView.getProdukSewaTable().getSelectedRow() != -1) {
                    produkSewaView.setId(produkSewaView.getProdukSewaTable().getValueAt(
                            produkSewaView.getProdukSewaTable().getSelectedRow(), 0).toString());
                    addValueComponent(produkSewaView.getId());
                }
            }
        });
    }

}
