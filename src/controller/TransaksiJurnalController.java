/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.TransaksiJurnal;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.TransaksiJurnalModel;
import tablemodel.TransaksiJurnalTableModel;
import view.TransaksiJurnalView;

/**
 *
 * @author Fadli Hudaya
 */
public class TransaksiJurnalController {

    private TransaksiJurnalView transaksiJurnalView;
    private TransaksiJurnalModel transaksiJurnalModel;
    private TransaksiJurnal transaksi_jurnal;

    public TransaksiJurnalController(TransaksiJurnalView transaksiJurnalView, TransaksiJurnalModel transaksiJurnalModel) {
        this.transaksiJurnalView = transaksiJurnalView;
        this.transaksiJurnalModel = transaksiJurnalModel;
    }

    public void refreshTransaksiJurnalTable() {
        transaksiJurnalView.setTransaksiJurnalTableModel(new TransaksiJurnalTableModel());
        transaksiJurnalView.getTransaksiJurnalTableModel().setListTransaksi_jurnal(transaksiJurnalModel.getAll(transaksiJurnalView.getIdField().getText()));
        transaksiJurnalView.getTransaksiJurnalTable().setModel(transaksiJurnalView.getTransaksiJurnalTableModel());
        transaksiJurnalView.getTransaksiJurnalTable().getTableHeader().setFont(new Font("Gill Sans MT", 0, 14));
        
        int totalDebit = 0;
        int totalKredit = 0;
        
        for (TransaksiJurnal data : transaksiJurnalView.getTransaksiJurnalTableModel().getList()) {
            totalDebit += data.getDebit();
            totalKredit += data.getKredit();
        }
        
        if (totalDebit != totalKredit) {
            transaksiJurnalView.getLabelWarning().setText("<html>DEBET DAN KREDIT BELUM BALANCE.<br/>SILAKAN CEK KEMBALI!</html>");
            transaksiJurnalView.getLabelWarning().setVisible(true);
        } else {
            transaksiJurnalView.getLabelWarning().setVisible(false);
        }
        // ResizeColumnUtility.dynamicResize(transaksiJurnalView.getTransaksiJurnalTable());
    }

    private TransaksiJurnal createTransaksiJurnal() {
        transaksi_jurnal = new TransaksiJurnal(transaksiJurnalModel.getIdAkun(transaksiJurnalView.getAkunCombo().getSelectedItem().toString()),
                transaksiJurnalView.getIdField().getText(), transaksiJurnalView.getTanggalField().getDate(), Integer.parseInt(transaksiJurnalView.getDebetField().getText()), Integer.parseInt(transaksiJurnalView.getKreditField().getText()), transaksiJurnalView.getKeteranganField().getText());
        return transaksi_jurnal;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (transaksiJurnalView.getTanggalField().getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(transaksiJurnalView, "Tanggal Masih Kosong !!!");
        } else {
            result = false;
        }

        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (transaksiJurnalModel.insert(createTransaksiJurnal())) {
                refreshTransaksiJurnalTable();
                resetData();
                JOptionPane.showMessageDialog(transaksiJurnalView, "Insert Data Transaksi Jurnal Sukses.");
            } else {
                JOptionPane.showMessageDialog(transaksiJurnalView, "Insert Data Transaksi Jurnal Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (transaksiJurnalModel.update(createTransaksiJurnal())) {
                refreshTransaksiJurnalTable();
                resetData();
                JOptionPane.showMessageDialog(transaksiJurnalView, "Update Data TransaksiJurnal Sukses.");
            } else {
                JOptionPane.showMessageDialog(transaksiJurnalView, "Update Data TransaksiJurnal Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id_akun, String id_jurnal) {
        if (transaksiJurnalView.getTransaksiJurnalTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(transaksiJurnalView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (transaksiJurnalModel.delete(id_akun, id_jurnal)) {
                    resetData();
                    JOptionPane.showMessageDialog(transaksiJurnalView, "Delete Data TransaksiJurnal Sukses.");
                } else {
                    JOptionPane.showMessageDialog(transaksiJurnalView, "Delete Data TransaksiJurnal Gagal !!!");
                }
            }
        }
    }

    public String autoNumber() {
        String number = transaksiJurnalModel.getId();
        if (number.equals("")) {
            number = "J001";
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
            number = "J" + number;
        }
        return number;
    }

    public void newData() {
        if (transaksiJurnalView.getBaruButton().getText().equals("Baru")) {
            transaksiJurnalView.getBaruButton().setText("Batal");
            transaksiJurnalView.getTambahButton().setEnabled(true);
            transaksiJurnalView.getHapusButton().setEnabled(false);
            //transaksiJurnalView.getIdField().setEnabled(true);
            transaksiJurnalView.getIdField().setText(autoNumber());
            transaksiJurnalView.getTanggalField().setEnabled(true);
            transaksiJurnalView.getTanggalField().setDate(null);
            transaksiJurnalView.getKeteranganField().setEnabled(true);
            transaksiJurnalView.getKeteranganField().setText("");
        } else {
            resetData();
        }
    }

    public void resetData() {
        transaksiJurnalView.getBaruButton().setText("Baru");
        transaksiJurnalView.getHapusButton().setEnabled(true);
        transaksiJurnalView.getKeteranganField().setText("");
        refreshTransaksiJurnalTable();
    }

    public void setAction() {
        transaksiJurnalView.getTransaksiJurnalTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (transaksiJurnalView.getTransaksiJurnalTable().getSelectedRow() != -1) {
                    transaksiJurnalView.setId(transaksiJurnalView.getTransaksiJurnalTable().getValueAt(
                            transaksiJurnalView.getTransaksiJurnalTable().getSelectedRow(), 0).toString());
                }
            }
        });
        
        transaksiJurnalView.getAkunCombo().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(transaksiJurnalView.getAkunCombo().getSelectedIndex() != -1){
                    transaksiJurnalView.setId_akun(transaksiJurnalModel.getIdAkun(transaksiJurnalView.getAkunCombo().getSelectedItem().toString()));
                }
            }
        });
    }

    public void loadNamaAkun() {
        String[] nama = transaksiJurnalModel.getNamaAkun();
        transaksiJurnalView.getAkunCombo().removeAllItems();
        for (int i = 0; i < nama.length; i++) {
            transaksiJurnalView.getAkunCombo().addItem(nama[i]);
        }
        transaksiJurnalView.getAkunCombo().setSelectedIndex(-1);
    }

}
