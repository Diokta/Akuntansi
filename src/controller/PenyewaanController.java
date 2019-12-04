/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.Penyewaan;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.PenyewaanModel;
import tablemodel.PenyewaanTableModel;
import view.PenyewaanView;

/**
 *
 * @author Fadli Hudaya
 */
public class PenyewaanController {

    private PenyewaanView penyewaanView;
    private PenyewaanModel penyewaanModel;
    private Penyewaan penyewaan;

    public PenyewaanController(PenyewaanView penyewaanView, PenyewaanModel penyewaanModel) {
        this.penyewaanView = penyewaanView;
        this.penyewaanModel = penyewaanModel;
    }

    public void refreshPenyewaanTable() {
        penyewaanView.setPenyewaanTableModel(new PenyewaanTableModel());
        penyewaanView.getPenyewaanTableModel().setListPenyewaan(penyewaanModel.getAll());
        penyewaanView.getPenyewaanTable().setModel(penyewaanView.getPenyewaanTableModel());
        penyewaanView.getPenyewaanTable().getTableHeader().setFont(new Font("Gill Sans MT", 0, 14));
        // ResizeColumnUtility.dynamicResize(penyewaanView.getPenyewaanTable());
    }

    public void addValueComponent(String no_faktur) {
        penyewaan = penyewaanModel.getPenyewaan(no_faktur);
        penyewaanView.getNoFakturField().setText(penyewaan.getNoFaktur());
        penyewaanView.getTanggalField().setDate(penyewaan.getTanggal());
        penyewaanView.getPelangganField().setSelectedItem(penyewaan.getNama_pelanggan());
        penyewaanView.getProdukField().setSelectedItem(penyewaan.getNama_produk());
        penyewaanView.getLamaSewaField().setValue(penyewaan.getLamaSewa());
        penyewaanView.getTotalField().setText(String.valueOf(penyewaan.getTotal()));
    }

