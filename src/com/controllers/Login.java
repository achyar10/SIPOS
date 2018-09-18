
package com.controllers;
import com.views.FrmLogin;
import java.sql.SQLException;

/**
 *
 * @author achyar
 */
public interface Login {
    public void Login (FrmLogin lgn) throws SQLException;
    public void Bersih (FrmLogin lgn) throws SQLException;
    
}
