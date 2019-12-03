/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.toedter.calendar.JDateChooser;
import controller.PembayaranBebanController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.PembayaranBebanModel;
import tablemodel.PembayaranBebanTableModel;

/**
 *
 * @author Fadli Hudaya
 */
public class PembayaranBebanView extends javax.swing.JInternalFrame {

    /**
     * Creates new form PembayaranBebanView
     */
    public PembayaranBebanView() {
        initComponents();
        pembayaranBebanModel = new PembayaranBebanModel();
        pembayaranBebanController = new PembayaranBebanController(this, pembayaranBebanModel);
        pembayaranBebanController.refreshPembayaranBebanTable();
        pembayaranBebanController.setAction();
        pembayaranBebanController.loadNamaBeban();
        setLocation((1366 / 2) - (getWidth() / 2), (768 / 2) - (getHeight() / 2));
    }

    public JButton getBaruButton() {
        return baruButton;
    }

    public JComboBox getBebanField() {
        return bebanField;
    }

    public JButton getHapusButton() {
        return hapusButton;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextArea getKeteranganField() {
        return keteranganField;
    }

    public JTextField getNominalField() {
        return nominalField;
    }

    public JButton getTambahButton() {
        return tambahButton;
    }

    public JDateChooser getTanggalField() {
        return tanggalField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTable getPembayaranBebanTable() {
        return pembayaranBebanTable;
    }

    public PembayaranBebanTableModel getPembayaranBebanTableModel() {
        return pembayaranBebanTableModel;
    }

    public void setPembayaranBebanTableModel(PembayaranBebanTableModel pembayaranBebanTableModel) {
        this.pembayaranBebanTableModel = pembayaranBebanTableModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_beban() {
        return id_beban;
    }

    public void setId_beban(String id_beban) {
        this.id_beban = id_beban;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nominalField = new javax.swing.JTextField();
        tanggalField = new com.toedter.calendar.JDateChooser();
        bebanField = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        keteranganField = new javax.swing.JTextArea();
        baruButton = new javax.swing.JButton();
        tambahButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pembayaranBebanTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Pengeluaran Biaya Operasional");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel1.setText("ID Pembayaran :");

        idField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        idField.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel2.setText("Tanggal :");

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel3.setText("Beban :");

        jLabel7.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel7.setText("Nominal :");

        nominalField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        nominalField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nominalField.setEnabled(false);

        tanggalField.setEnabled(false);
        tanggalField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N

        bebanField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        bebanField.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel4.setText("Keterangan :");

        jScrollPane2.setEnabled(false);
        jScrollPane2.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N

        keteranganField.setColumns(20);
        keteranganField.setRows(5);
        keteranganField.setEnabled(false);
        jScrollPane2.setViewportView(keteranganField);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nominalField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tanggalField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bebanField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel7});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tanggalField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(bebanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nominalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        baruButton.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        baruButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/batal.png"))); // NOI18N
        baruButton.setText("Baru");
        baruButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baruButtonActionPerformed(evt);
            }
        });

        tambahButton.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        tambahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/tambah.png"))); // NOI18N
        tambahButton.setText("Tambah");
        tambahButton.setEnabled(false);
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/simpan.png"))); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        hapusButton.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        hapusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/hapus.png"))); // NOI18N
        hapusButton.setText("Hapus");
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        pembayaranBebanTable.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        pembayaranBebanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        pembayaranBebanTable.setRowHeight(22);
        jScrollPane1.setViewportView(pembayaranBebanTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(baruButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tambahButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baruButton)
                    .addComponent(tambahButton)
                    .addComponent(updateButton)
                    .addComponent(hapusButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void baruButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baruButtonActionPerformed
        pembayaranBebanController.newData();
    }//GEN-LAST:event_baruButtonActionPerformed

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        pembayaranBebanController.saveOrNew();
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        pembayaranBebanController.updateData();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        pembayaranBebanController.saveOrDelete(id);
    }//GEN-LAST:event_hapusButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baruButton;
    private javax.swing.JComboBox bebanField;
    private javax.swing.JButton hapusButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea keteranganField;
    private javax.swing.JTextField nominalField;
    private javax.swing.JTable pembayaranBebanTable;
    private javax.swing.JButton tambahButton;
    private com.toedter.calendar.JDateChooser tanggalField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private PembayaranBebanTableModel pembayaranBebanTableModel;
    private PembayaranBebanModel pembayaranBebanModel;
    private PembayaranBebanController pembayaranBebanController;
    private String id = "";
    private String id_beban = "";
}
