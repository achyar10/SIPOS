
package com.models;

import com.controllers.Pembelian;
import com.views.FrmPembelian;
import com.koneksi.koneksi;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author achyar
 */
public class Pembelian_model implements Pembelian {
    

    @Override
    public void Simpan(FrmPembelian pb) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT pembelian VALUES(?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, pb.txt_pembelian_id.getText());
            prepare.setString(2, pb.txt_pembelian_faktur.getText());
            prepare.setString(3, pb.tglbeli);
            prepare.setString(4, pb.txt_pembelian_harga.getText());
            prepare.setString(5, pb.txt_pemasok_id.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(pb);
            Bersih(pb);
            AutoNomor(pb);
            NoFaktur(pb);
        }
    }

    @Override
    public void Ubah(FrmPembelian pb) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "UPDATE pembelian SET pembelian_faktur = ?, "
                    + "pembelian_tanggal = ?,"
                    + "pembelian_jumlah_harga = ?,"
                    + "pemasok_pemasok_id = ?"
                    + "WHERE pembelian_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, pb.txt_pembelian_faktur.getText());
            prepare.setString(2, pb.tglbeli);
            prepare.setString(3, pb.txt_pembelian_harga.getText());
            prepare.setString(4, pb.txt_pemasok_id.getText());
            prepare.setString(5, pb.txt_pembelian_id.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(pb);
            Bersih(pb);
            AutoNomor(pb);
            NoFaktur(pb);
        }
    }

    @Override
    public void Hapus(FrmPembelian pb) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM pembelian WHERE pembelian_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, pb.txt_pembelian_id.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasi Dihapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(pb);
            Bersih(pb);
        }
    }

    @Override
    public void Tampil(FrmPembelian pb) throws SQLException {
        pb.tbl.getDataVector().removeAllElements();
        pb.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM pembelian LEFT JOIN pemasok ON pembelian.pemasok_pemasok_id = pemasok.pemasok_id ORDER BY pembelian_faktur DESC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data;
                data = new Object[5];
                data[0] = rs.getString("pembelian_id");
                data[1] = rs.getString("pembelian_faktur");
                data[2] = rs.getString("pembelian_tanggal");
                data[3] = rs.getString("pembelian_jumlah_harga");
                data[4] = rs.getString("pemasok_nama");
                pb.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Bersih(FrmPembelian pb) throws SQLException {
        pb.txt_pembelian_id.setText(null);
        pb.txt_pembelian_faktur.setText(null);
        pb.cmb_pemasok.setSelectedItem("--Pemasok--");
        pb.txt_pemasok_id.setText(null);
        pb.txt_pembelian_harga.setText(null);
        pb.txt_pembelian_tgl.setDate(null);
    }

    @Override
    public void KlikTabel(FrmPembelian pb) throws SQLException {
        try {
            int pilih = pb.tbl_pembelian.getSelectedRow();
            String s = (String)pb.tbl_pembelian.getModel().getValueAt(pilih, 2);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-M-dd");
            Date d = f.parse(s);
            if (pilih == -1) {
                return; 
            }
            pb.txt_pembelian_id.setText(pb.tbl.getValueAt(pilih, 0).toString());
            pb.txt_pembelian_faktur.setText(pb.tbl.getValueAt(pilih, 1).toString());
            pb.txt_pembelian_tgl.setDate(d);
            pb.txt_pembelian_harga.setText(pb.tbl.getValueAt(pilih, 3).toString());
            pb.cmb_pemasok.setSelectedItem(pb.tbl.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT pemasok_nama FROM pemasok WHERE pemasok_id = '"+pb.txt_pemasok_id.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] data = new Object[2];
                data[1] = rs.getString(1);
                pb.cmb_pemasok.setSelectedItem(rs.getString("pemasok_nama"));
            }
            rs.close(); st.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void Combo(FrmPembelian pb) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT pemasok_nama FROM pemasok";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] data = new Object[3];
                data[1] = rs.getString(1);
                pb.cmb_pemasok.addItem((String) data[1]);                                    
            }
            rs.close(); st.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    @Override
    public void Combo2(FrmPembelian pb) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT pemasok_id FROM pemasok WHERE pemasok_nama = '"+pb.cmb_pemasok.getSelectedItem()+"'";
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Object[] data = new Object[3];
                data[1] = rs.getString(1);
                pb.txt_pemasok_id.setText(rs.getString("pemasok_id"));
            }
            rs.close(); 
        st.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void Cari(FrmPembelian pb) throws SQLException {
        pb.tbl.getDataVector().removeAllElements();
        pb.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM pembelian LEFT JOIN pemasok ON pembelian.pemasok_pemasok_id = pemasok.pemasok_id WHERE pemasok_nama like '%" + pb.txt_cari.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data = new Object[8];
                data[0] = rs.getString("pembelian_id");
                data[1] = rs.getString("pembelian_faktur");
                data[2] = rs.getString("pembelian_tanggal");
                data[3] = rs.getString("pembelian_jumlah_harga");
                data[4] = rs.getString("pemasok_nama");
                pb.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void AutoNomor(FrmPembelian pb) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "SELECT MAX(pembelian_id) FROM pembelian";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                pb.txt_pembelian_id.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e){
            System.out.println(""+ e.getMessage());
        }
    }

    @Override
    public void NoFaktur(FrmPembelian pb) throws SQLException {
        try {
            java.util.Date sekarang = new java.util.Date();
            java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("/dd/MM/yyyy");
            String tglfaktur = kal.format(sekarang) ;
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "SELECT * FROM pembelian ORDER BY pembelian_faktur DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String nofak = rs.getString("pembelian_faktur").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}

               pb.txt_pembelian_faktur.setText("F" + Nol + AN);
            } else {
               pb.txt_pembelian_faktur.setText("F0001");
            }

           }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           }
    }
    
}
