
package com.controllers;

import com.views.FrmProduk;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface Produk {
    
    public void Simpan(FrmProduk brg) throws SQLException;
    public void Ubah (FrmProduk brg) throws SQLException;
    public void Hapus(FrmProduk brg) throws SQLException;
    public void Tampil (FrmProduk brg) throws SQLException;
    public void Bersih(FrmProduk brg) throws SQLException;
    public void KlikTabel (FrmProduk brg) throws SQLException;
    public void Combo (FrmProduk brg) throws SQLException;
    public void Combo2 (FrmProduk brg) throws SQLException;
    public void Cari (FrmProduk brg) throws SQLException;
    
}
