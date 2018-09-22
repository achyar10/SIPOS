
package com.controllers;

import com.views.FrmPembelian;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface Pembelian {
    public void Simpan(FrmPembelian pb) throws SQLException;
    public void Ubah (FrmPembelian pb) throws SQLException;
    public void Hapus(FrmPembelian pb) throws SQLException;
    public void Tampil (FrmPembelian pb) throws SQLException;
    public void Bersih(FrmPembelian pb) throws SQLException;
    public void KlikTabel (FrmPembelian pb) throws SQLException;
    public void Combo (FrmPembelian pb) throws SQLException;
    public void Combo2 (FrmPembelian pb) throws SQLException;
    public void Cari (FrmPembelian pb) throws SQLException;
    public void AutoNomor (FrmPembelian pb) throws SQLException;
    public void NoFaktur (FrmPembelian pb) throws SQLException;
}
