
package com.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author achyar
 */
public class koneksi {
    private static Connection koneksi;
    
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost/sipos";
                String username = "root";
                String pass = "root";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, username, pass);
            } catch (Exception e) {
                System.out.println(e);
            }
        }return koneksi;
    }
    
}
