/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.controllers.Login;
import com.koneksi.UserID;
import com.koneksi.koneksi;
import com.views.FrmLogin;
import com.views.FrmMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author achyar
 */
public class Login_model implements Login {

    @Override
    public void Login(FrmLogin lgn) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM pengguna WHERE pengguna_username='"+lgn.txt_username.getText()+"' AND pengguna_password = sha1('"+lgn.txt_pass.getText()+"')";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                UserID.setUserLogin(lgn.txt_username.getText());
                UserID.setNameUserLogin(rs.getString("pengguna_nama"));
                if(rs.getString("pengguna_level").equals("ADMIN")){
                    new FrmMenu().show();
                    lgn.dispose();
                }else if(rs.getString("pengguna_level").equals("KASIR")){
//                    new FrmKasir().show();
                    lgn.dispose();
                } else {
                    JOptionPane.showMessageDialog(lgn, "Password Salah");
                    Bersih(lgn);
                }
            } else {
                JOptionPane.showMessageDialog(lgn, "Username Belum Terdaftar");
                Bersih(lgn);
                lgn.txt_username.requestFocus();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void Bersih(FrmLogin lgn) throws SQLException {
        lgn.txt_username.setText(null);
        lgn.txt_pass.setText(null);
        lgn.txt_username.requestFocus();
    }
    
}