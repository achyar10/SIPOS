
package com.models;

import com.controllers.Kembalian;
import com.views.FrmKembalian;
import com.views.FrmTransaksi;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public class Kembalian_model implements Kembalian {

    @Override
    public void Bersih(FrmKembalian kmbl) throws SQLException {
        kmbl.dispose();
        FrmTransaksi.txt_total_1.setText(null);
        FrmTransaksi.txt_total_2.setText(null);
        FrmTransaksi.txt_bayar.setText(null);
        FrmTransaksi.txt_kembali.setText(null);
        new FrmTransaksi().show();
    }
    
}
