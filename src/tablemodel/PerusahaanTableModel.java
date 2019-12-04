/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entitas.Perusahaan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fadli
 */
public class PerusahaanTableModel extends AbstractTableModel {

    private List<Perusahaan> list;
    private String[] header = {"Nama", "Email", "No. Telp", "Alamat"};

    public PerusahaanTableModel() {
        list = new ArrayList<>();
    }

    public void setListPerusahaan(List<Perusahaan> list) {
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
