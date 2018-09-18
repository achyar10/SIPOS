
package com.models;

import com.controllers.User;
import com.koneksi.koneksi;
import com.views.FrmUser;

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
public class User_model implements User {

    @Override
    public void Simpan(FrmUser usr) throws SQLException {
        try { 
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT pengguna VALUES (?,?,SHA1(?),?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txt_userid.getText());
            prepare.setString(2, usr.txt_username.getText());
            prepare.setString(3, usr.txt_password.getText());
            prepare.setString(4, usr.txt_fullname.getText());
            prepare.setString(5, usr.cmb_level.getSelectedItem().toString());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(usr);
            Bersih(usr);
            AutoNomor(usr);
        }
    }

    @Override
    public void Ubah(FrmUser usr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "UPDATE pengguna SET pengguna_username = ?, "
                    + "pengguna_password = SHA1(?), "
                    + "pengguna_nama = ?,"
                    + "pengguna_level= ?"
                    + "WHERE pengguna_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txt_username.getText());
            prepare.setString(2, usr.txt_password.getText());
            prepare.setString(3, usr.txt_fullname.getText());
            prepare.setString(4, usr.cmb_level.getSelectedItem().toString());
            prepare.setString(5, usr.txt_userid.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(usr);
            Bersih(usr);
            AutoNomor(usr);
        }
    }

    @Override
    public void Hapus(FrmUser usr) throws SQLException {
    try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM pengguna WHERE pengguna_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, usr.txt_userid.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(usr);
            Bersih(usr);
            AutoNomor(usr);
        }
    
    }

    @Override
    public void Tampil(FrmUser usr) throws SQLException {
        usr.tbl.getDataVector().removeAllElements();
        usr.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM pengguna ORDER BY pengguna_id ASC";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               Object[] data;
               data = new Object[4];
               data[0] = rs.getString("pengguna_id");
               data[1] = rs.getString("pengguna_nama");
               data[2] = rs.getString("pengguna_username");
               data[3] = rs.getString("pengguna_level");
                usr.tbl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }   
    }

    @Override
    public void Bersih(FrmUser usr) throws SQLException {
        usr.txt_userid.setText(null);
        usr.txt_username.setText(null);
        usr.txt_password.setText(null);
        usr.txt_fullname.setText(null);
        usr.cmb_level.setSelectedItem("Level");
        usr.txt_username.requestFocus();   
    }

    @Override
    public void KlikTabel(FrmUser usr) throws SQLException {
        try {
            int pilih = usr.tblUser.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            usr.txt_userid.setText(usr.tbl.getValueAt(pilih, 0).toString());
            usr.txt_fullname.setText(usr.tbl.getValueAt(pilih, 1).toString());
            usr.txt_username.setText(usr.tbl.getValueAt(pilih, 2).toString());
            usr.txt_password.setText(usr.tbl.getValueAt(pilih, 3).toString());
            usr.cmb_level.setSelectedItem(usr.tbl.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
            
        }
    }

    @Override
    public void AutoNomor(FrmUser usr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT MAX(pengguna_id) FROM pengguna";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                usr.txt_userid.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }
    
}
