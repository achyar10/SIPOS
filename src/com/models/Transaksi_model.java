
package com.models;

import com.controllers.Transaksi;
import com.koneksi.koneksi;
import com.views.FrmKembalian;
import com.views.FrmTransaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;


/**
 *
 * @author achyar
 */
public class Transaksi_model implements Transaksi {
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    
    Map<String, Object> parameter = new HashMap<String, Object>();

    @Override
    public void Simpan(FrmTransaksi trx) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT transaksi VALUES (?,?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, trx.txt_transaksi_id.getText());
            prepare.setString(2, trx.txt_transaksi_kode.getText());
            prepare.setString(3, trx.txt_transaksi_tgl.getText());
            prepare.setString(4, trx.txt_produk_harga.getText());
            prepare.setString(5, trx.txt_transaksi_qty.getText());
            prepare.setString(6, trx.txt_pengguna_id.getText());
            prepare.executeUpdate();
            
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        }
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT detail_transaksi VALUES (?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, trx.txt_transaksi_id.getText());
            prepare.setString(2, trx.txt_produk_kode.getText());
            prepare.executeUpdate();
           
            prepare.close();
        } catch (Exception e) {
        }
        finally {
            Tampil(trx);
            AutoNomor(trx);
            Total(trx);
            Bersih(trx);
        }
    }

    @Override
    public void Ubah(FrmTransaksi trx) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "UPDATE transaksi SET transaksi_harga = '"+trx.txt_produk_harga.getText()+"', "
                    + "transaksi_qty = '"+trx.txt_transaksi_qty.getText()+"' "
                    + "WHERE transaksi_id = '"+trx.txt_transaksi_id2.getText()+"'";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.executeUpdate();
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(trx);
            Bersih(trx);
            trx.txt_produk_kode.requestFocus();
        }
    }

    @Override
    public void Hapus(FrmTransaksi trx) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM transaksi WHERE transaksi_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, trx.txt_transaksi_id2.getText());
            prepare.executeUpdate();
            
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(trx);
            AutoNomor(trx);
            Bersih(trx);
            Total(trx);
        }
    }

    @Override
    public void Tampil(FrmTransaksi trx) throws SQLException {
        trx.tbl.getDataVector().removeAllElements();
        trx.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT a.transaksi_id, c.produk_kode, c.produk_nama, c.produk_harga, a.transaksi_qty, a.transaksi_harga FROM transaksi "
                    + "a JOIN detail_transaksi b ON b.transaksi_id = a.transaksi_id "
                    + "JOIN produk c ON c.produk_kode = b.produk_kode "
                    + "WHERE a.transaksi_kode='"+trx.txt_transaksi_kode.getText()+"' ORDER BY a.transaksi_id ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data = new Object[6];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                trx.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void KlikTabel(FrmTransaksi trx) throws SQLException {
        try {
            int pilih = trx.tbl_transaksi.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            trx.txt_transaksi_id2.setText(trx.tbl.getValueAt(pilih, 0).toString());
            trx.txt_produk_kode.setText(trx.tbl.getValueAt(pilih, 1).toString());
            trx.txt_produk_nama.setText(trx.tbl.getValueAt(pilih, 2).toString());
            trx.txt_produk_harga.setText(trx.tbl.getValueAt(pilih, 3).toString());
            trx.txt_transaksi_qty.setText(trx.tbl.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
        } finally {
            trx.txt_transaksi_qty.requestFocus();
            trx.txt_transaksi_qty.setText(null);
        }
    }

    @Override
    public void Bersih(FrmTransaksi trx) throws SQLException {
        trx.txt_produk_kode.setText(null);
        trx.txt_produk_nama.setText(null);
        trx.txt_produk_harga.setText(null);
        trx.txt_transaksi_qty.setText(null);
        trx.txt_produk_kode.requestFocus();
    }

    @Override
    public void Tanggal(FrmTransaksi trx) throws SQLException {
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        trx.txt_transaksi_tgl.setText(kal.format(sekarang));
    }

    @Override
    public void ScanBarcode(FrmTransaksi trx) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM produk WHERE produk_kode = '"+trx.txt_produk_kode.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                trx.txt_produk_nama.setText(rs.getString("produk_nama"));
                trx.txt_produk_harga.setText(rs.getString("produk_harga"));
                trx.txt_transaksi_qty.setText("1");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            trx.txt_produk_harga.requestFocus();
        }
    }

    @Override
    public void AutoNomor(FrmTransaksi trx) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "SELECT MAX(transaksi_id) FROM transaksi";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                trx.txt_transaksi_id.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }

    @Override
    public void AutoNomorKode(FrmTransaksi trx) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "SELECT MAX(transaksi_kode) FROM transaksi";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                trx.txt_transaksi_kode.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }

    @Override
    public void Total(FrmTransaksi trx) throws SQLException {
        int JumlahBaris = trx.tbl_transaksi.getRowCount();
        int Total = 0;
        int Harga_Barang;
        TableModel tableModel;
        tableModel = trx.tbl_transaksi.getModel();
        for (int i = 0; i < JumlahBaris; i++) {
            Harga_Barang = Integer.parseInt(tableModel.getValueAt(i, 5).toString());
            Total = (Total + Harga_Barang);
            trx.txt_total_1.setText(String.valueOf(Total));
            trx.txt_total_2.setText("Total "+String.valueOf(Total));
        }
    }

    @Override
    public void Kembalian(FrmTransaksi trx) throws SQLException {
        double total = Double.valueOf(trx.txt_total_1.getText());
        double bayar = Double.valueOf(trx.txt_bayar.getText());
        double kembalian = (bayar-total);
        if (bayar < total){
            JOptionPane.showMessageDialog(null, "Maaf, Uang anda tidak cukup!!!");
        } else {
        trx.txt_kembali.setText(String.valueOf(kembalian));
        trx.txt_total_2.setText("-" +String.valueOf(kembalian));
        new FrmKembalian().show();
        FrmKembalian.lbl_kembalian.setText(String.valueOf(kembalian)); 
        }
    }

    @Override
    public void UbahJumlah(FrmTransaksi trx) throws SQLException {
        double harga = Double.valueOf(trx.txt_produk_harga.getText());
        double jumlah = Double.valueOf(trx.txt_transaksi_qty.getText());
        double jumlah_baru = (harga*jumlah);
        trx.txt_produk_harga.setText(String.valueOf(jumlah_baru));
        Ubah(trx);
        Total(trx);
    }

    @Override
    public void CetakStruk(FrmTransaksi trx) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
