/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ReportController;
import entitas.Perusahaan;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenLookAndFeel;

/**
 *
 * @author Fadli Hudaya
 */
public class MenuUtama extends javax.swing.JFrame {

    public static Perusahaan DataPerusahaan;

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
        setExtendedState(6);
        reportController = new ReportController();
        WelcomeView wv = new WelcomeView();
        desktopPane.removeAll();
        desktopPane.updateUI();
        desktopPane.add(wv);
        wv.setVisible(true);
    }

    public void setActive() {
//        if (level.equals("Admin Keuangan")) {
//            masterMenu.setEnabled(true);
//            adminMenu.setEnabled(true);
//            biayaOperasionalMenu.setEnabled(true);
//            produkSewaMenu.setEnabled(true);
//            laporanMenu.setEnabled(true);
//            loginMenu2.setText("Logout");
//            loginMenu.setText("Logout Perusahaan");
//        } else {
//            masterMenu.setEnabled(true);
//            adminMenu.setEnabled(false);
//            biayaOperasionalMenu.setEnabled(false);
//            produkSewaMenu.setEnabled(false);
//            loginMenu2.setText("Logout");
//        }
            masterMenu.setEnabled(true);
            adminMenu.setEnabled(true);
            biayaOperasionalMenu.setEnabled(true);
            laporanMenu.setEnabled(true);
            loginMenu.setText("Logout");
            menuBar.remove(daftarMenu);
            kinerjaMenu.setEnabled(true);
            
            MenuInternalView mv = new MenuInternalView(this);
            desktopPane.removeAll();
            desktopPane.updateUI();
            desktopPane.add(mv);
            mv.setVisible(true);
    }

    private void deactive() {
        masterMenu.setEnabled(false);
        laporanMenu.setEnabled(false);
        kinerjaMenu.setEnabled(false);
        loginMenu.setText("Login");
        menuBar.add(daftarMenu,0);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new Fadly.CustomComponents.component.DesktopPane();
        menuBar = new javax.swing.JMenuBar();
        daftarMenu = new javax.swing.JMenu();
        loginMenu = new javax.swing.JMenu();
        masterMenu = new javax.swing.JMenu();
        adminMenu = new javax.swing.JMenuItem();
        biayaOperasionalMenu = new javax.swing.JMenuItem();
        Transaksi = new javax.swing.JMenuItem();
        laporanMenu = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        kinerjaMenu = new javax.swing.JMenu();
        CurrentRasioMenu = new javax.swing.JMenuItem();
        epsMenu = new javax.swing.JMenuItem();
        roiMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIAK DIFAN");

        desktopPane.setGambar(new java.io.File("E:\\Git_Repos\\program_akuntansi\\src\\resources\\bg_accounting.jpg"));

        daftarMenu.setText("Daftar");
        daftarMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftarMenuMouseClicked(evt);
            }
        });
        menuBar.add(daftarMenu);

        loginMenu.setText("Login");
        loginMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMenuMouseClicked(evt);
            }
        });
        loginMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMenuActionPerformed(evt);
            }
        });
        menuBar.add(loginMenu);

        masterMenu.setText("Data Master");
        masterMenu.setEnabled(false);

        adminMenu.setText("Perusahaan");
        adminMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminMenuActionPerformed(evt);
            }
        });
        masterMenu.add(adminMenu);

        biayaOperasionalMenu.setText("Akun");
        biayaOperasionalMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                biayaOperasionalMenuActionPerformed(evt);
            }
        });
        masterMenu.add(biayaOperasionalMenu);

        Transaksi.setText("Transaksi");
        Transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiActionPerformed(evt);
            }
        });
        masterMenu.add(Transaksi);

        menuBar.add(masterMenu);

        laporanMenu.setText("Laporan");
        laporanMenu.setEnabled(false);

        jMenuItem7.setText("Laporan Jurnal Umum");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        laporanMenu.add(jMenuItem7);

        jMenuItem8.setText("Laporan Buku Besar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        laporanMenu.add(jMenuItem8);

        jMenuItem13.setText("Laporan Neraca Saldo Bulanan");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        laporanMenu.add(jMenuItem13);

        jMenuItem10.setText("Laporan Neraca Saldo Tahunan");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        laporanMenu.add(jMenuItem10);

        jMenuItem9.setText("Laporan Laba Rugi");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        laporanMenu.add(jMenuItem9);

        menuBar.add(laporanMenu);

        kinerjaMenu.setText("Kinerja Keuangan");
        kinerjaMenu.setEnabled(false);

        CurrentRasioMenu.setText("Current Rasio");
        CurrentRasioMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentRasioMenuActionPerformed(evt);
            }
        });
        kinerjaMenu.add(CurrentRasioMenu);

        epsMenu.setText("Earning Per Share");
        epsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epsMenuActionPerformed(evt);
            }
        });
        kinerjaMenu.add(epsMenu);

        roiMenu.setText("Return on Investment");
        roiMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roiMenuActionPerformed(evt);
            }
        });
        kinerjaMenu.add(roiMenu);

        menuBar.add(kinerjaMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void biayaOperasionalMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_biayaOperasionalMenuActionPerformed
        showAkunMenu();
    }//GEN-LAST:event_biayaOperasionalMenuActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        showJurnalUmum();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        showBukuBesar();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        showLabaRugi();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        showNeracaBulanan();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        showNeracaTahunan();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void TransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiActionPerformed
        showTransaksiMenu();
    }//GEN-LAST:event_TransaksiActionPerformed

    private void loginMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMenuMouseClicked
        if (loginMenu.getText().equals("Login")) {
            LoginView lv = new LoginView(this, true, this);
            lv.setVisible(true);
        } else {
            deactive();
        }
    }//GEN-LAST:event_loginMenuMouseClicked

    private void loginMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMenuActionPerformed
        
    }//GEN-LAST:event_loginMenuActionPerformed

    private void daftarMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarMenuMouseClicked
        DaftarView dv = new DaftarView();
        desktopPane.add(dv);
        desktopPane.updateUI();
        dv.setVisible(true);
    }//GEN-LAST:event_daftarMenuMouseClicked

    private void CurrentRasioMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentRasioMenuActionPerformed
        showCR();
    }//GEN-LAST:event_CurrentRasioMenuActionPerformed

    private void adminMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminMenuActionPerformed
        showPerusahaanMenu();
    }//GEN-LAST:event_adminMenuActionPerformed

    private void epsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epsMenuActionPerformed
        showEPS();
    }//GEN-LAST:event_epsMenuActionPerformed

    private void roiMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roiMenuActionPerformed
        showROI();
    }//GEN-LAST:event_roiMenuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CurrentRasioMenu;
    private javax.swing.JMenuItem Transaksi;
    private javax.swing.JMenuItem adminMenu;
    private javax.swing.JMenuItem biayaOperasionalMenu;
    private javax.swing.JMenu daftarMenu;
    private Fadly.CustomComponents.component.DesktopPane desktopPane;
    private javax.swing.JMenuItem epsMenu;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu kinerjaMenu;
    private javax.swing.JMenu laporanMenu;
    private javax.swing.JMenu loginMenu;
    private javax.swing.JMenu masterMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem roiMenu;
    // End of variables declaration//GEN-END:variables
    public ReportController reportController;

    static {
        try {
            UIManager.setLookAndFeel(new SubstanceMistSilverLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showPerusahaanMenu() {
        PerusahaanView pv = new PerusahaanView();
        desktopPane.add(pv);
        desktopPane.updateUI();
        pv.setVisible(true);
    }

    public void showAkunMenu() {
        AkunView bv = new AkunView();
        desktopPane.updateUI();
        desktopPane.add(bv);
        bv.setVisible(true);
    }

    public void showTransaksiMenu() {
        TransaksiJurnalView tjv = new TransaksiJurnalView();
        desktopPane.add(tjv);
        desktopPane.updateUI();
        tjv.setVisible(true);
    }

    public void showJurnalUmum() {
        PeriodeView pv = new PeriodeView(this, true, true);
        pv.setOption(1);
        pv.setVisible(true);
    }

    public void showBukuBesar() {
        PeriodeView pv = new PeriodeView(this, true, true);
        pv.setOption(2);
        pv.setVisible(true);
    }

    public void showNeracaBulanan() {
        PeriodeView pv = new PeriodeView(this, true, true);
        pv.setOption(4);
        pv.setVisible(true);
    }

    public void showNeracaTahunan() {
        PeriodeView pv = new PeriodeView(this, true, false);
        pv.setVisible(true);
    }

    public void showLabaRugi() {
        PeriodeView pv = new PeriodeView(this, true, true);
        pv.setOption(3);
        pv.setVisible(true);
    }

    public void showEPS() {
        epsView vv = new epsView(this, true);
        vv.setVisible(true);
    }

    public void showCR() {
        reportController.getCashRasio();
    }

    public void showROI() {
        roiView rv = new roiView(this, true);
        rv.setVisible(true);
    }
}
