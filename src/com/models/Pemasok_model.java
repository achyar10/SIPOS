
package com.models;
import com.controllers.Pemasok;
import com.koneksi.koneksi;
import com.views.FrmPemasok;
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
public class Pemasok_model implements Pemasok {

    @Override
    public void Simpan(FrmPemasok spl) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT pemasok VALUES (?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txt_pemasok_id.getText());
            prepare.setString(2, spl.txt_pemasok_nama.getText());
            prepare.setString(3, spl.txt_pemasok_tlp.getText());
            prepare.setString(4, spl.txt_pemasok_alamat.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(spl);
            Bersih(spl);
            AutoNomor(spl);
        } 
    }

    @Override
    public void Ubah(FrmPemasok spl) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "UPDATE pemasok SET pemasok_nama = ?,"
                    + "pemasok_tlp = ?,"
                    + "pemasok_alamat = ?"
                    + "WHERE pemasok_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txt_pemasok_nama.getText());
            prepare.setString(2, spl.txt_pemasok_tlp.getText());
            prepare.setString(3, spl.txt_pemasok_alamat.getText());
            prepare.setString(4, spl.txt_pemasok_id.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            prepare.close();
            
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            Tampil(spl);
            Bersih(spl);
            AutoNomor(spl);
        }
    
    }

    @Override
    public void Hapus(FrmPemasok spl) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM pemasok WHERE pemasok_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txt_pemasok_id.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
            
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            Tampil(spl);
            Bersih(spl);
            AutoNomor(spl);
        }
    }

    @Override
    public void Tampil(FrmPemasok spl) throws SQLException {
    spl.tbl.getDataVector().removeAllElements();
    spl.tbl.fireTableDataChanged();
        try{
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM pemasok ORDER BY pemasok_id ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data;
                data = new Object[4];
                data[0] = rs.getString("pemasok_id");
                data[1] = rs.getString("pemasok_nama");
                data[2] = rs.getString("pemasok_tlp");
                data[3] = rs.getString("pemasok_alamat");
                spl.tbl.addRow(data);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Bersih(FrmPemasok spl) throws SQLException {
        spl.txt_pemasok_id.setText(null);
        spl.txt_pemasok_nama.setText(null);
        spl.txt_pemasok_tlp.setText(null);
        spl.txt_pemasok_alamat.setText(null);
        spl.txt_pemasok_nama.requestFocus();
        
    }

    @Override
    public void KlikTabel(FrmPemasok spl) throws SQLException {
        try {
            int pilih = spl.tbl_pemasok.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            spl.txt_pemasok_id.setText(spl.tbl.getValueAt(pilih, 0).toString());
            spl.txt_pemasok_nama.setText(spl.tbl.getValueAt(pilih, 1).toString());
            spl.txt_pemasok_tlp.setText(spl.tbl.getValueAt(pilih, 2).toString());
            spl.txt_pemasok_alamat.setText(spl.tbl.getValueAt(pilih, 3).toString());
        } catch(Exception e) {
            
        } finally {
            spl.txt_pemasok_nama.requestFocus();
        }
    }

    @Override
    public void AutoNomor(FrmPemasok spl) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "SELECT MAX(pemasok_id) FROM pemasok";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                spl.txt_pemasok_id.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e){
            System.out.println(""+ e.getMessage());
        }
    }
    
}
