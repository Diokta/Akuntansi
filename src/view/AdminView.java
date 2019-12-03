/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AdminController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.AdminModel;
import tablemodel.AdminTableModel;

/**
 *
 * @author Fadli Hudaya
 */
public class AdminView extends javax.swing.JInternalFrame {

    /**
     * Creates new form AdminView
     */
    public AdminView() {
        initComponents();
        adminModel = new AdminModel();
        adminController = new AdminController(this, adminModel);
        adminController.refreshAdminTable();
        adminController.setAction();
        setLocation((1366 / 2) - (getWidth() / 2), (768 / 2) - (getHeight() / 2));
    }

    public JTable getAdminTable() {
        return adminTable;
    }

    public JComboBox getLevelField() {
        return levelField;
    }

    public JButton getBaruButton() {
        return baruButton;
    }

    public JButton getHapusButton() {
        return hapusButton;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNamaLengkapField() {
        return namaLengkapField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getTambahButton() {
        return tambahButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public AdminTableModel getAdminTableModel() {
        return adminTableModel;
    }

    public void setAdminTableModel(AdminTableModel adminTableModel) {
        this.adminTableModel = adminTableModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        namaLengkapField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        levelField = new javax.swing.JComboBox();
        baruButton = new javax.swing.JButton();
        tambahButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        adminTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Perusahaan");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel1.setText("ID :");

        idField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        idField.setEnabled(false);

        namaLengkapField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        namaLengkapField.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel2.setText("Nama :");

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel4.setText("Username :");

        usernameField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        usernameField.setEnabled(false);

        passwordField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        passwordField.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel5.setText("Password :");

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        jLabel3.setText("Level :");

        levelField.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        levelField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin Keuangan", "Customer Service" }));
        levelField.setEnabled(false);

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
                        .addComponent(idField, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaLengkapField)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(levelField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(namaLengkapField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(levelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        adminTable.setFont(new java.awt.Font("Gill Sans MT", 0, 13)); // NOI18N
        adminTable.setModel(new javax.swing.table.DefaultTableModel(
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
        adminTable.setRowHeight(22);
        jScrollPane1.setViewportView(adminTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(baruButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tambahButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {baruButton, tambahButton, updateButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baruButton)
                    .addComponent(tambahButton)
                    .addComponent(updateButton)
                    .addComponent(hapusButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void baruButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baruButtonActionPerformed
        adminController.newData();
    }//GEN-LAST:event_baruButtonActionPerformed

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        adminController.saveOrNew();
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        adminController.updateData();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        adminController.saveOrDelete(id);
    }//GEN-LAST:event_hapusButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable adminTable;
    private javax.swing.JButton baruButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox levelField;
    private javax.swing.JTextField namaLengkapField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton tambahButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
    private AdminModel adminModel;
    private AdminTableModel adminTableModel;
    private String id = "";
    private AdminController adminController;
}
