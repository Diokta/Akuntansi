/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entitas.Pelanggan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fadli
 */
public class PelangganTableModel extends AbstractTableModel {

    private List<Pelanggan> list;
    private String[] header = {"ID", "Nama", "Jenis Kelamin", "Alamat", "No KTP"};

    public PelangganTableModel() {
        list = new ArrayList<>();
    }

    public void setListPelanggan(List<Pelanggan> list) {
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
        return list.get(rowIndex).getObject(columnIndex);
    }

    @Override
    public String getColumnName(int index) {
        return header[index];
    }

}
