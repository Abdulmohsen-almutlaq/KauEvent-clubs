/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.sql.*;
import java.util.Calendar;

public class Student {
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String NGROK_URL = System.getenv("NGROK_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static String KAU_ID;

    private static String studentName;
    // Constructor

    static String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Student() {
        // Initialize other fields if needed
    }

    // Getter methods
    public static String getKAU_ID() {
        return KAU_ID;
    }

    public static String getStudentName() {
        return studentName;
    }

    public static boolean validateStudent(String KAU_ID, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
                // ...
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public static boolean RegisterEventStudent(String ID, String eventNum, String eventName) {
        try (Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            // ...
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // ...

    public static boolean leaveClub(String KAU_ID, String clubCode) {
        try (Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            // ...
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    private static boolean isClubEnrollmentExists(Connection con, String KAU_ID, String clubCode) throws SQLException {
        String query = "SELECT 1 FROM studentClubs WHERE KAU_ID = ? AND clubCode = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, KAU_ID);
            pst.setString(2, clubCode);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }


    // Method to get the current instance
    public static Student getCurrentInstance() {
        return new Student();
    }
}
