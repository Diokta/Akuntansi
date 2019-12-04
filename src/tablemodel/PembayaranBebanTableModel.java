/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entitas.PembayaranBeban;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fadli
 */
public class PembayaranBebanTableModel extends AbstractTableModel {

    private List<PembayaranBeban> list;
    private String[] header = {"ID", "Tanggal", "Nama Beban", "Nominal", "Keterangan"};

    public PembayaranBebanTableModel() {
        list = new ArrayList<>();
    }

    public void setListPembayaran_beban(List<PembayaranBeban> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return list.get(rowIndex).getObjects(columnIndex);
    }

    @Override
    public String getColumnName(int index) {
        return header[index];
    }

}
