
package com.controllers;

import com.views.FrmLaporan;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface Laporan {
    public void Tampilkan(FrmLaporan lp) throws SQLException;
    
}
