/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entitas.Penyewaan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fadli
 */
public class PenyewaanTableModel extends AbstractTableModel {

    private List<Penyewaan> list;
    private String[] header = {"ID Penyewaan", "Tanggal", "Pelanggan", "Produk Sewa", "Lama Sewa", "Total"};

    public PenyewaanTableModel() {
        list = new ArrayList<>();
    }

    public void setListPenyewaan(List<Penyewaan> list) {
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
