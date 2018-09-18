
package com.controllers;

import com.views.FrmKategori;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface Kategori {
    public void Simpan (FrmKategori ktg) throws SQLException;
    public void Ubah (FrmKategori ktg) throws SQLException;
    public void Hapus (FrmKategori ktg) throws SQLException;
    public void Tampil (FrmKategori ktg) throws SQLException;
    public void Bersih (FrmKategori ktg) throws SQLException;
    public void KlikTabel (FrmKategori ktg) throws SQLException;
    public void AutoNomor (FrmKategori ktg) throws SQLException;
    
}
