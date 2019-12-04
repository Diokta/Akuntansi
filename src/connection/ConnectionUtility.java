/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fadli Acek
 */
public class ConnectionUtility {
    
    public static String ip = "localhost";
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://"+ ip + "/surya_printindo", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}