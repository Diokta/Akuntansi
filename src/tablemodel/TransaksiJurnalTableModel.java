/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entitas.TransaksiJurnal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fadli
 */
public class TransaksiJurnalTableModel extends AbstractTableModel {

    private List<TransaksiJurnal> list;
    private String[] header = {"ID Akun", "Nama Akun", "Debit", "Kredit", "Keterangan"};

    public TransaksiJurnalTableModel() {
        list = new ArrayList<>();
    }

    public void setListTransaksi_jurnal(List<TransaksiJurnal> list) {
        this.list = list;
    }

    public List<TransaksiJurnal> getList() {
        return list;
    }
    
    public void addEmptyRow() {
        this.list.add(new TransaksiJurnal());
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
