
package com.controllers;

import com.views.FrmCariProduk;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface CariProduk {
    
    public void Tampil (FrmCariProduk cp) throws SQLException;
    public void KlikTabel (FrmCariProduk cp) throws SQLException;
    public void Cari (FrmCariProduk cp) throws SQLException;
    
}
