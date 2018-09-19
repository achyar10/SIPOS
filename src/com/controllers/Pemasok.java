
package com.controllers;

import com.views.FrmPemasok;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface Pemasok {
    public void Simpan (FrmPemasok spl) throws SQLException;
    public void Ubah (FrmPemasok spl) throws SQLException;
    public void Hapus (FrmPemasok spl) throws SQLException;
    public void Tampil (FrmPemasok spl) throws SQLException;
    public void Bersih (FrmPemasok spl) throws SQLException;
    public void KlikTabel (FrmPemasok spl) throws SQLException;
    public void AutoNomor (FrmPemasok spl) throws SQLException;
    
}