    private Penyewaan createPenyewaan() {
        penyewaan = new Penyewaan(penyewaanView.getNoFakturField().getText(), penyewaanView.getTanggalField().getDate(),
                penyewaanView.getId_pelanggan(), penyewaanView.getId_produk(),
                Integer.parseInt(penyewaanView.getLamaSewaField().getValue().toString()),
                Integer.parseInt(penyewaanView.getTotalField().getText()));
        return penyewaan;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (penyewaanView.getTanggalField().getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(penyewaanView, "Tanggal Masih Kosong !!!");
        } else if (penyewaanView.getPelangganField().getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(penyewaanView, "Id pelanggan Masih Kosong !!!");
        } else if (penyewaanView.getProdukField().getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(penyewaanView, "Id produk sewa Masih Kosong !!!");
        } else if (penyewaanView.getLamaSewaField().getValue().toString().equals("0")) {
            JOptionPane.showMessageDialog(penyewaanView, "Lama sewa Masih Kosong !!!");
        } else if (penyewaanView.getTotalField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(penyewaanView, "Total Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (penyewaanModel.insert(createPenyewaan())) {
                refreshPenyewaanTable();
                resetData();
                JOptionPane.showMessageDialog(penyewaanView, "Insert Data Penyewaan Sukses.");
            } else {
                JOptionPane.showMessageDialog(penyewaanView, "Insert Data Penyewaan Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (penyewaanModel.update(createPenyewaan())) {
                refreshPenyewaanTable();
                resetData();
                JOptionPane.showMessageDialog(penyewaanView, "Update Data Penyewaan Sukses.");
            } else {
                JOptionPane.showMessageDialog(penyewaanView, "Update Data Penyewaan Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String no_faktur) {
        if (penyewaanView.getPenyewaanTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(penyewaanView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (penyewaanModel.deletePenyewaan(no_faktur)) {
                    resetData();
                    JOptionPane.showMessageDialog(penyewaanView, "Delete Data Penyewaan Sukses.");
                } else {
                    JOptionPane.showMessageDialog(penyewaanView, "Delete Data Penyewaan Gagal !!!");
                }
            }
        }
    }

    public void loadNamaPelanggan() {
        String[] nama = penyewaanModel.getNamaPelanggan();
        penyewaanView.getPelangganField().removeAllItems();
        for (int i = 0; i < nama.length; i++) {
            penyewaanView.getPelangganField().addItem(nama[i]);
        }
        penyewaanView.getPelangganField().setSelectedIndex(-1);
    }

    public void setIdPelanggan() {
        String nama = penyewaanView.getPelangganField().getSelectedItem().toString();
        String kode = penyewaanModel.getIdPelanggan(nama);
        penyewaanView.setId_pelanggan(kode);
    }

    public void loadNamaProduk() {
        String[] nama = penyewaanModel.getNamaProduk();
        penyewaanView.getProdukField().removeAllItems();
        for (int i = 0; i < nama.length; i++) {
            penyewaanView.getProdukField().addItem(nama[i]);
        }
        penyewaanView.getProdukField().setSelectedIndex(-1);
    }

    public void setIdProduk() {
        String nama = penyewaanView.getProdukField().getSelectedItem().toString();
        String kode = penyewaanModel.getIdProduk(nama);
        penyewaanView.setId_produk(kode);
    }

    public String autoNumber() {
        String number = penyewaanModel.getNo_faktur();
        if (number.equals("")) {
            number = "PE001";
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
            number = "PE" + number;
        }
        return number;
    }

    public void newData() {
        if (penyewaanView.getBaruButton().getText().equals("Baru")) {
            penyewaanView.getBaruButton().setText("Batal");
            penyewaanView.getTambahButton().setEnabled(true);
            penyewaanView.getUpdateButton().setEnabled(false);
            penyewaanView.getHapusButton().setEnabled(false);
            //penyewaanView.getNoFakturField().setEnabled(true);
            penyewaanView.getNoFakturField().setText(autoNumber());
            penyewaanView.getTanggalField().setEnabled(true);
            penyewaanView.getTanggalField().setDate(null);
            penyewaanView.getPelangganField().setEnabled(true);
            penyewaanView.getPelangganField().setSelectedIndex(-1);
            penyewaanView.getProdukField().setEnabled(true);
            penyewaanView.getProdukField().setSelectedIndex(-1);
            penyewaanView.getLamaSewaField().setEnabled(true);
            penyewaanView.getLamaSewaField().setValue(1);
            //penyewaanView.getTotalField().setEnabled(true);
            penyewaanView.getTotalField().setText("");
            loadNamaPelanggan();
            loadNamaProduk();
        } else {
            resetData();
        }
    }

    public void resetData() {
        penyewaanView.getBaruButton().setText("Baru");
        penyewaanView.getUpdateButton().setText("Update");
        penyewaanView.getTambahButton().setEnabled(false);
        penyewaanView.getUpdateButton().setEnabled(true);
        penyewaanView.getHapusButton().setEnabled(true);
        penyewaanView.getNoFakturField().setEnabled(false);
        penyewaanView.getNoFakturField().setText("");
        penyewaanView.getTanggalField().setEnabled(false);
        penyewaanView.getTanggalField().setDate(null);
        penyewaanView.getPelangganField().setEnabled(false);
        penyewaanView.getPelangganField().setSelectedIndex(-1);
        penyewaanView.getProdukField().setEnabled(false);
        penyewaanView.getProdukField().setSelectedIndex(-1);
        penyewaanView.getLamaSewaField().setEnabled(false);
        penyewaanView.getLamaSewaField().setValue(1);
        //penyewaanView.getTotalField().setEnabled(false);
        penyewaanView.getTotalField().setText("");
        refreshPenyewaanTable();
    }

    public void updateData() {
        if (penyewaanView.getUpdateButton().getText().equals("Update")) {
            penyewaanView.getBaruButton().setText("Batal");
            penyewaanView.getTambahButton().setEnabled(false);
            penyewaanView.getUpdateButton().setText("Simpan");
            penyewaanView.getHapusButton().setEnabled(false);
            penyewaanView.getNoFakturField().setEnabled(true);
            penyewaanView.getTanggalField().setEnabled(true);
            penyewaanView.getPelangganField().setEnabled(true);
            penyewaanView.getProdukField().setEnabled(true);
            penyewaanView.getLamaSewaField().setEnabled(true);
            //penyewaanView.getTotalField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    private Integer getTotal() {
        if (!penyewaanView.getHargaSewaField().getText().isEmpty() && !penyewaanView.getLamaSewaField().getValue().toString().equals("0")) {
            Integer lama_sewa = Integer.valueOf(penyewaanView.getLamaSewaField().getValue().toString());
            Integer harga_sewa = Integer.valueOf(penyewaanView.getHargaSewaField().getText());
            Integer total = lama_sewa * harga_sewa;
            return total;
        } else {
            return null;
        }
    }

    public void setAction() {
        penyewaanView.getPenyewaanTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (penyewaanView.getPenyewaanTable().getSelectedRow() != -1) {
                    penyewaanView.setNo_faktur(penyewaanView.getPenyewaanTable().getValueAt(
                            penyewaanView.getPenyewaanTable().getSelectedRow(), 0).toString());
                    addValueComponent(penyewaanView.getNo_faktur());
                }
            }
        });
        penyewaanView.getPelangganField().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (penyewaanView.getPelangganField().getSelectedIndex() != -1) {
                    penyewaanView.setId_pelanggan(penyewaanModel.getIdPelanggan(penyewaanView.getPelangganField().getSelectedItem().toString()));
                }
            }
        });
        penyewaanView.getProdukField().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (penyewaanView.getProdukField().getSelectedIndex() != -1) {
                    penyewaanView.setId_produk(penyewaanModel.getIdProduk(penyewaanView.getProdukField().getSelectedItem().toString()));
                    penyewaanView.getHargaSewaField().setText(String.valueOf(penyewaanModel.getHargaSewa(penyewaanView.getId_produk())));
                    penyewaanView.getTotalField().setText(String.valueOf(getTotal()));
                } else {
                    penyewaanView.getHargaSewaField().setText("0");
                    penyewaanView.getTotalField().setText("0");
                }
            }
        });
        penyewaanView.getLamaSewaField().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                penyewaanView.getTotalField().setText(String.valueOf(getTotal()));
            }
        });
    }

}
