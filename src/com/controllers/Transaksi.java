
package com.controllers;

import com.views.FrmTransaksi;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface Transaksi {
    public void Simpan (FrmTransaksi trx) throws SQLException;
    public void Ubah (FrmTransaksi trx) throws SQLException;
    public void Hapus (FrmTransaksi trx) throws SQLException;
    public void Tampil (FrmTransaksi trx) throws SQLException;
    public void KlikTabel (FrmTransaksi trx) throws SQLException;
    public void Bersih (FrmTransaksi trx) throws SQLException;
    public void Tanggal (FrmTransaksi trx) throws SQLException;
    public void ScanBarcode (FrmTransaksi trx) throws SQLException;
    public void AutoNomor (FrmTransaksi trx) throws SQLException;
    public void AutoNomorKode (FrmTransaksi trx) throws SQLException;
    public void Total (FrmTransaksi trx) throws SQLException;
    public void Kembalian (FrmTransaksi trx) throws SQLException;
    public void UbahJumlah (FrmTransaksi trx) throws SQLException;
    public void CetakStruk (FrmTransaksi trx) throws SQLException;
    
}
