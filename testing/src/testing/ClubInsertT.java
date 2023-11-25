/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ClubInsertT {

    public void insertClub(int clubCode, String clubName, String clubDescription, Date clubFounded) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            try (Connection con = DriverManager.getConnection(url)) {
                String query = "insert into clubBase (clubCode,clubName,clubDescription,clubFounded) values (?,?,?,?)";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setInt(1, clubCode);
                    pst.setString(2, clubName);
                    pst.setString(3, clubDescription);
                    java.sql.Date sqlDate = new java.sql.Date(clubFounded.getTime());
                    pst.setDate(4, sqlDate);
                    pst.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error inserting club", e);
        }
    }

    public boolean checkIfClubExistsInTable(int clubCode) throws SQLException {
        boolean clubExists = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            try (Connection con = DriverManager.getConnection(url)) {
                String query = "SELECT COUNT(*) FROM clubBase WHERE clubCode = ?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setInt(1, clubCode);
                    try (java.sql.ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            int count = rs.getInt(1);
                            clubExists = count > 0;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error checking if club exists", e);
        }
        return clubExists;
    }
}

