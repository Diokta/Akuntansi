/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entitas.Akun;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fadli
 */
public class BebanTableModel extends AbstractTableModel {

    private List<Akun> list;
    private String[] header = {"ID", "Nama", "Keterangan"};

    public BebanTableModel() {
        list = new ArrayList<>();
    }

    public void setListBeban(List<Akun> list) {
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
