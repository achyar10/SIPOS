
package com.models;

import com.controllers.Laporan;
import com.koneksi.koneksi;
import com.views.FrmLaporan;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author achyar
 */
public class Laporan_model implements Laporan {
    
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    
    Map<String, Object> parameter = new HashMap<String, Object>();

    @Override
    public void Tampilkan(FrmLaporan lp) throws SQLException {
        try {
            parameter.put("txt_tgl_awal", lp.tglFrom);
            parameter.put("txt_tgl_akhir", lp.tglUntil);
            File file = new File("src/com/report/LaporanPenjualan.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, koneksi.getKoneksi());
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
