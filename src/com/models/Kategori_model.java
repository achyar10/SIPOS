
package com.models;

import com.controllers.Kategori;
import com.koneksi.koneksi;
import com.views.FrmKategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author achyar
 */
public class Kategori_model implements Kategori {

    @Override
    public void Simpan(FrmKategori ktg) throws SQLException {
    try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT kategori VALUES (?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ktg.txt_kategori_id.getText());
            prepare.setString(2, ktg.txt_kategori_nama.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(ktg);
            Bersih(ktg);
            AutoNomor(ktg);
        }
    }

    @Override
    public void Ubah(FrmKategori ktg) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "UPDATE kategori SET kategori_nama = ? WHERE kategori_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ktg.txt_kategori_nama.getText());
            prepare.setString(2, ktg.txt_kategori_id.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(ktg);
            Bersih(ktg);
            AutoNomor(ktg);
        }
    }

    @Override
    public void Hapus(FrmKategori ktg) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM kategori WHERE kategori_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ktg.txt_kategori_id.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(ktg);
            Bersih(ktg);
            AutoNomor(ktg);
        }
    }

    @Override
    public void Tampil(FrmKategori ktg) throws SQLException {
        ktg.tbl.getDataVector().removeAllElements();
        ktg.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM kategori ORDER BY kategori_id ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data;
                data = new Object[4];
                data[0] = rs.getString("kategori_id");
                data[1] = rs.getString("kategori_nama");
                ktg.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Bersih(FrmKategori ktg) throws SQLException {
        ktg.txt_kategori_id.setText(null);
        ktg.txt_kategori_nama.setText(null);
        ktg.txt_kategori_nama.requestFocus();
    }

    @Override
    public void KlikTabel(FrmKategori ktg) throws SQLException {
        try {
            ktg.btn_ubah.setVisible(true);
            ktg.btn_hapus.setVisible(true);
            int pilih = ktg.tblKategori.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            ktg.txt_kategori_id.setText(ktg.tbl.getValueAt(pilih, 0).toString());
            ktg.txt_kategori_nama.setText(ktg.tbl.getValueAt(pilih, 1).toString());
        } catch (Exception e) {
        } finally {
            ktg.txt_kategori_nama.requestFocus();
        }
    }

    @Override
    public void AutoNomor(FrmKategori ktg) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "SELECT MAX(kategori_id) FROM kategori";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                ktg.txt_kategori_id.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e){
            System.out.println(""+ e.getMessage());
        }
    }
    
}
