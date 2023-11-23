/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

/**
 *
 * @author abdul
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class eventinsertT {

    public void insertEvent(String eventName, String eventDescription, Date eventDate, int adminID) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            try (Connection con = DriverManager.getConnection(url)) {
                String query = "insert into eventBase (eventName,eventDescription,eventDate,Admin_ID) values (?,?,?,?)";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, eventName);
                    pst.setString(2, eventDescription);
                    java.sql.Date sqlDate = new java.sql.Date(eventDate.getTime());
                    pst.setDate(3, sqlDate);
                    pst.setInt(4, adminID);
                    pst.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error inserting event", e);
        }
    }
        public boolean checkIfEventExistsInTable(String eventName) throws SQLException {
        boolean eventExists = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            try (Connection con = DriverManager.getConnection(url)) {
                String query = "SELECT COUNT(*) FROM eventBase WHERE eventName = ?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, eventName);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            int count = rs.getInt(1);
                            eventExists = count > 0;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error checking if event exists", e);
        }
        return eventExists;
    }
}