/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.ConnectionUtility;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import view.MenuUtama;

/**
 *
 * @author Fadli Hudaya
 */
public class ReportController {
    
    public void getReportProduk() {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Laporan Produk.jasper");
        map = new HashMap<>();
        //JasperFillManager.fillReportToFile(compiledReportName, params); 
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getCashRasio() {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Cash Rasio.jasper");
        map = new HashMap<>();
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void getEPS(String bulan, String tahun, int jumlahLembar, BigDecimal hargaPerLembar) {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Earning Per Share.jasper");
        map = new HashMap<>();
        map.put("BULAN", bulan);
        map.put("TAHUN", tahun);
        map.put("JUMLAHLEMBAR", jumlahLembar);
        map.put("HARGAPERLEMBAR", hargaPerLembar);
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     public void getROI(String bulan, String tahun, int totalInvestment) {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Return On Investment.jasper");
        map = new HashMap<>();
        map.put("BULAN", bulan);
        map.put("TAHUN", tahun);
        map.put("TOTALINVESTMENT", totalInvestment);
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
    public void getReportPelanggan() {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Laporan Pelanggan.jasper");
        map = new HashMap<>();
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getReportJurnal(String bulan, String tahun) {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Jurnal Umum.jasper");
        map = new HashMap<>();
        map.put("BULAN", bulan);
        map.put("TAHUN", tahun);
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getReportBukuBesar(String bulan, String tahun) {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Laporan Buku Besar.jasper");
        map = new HashMap<>();
        map.put("BULAN", bulan);
        map.put("TAHUN", tahun);
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());
        
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getReportLabaRugi(String bulan, String tahun) {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Laporan Laba Rugi.jasper");
        map = new HashMap<>();
        map.put("BULAN", bulan);
        map.put("TAHUN", tahun);
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());
        
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void getReportNeraca(String bulan, String tahun) {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Laporan Neraca Saldo.jasper");
        map = new HashMap<>();
        map.put("BULAN", bulan);
        map.put("TAHUN", tahun);
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());
        
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getReportNeracaTahunan(String tahun) {
        InputStream stream;
        Map<String, Object> map;
        stream = getClass().getResourceAsStream("report/Laporan Neraca Saldo Tahunan.jasper");
        map = new HashMap<>();
        map.put("TAHUN", tahun);
        map.put("NAMAPERUSAHAAN", MenuUtama.DataPerusahaan.getNama());
        map.put("ALAMATPERUSAHAAN", MenuUtama.DataPerusahaan.getAlamat());        
        map.put("TELPPERUSAHAAN", MenuUtama.DataPerusahaan.getNoTelp());
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, map, ConnectionUtility.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            //Logger.getLogger(KonsultasiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
