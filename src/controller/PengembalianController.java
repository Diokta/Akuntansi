/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitas.Pengembalian;
import entitas.Penyewaan;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.PengembalianModel;
import tablemodel.PengembalianTableModel;
import view.PengembalianView;

/**
 *
 * @author Fadli Hudaya
 */
public class PengembalianController {

    private PengembalianView pengembalianView;
    private PengembalianModel pengembalianModel;
    private Pengembalian pengembalian;

    public PengembalianController(PengembalianView pengembalianView, PengembalianModel pengembalianModel) {
        this.pengembalianView = pengembalianView;
        this.pengembalianModel = pengembalianModel;
    }

    public void refreshPengembalianTable() {
        pengembalianView.setPengembalianTableModel(new PengembalianTableModel());
        pengembalianView.getPengembalianTableModel().setListPengembalian(pengembalianModel.getAll());
        pengembalianView.getPengembalianTable().setModel(pengembalianView.getPengembalianTableModel());
        pengembalianView.getPengembalianTable().getTableHeader().setFont(new Font("Segoe UI", 0, 14));
        loadNoFaktur();
        // ResizeColumnUtility.dynamicResize(pengembalianView.getPengembalianTable());
    }

    public void addValueComponent(String id) {
        pengembalian = pengembalianModel.getPengembalian(id);
        pengembalianView.getIdField().setText(pengembalian.getId());
        pengembalianView.getIdPenyewaanField().setSelectedItem(pengembalian.getIdPenyewaan());
        pengembalianView.getTanggalDikembalikanField().setDate(pengembalian.getTanggalDikembalikan());
        pengembalianView.getTelatField().setValue(pengembalian.getLamaTelat());
        pengembalianView.getTotalDendaField().setText(String.valueOf(pengembalian.getTotalDenda()));
    }

    private Pengembalian createPengembalian() {
        pengembalian = new Pengembalian(pengembalianView.getIdField().getText(),
                pengembalianView.getIdPenyewaanField().getSelectedItem().toString(),
                pengembalianView.getTanggalDikembalikanField().getDate(),
                Integer.parseInt(pengembalianView.getTelatField().getValue().toString()),
                Integer.parseInt(pengembalianView.getTotalDendaField().getText()));
        return pengembalian;
    }

    private boolean isEmptyField() {
        boolean result = true;
        if (pengembalianView.getIdPenyewaanField().getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(pengembalianView, "ID penyewaan Masih Kosong !!!");
        } else if (pengembalianView.getTanggalDikembalikanField().getDate().toString().isEmpty()) {
            JOptionPane.showMessageDialog(pengembalianView, "Tanggal dikembalikan Masih Kosong !!!");
        } else if (pengembalianView.getTelatField().getValue().toString().isEmpty()) {
            JOptionPane.showMessageDialog(pengembalianView, "Lama telat Masih Kosong !!!");
        } else if (pengembalianView.getTotalDendaField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(pengembalianView, "Total denda Masih Kosong !!!");
        } else {
            result = false;
        }
        return result;
    }

    public void saveOrNew() {
        if (!isEmptyField()) {
            if (pengembalianModel.insert(createPengembalian())) {
                refreshPengembalianTable();
                resetData();
                JOptionPane.showMessageDialog(pengembalianView, "Insert Data Pengembalian Sukses.");
            } else {
                JOptionPane.showMessageDialog(pengembalianView, "Insert Data Pengembalian Gagal !!!");
            }
        }
    }

    public void saveOrUpdate() {
        if (!isEmptyField()) {
            if (pengembalianModel.update(createPengembalian())) {
                refreshPengembalianTable();
                resetData();
                JOptionPane.showMessageDialog(pengembalianView, "Update Data Pengembalian Sukses.");
            } else {
                JOptionPane.showMessageDialog(pengembalianView, "Update Data Pengembalian Gagal !!!");
            }
        }
    }

    public void saveOrDelete(String id) {
        if (pengembalianView.getPengembalianTable().getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(pengembalianView, "Anda yakin ingin menghapus data ini?", "Hapus data",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (pengembalianModel.delete(id)) {
                    refreshPengembalianTable();
                    resetData();
                    JOptionPane.showMessageDialog(pengembalianView, "Delete Data Pengembalian Sukses.");
                } else {
                    JOptionPane.showMessageDialog(pengembalianView, "Delete Data Pengembalian Gagal !!!");
                }
            }
        }
    }

    public void loadNoFaktur() {
        String[] nama = pengembalianModel.getIdPenyewaan();
        pengembalianView.getIdPenyewaanField().removeAllItems();
        for (int i = 0; i < nama.length; i++) {
            pengembalianView.getIdPenyewaanField().addItem(nama[i]);
        }
        pengembalianView.getIdPenyewaanField().setSelectedIndex(-1);
    }

