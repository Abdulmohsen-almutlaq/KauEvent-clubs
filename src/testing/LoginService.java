/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.sql.*;

public class LoginService {

    public boolean authenticateUser(String username, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            
            try (Connection con = DriverManager.getConnection(url);
                 PreparedStatement pst = con.prepareStatement("SELECT * FROM student WHERE KAU_ID=? AND password = ?")) {
                
                pst.setString(1, username);
                pst.setString(2, password);

                try (ResultSet rs = pst.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // log the exception in a real application
            return false;
        }
    }
}

