
package com.models;

import com.controllers.Produk;
import com.koneksi.koneksi;;
import com.views.FrmProduk;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author achyar
 */
public class Produk_model implements Produk {

    @Override
    public void Simpan(FrmProduk brg) throws SQLException {
    try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT PRODUK VALUES(?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, brg.txt_produk_kode.getText());
            prepare.setString(2, brg.txt_produk_nama.getText());
            prepare.setString(3, brg.txt_produk_harga.getText());
            prepare.setString(4, brg.txt_kat_id.getText());
            prepare.setString(5, brg.tglkadal);
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(brg);
            Bersih(brg);
        }
    }

    @Override
    public void Ubah(FrmProduk brg) throws SQLException {
    try {
            Connection con = koneksi.getKoneksi();
            String sql = "UPDATE produk SET produk_nama = ?, "
                    + "produk_harga = ?, "
                    + "kategori_kategori_id = ?, "
                    + "produk_tgl_ex = ? "
                    + "WHERE produk_kode = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, brg.txt_produk_nama.getText());
            prepare.setString(2, brg.txt_produk_harga.getText());
            prepare.setString(3, brg.txt_kat_id.getText());
            prepare.setString(4, brg.tglkadal);
            prepare.setString(5, brg.txt_produk_kode.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(brg);
            Bersih(brg);
        }
    }

    @Override
    public void Hapus(FrmProduk brg) throws SQLException {
    try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM produk WHERE produk_kode = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, brg.txt_produk_kode.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasi Dihapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(brg);
            Bersih(brg);
        }
    }

    @Override
    public void Tampil(FrmProduk brg) throws SQLException {
        brg.tbl.getDataVector().removeAllElements();
        brg.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM produk LEFT JOIN kategori ON produk.kategori_kategori_id = kategori.kategori_id ORDER BY produk_kode ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data;
                data = new Object[5];
                data[0] = rs.getString("produk_kode");
                data[1] = rs.getString("produk_nama");
                data[2] = rs.getString("produk_harga");
                data[3] = rs.getString("kategori_nama");
                data[4] = rs.getString("produk_tgl_ex");
                brg.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Bersih(FrmProduk brg) throws SQLException {
        brg.txt_produk_kode.setText(null);
        brg.txt_produk_nama.setText(null);
        brg.cmb_kat.setSelectedItem("--Kategori--");
        brg.txt_kat_id.setText(null);
        brg.txt_produk_harga.setText(null);
        brg.txt_produk_ex.setDate(null);
        brg.txt_produk_kode.requestFocus();
    }

    @Override
    public void KlikTabel(FrmProduk brg) throws SQLException {
        try {
            brg.btn_ubah.setVisible(true);
            brg.btn_hapus.setVisible(true);
            int pilih = brg.tbl_produk.getSelectedRow();
            String s = (String)brg.tbl_produk.getModel().getValueAt(pilih, 4);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-M-dd");
            Date d = f.parse(s);
            if (pilih == -1) {
                return; 
            }
            brg.txt_produk_kode.setText(brg.tbl.getValueAt(pilih, 0).toString());
            brg.txt_produk_nama.setText(brg.tbl.getValueAt(pilih, 1).toString());
            brg.txt_produk_harga.setText(brg.tbl.getValueAt(pilih, 2).toString());
            brg.cmb_kat.setSelectedItem(brg.tbl.getValueAt(pilih, 3).toString());
            brg.txt_produk_ex.setDate(d);
        } catch (Exception e) {
        }
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT kategori_nama FROM kategori WHERE kategori_id = '"+brg.txt_kat_id.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] data = new Object[3];
                data[1] = rs.getString(1);
                brg.cmb_kat.setSelectedItem(rs.getString("kategori_nama"));
            }
            rs.close(); st.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void Combo(FrmProduk brg) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT kategori_nama FROM kategori";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] data = new Object[3];
                data[1] = rs.getString(1);
                brg.cmb_kat.addItem((String) data[1]);                                    
            }
            rs.close(); st.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    @Override
    public void Combo2(FrmProduk brg) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT kategori_id FROM kategori WHERE kategori_nama = '"+brg.cmb_kat.getSelectedItem()+"'";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] data = new Object[3];
                data[1] = rs.getString(1);
                brg.txt_kat_id.setText(rs.getString("kategori_id"));
            }
            rs.close(); st.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void Cari(FrmProduk brg) throws SQLException {
        brg.tbl.getDataVector().removeAllElements();
        brg.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM produk LEFT JOIN kategori ON produk.kategori_kategori_id = kategori.kategori_id WHERE produk_nama like '%" + brg.txt_produk_cari.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data = new Object[8];
                data[0] = rs.getString("produk_kode");
                data[1] = rs.getString("produk_nama");
                data[2] = rs.getString("produk_harga");
                data[3] = rs.getString("kategori_nama");
                data[4] = rs.getString("produk_tgl_ex");
                brg.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