    private void loadDetail(String id) {
        Penyewaan penyewaan = pengembalianModel.getPenyewaan(id);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date tglSewa = penyewaan.getTanggal();
        Calendar tglSewaCal = Calendar.getInstance();
        Calendar tglPengembalianCal = Calendar.getInstance();
        Calendar tglDikembalikanCal = Calendar.getInstance();
        tglSewaCal.setTime(tglSewa);
        tglPengembalianCal.setTime(tglSewa);
        Integer lamaSewa = penyewaan.getLamaSewa();
        tglPengembalianCal.add(Calendar.MONTH, lamaSewa);
        Date tglPengembalian = tglPengembalianCal.getTime();
        Date tglDikembalikan = new Date();
        tglDikembalikanCal.setTime(tglDikembalikan);
        Long tempTelat;
        Long telat = 0l;
        Integer biayaSewa = 0;
        if (tglDikembalikan.getTime() > tglPengembalian.getTime()) {
            System.out.println("Telat");
            tempTelat = Math.abs(tglDikembalikan.getTime() - tglPengembalian.getTime());
            telat = TimeUnit.MILLISECONDS.toDays(tempTelat);
            biayaSewa = 10000;
        } else {
            telat = 0l;
            biayaSewa = 0;
        }
        pengembalianView.getTanggalSewaField().setDate(tglSewa);
        pengembalianView.getTanggalPengembalianField().setDate(tglPengembalian);
        pengembalianView.getTelatField().setValue(telat);
        pengembalianView.getLamaSewaField().setValue(lamaSewa);
        pengembalianView.getTanggalDikembalikanField().setDate(tglDikembalikan);
        pengembalianView.getBiayaSewaField().setText(String.valueOf(biayaSewa));
        Integer total = Integer.valueOf(pengembalianView.getTelatField().getValue().toString()) * biayaSewa;
        pengembalianView.getTotalDendaField().setText(String.valueOf(total));
    }

    public String autoNumber() {
        String number = pengembalianModel.getId();
        if (number.equals("")) {
            number = "PG001";
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
            number = "PG" + number;
        }
        return number;
    }

    public void newData() {
        loadNoFaktur();
        if (pengembalianView.getBaruButton().getText().equals("Baru")) {
            pengembalianView.getBaruButton().setText("Batal");
            pengembalianView.getTambahButton().setEnabled(true);
            pengembalianView.getUpdateButton().setEnabled(false);
            pengembalianView.getHapusButton().setEnabled(false);
            pengembalianView.getIdField().setText(autoNumber());
            pengembalianView.getIdPenyewaanField().setEnabled(true);
            pengembalianView.getIdPenyewaanField().setSelectedIndex(-1);
            pengembalianView.getTanggalSewaField().setDate(null);
            pengembalianView.getLamaSewaField().setValue(0);
            pengembalianView.getTanggalPengembalianField().setDate(null);
            pengembalianView.getTanggalDikembalikanField().setDate(null);
            pengembalianView.getTelatField().setValue(0);
            pengembalianView.getBiayaSewaField().setText("");
            pengembalianView.getTotalDendaField().setText("");
        } else {
            resetData();
        }
    }

    public void resetData() {
        pengembalianView.getBaruButton().setText("Baru");
        pengembalianView.getUpdateButton().setText("Update");
        pengembalianView.getTambahButton().setEnabled(false);
        pengembalianView.getUpdateButton().setEnabled(true);
        pengembalianView.getHapusButton().setEnabled(true);
        pengembalianView.getIdField().setText(autoNumber());
        pengembalianView.getIdPenyewaanField().setEnabled(false);
        pengembalianView.getIdPenyewaanField().setSelectedIndex(-1);
        pengembalianView.getTanggalSewaField().setDate(null);
        pengembalianView.getLamaSewaField().setValue(0);
        pengembalianView.getTanggalPengembalianField().setDate(null);
        pengembalianView.getTanggalDikembalikanField().setDate(null);
        pengembalianView.getTelatField().setValue(0);
        pengembalianView.getBiayaSewaField().setText("");
        pengembalianView.getTotalDendaField().setText("");
    }

    public void updateData() {
        if (pengembalianView.getUpdateButton().getText().equals("Update")) {
            pengembalianView.getBaruButton().setText("Batal");
            pengembalianView.getTambahButton().setEnabled(false);
            pengembalianView.getUpdateButton().setText("Simpan");
            pengembalianView.getHapusButton().setEnabled(false);
            pengembalianView.getIdPenyewaanField().setEnabled(true);
        } else {
            saveOrUpdate();
        }
    }

    public void setAction() {
        pengembalianView.getPengembalianTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (pengembalianView.getPengembalianTable().getSelectedRow() != -1) {
                    pengembalianView.setId_pengembalian(pengembalianView.getPengembalianTable().getValueAt(
                            pengembalianView.getPengembalianTable().getSelectedRow(), 0).toString());
                    addValueComponent(pengembalianView.getId_pengembalian());
                }
            }
        });

        pengembalianView.getIdPenyewaanField().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (pengembalianView.getIdPenyewaanField().getSelectedIndex() != -1) {
                    loadDetail(pengembalianView.getIdPenyewaanField().getSelectedItem().toString());
                }
            }
        });
    }

}
