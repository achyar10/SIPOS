
package com.models;

import com.controllers.CariProduk;
import com.koneksi.koneksi;
import com.views.FrmCariProduk;
import com.views.FrmTransaksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author achyar
 */
public class CariProduk_model implements CariProduk {

    @Override
    public void Tampil(FrmCariProduk cp) throws SQLException {
        cp.tbl.getDataVector().removeAllElements();
        cp.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM produk LEFT JOIN kategori ON produk.kategori_kategori_id = kategori.kategori_id ORDER BY produk_nama ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data = new Object[5];
                data[0] = rs.getString("produk_kode");
                data[1] = rs.getString("produk_nama");
                data[2] = rs.getString("produk_harga");
                data[3] = rs.getString("kategori_nama");
                data[4] = rs.getString("produk_tgl_ex");
                cp.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void KlikTabel(FrmCariProduk cp) throws SQLException {
        try {
            int pilih = cp.tbl_cari.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            FrmTransaksi.txt_produk_kode.setText(cp.tbl.getValueAt(pilih, 0).toString());
            FrmTransaksi.txt_produk_nama.setText(cp.tbl.getValueAt(pilih, 1).toString());
            FrmTransaksi.txt_produk_harga.setText(cp.tbl.getValueAt(pilih, 2).toString());
            FrmTransaksi.txt_transaksi_qty.setText("1");
            FrmTransaksi.btn_ok.requestFocus();
        } catch (Exception e) {
        }
    }

    @Override
    public void Cari(FrmCariProduk cp) throws SQLException {
        cp.tbl.getDataVector().removeAllElements();
        cp.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM produk LEFT JOIN kategori ON produk.kategori_kategori_id = kategori.kategori_id WHERE produk_nama like '%" + cp.txt_cari.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data = new Object[8];
                data[0] = rs.getString("produk_kode");
                data[1] = rs.getString("produk_nama");
                data[2] = rs.getString("produk_harga");
                data[3] = rs.getString("kategori_nama");
                data[4] = rs.getString("produk_tgl_ex");
                cp.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
