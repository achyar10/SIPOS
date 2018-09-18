
package com.koneksi;

/**
 *
 * @author achyar
 */
public class UserID {
    private static  String username;
    private static  String ufullname;
    
    public static void setUserLogin(String username) {
        UserID.username = username;
    }
    
    public static String getUserLogin(){
        return username;
    }
    
    public static void setNameUserLogin(String ufullname) {
        UserID.ufullname = ufullname;
    }
    
    public static String getNameUserLogin(){
        return ufullname;
    }
    
}
