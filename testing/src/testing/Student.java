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
            try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM student WHERE KAU_ID=? AND password=?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, KAU_ID);
                    pst.setString(2, password);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            // If a matching record is found, set the details in the current instance
                            // Assuming the columns in the database match the fields in the class
                            Student.KAU_ID = rs.getString("KAU_ID");
                            Student.studentName = rs.getString("studentName");
                            // Set other fields as needed
                            return true;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public static boolean RegisterEventStudent(String ID, String eventNum, String eventName) {
        try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            String query = "INSERT INTO studentEvents (KAU_ID, eventNum, eventName) VALUES (?, ?, ?)";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, ID);
                pst.setString(2, eventNum);
                pst.setString(3, eventName);
                pst.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
        public static boolean deleteEvent(String KAU_ID, String eventNum) {
        try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM studentEvents WHERE KAU_ID = ? AND eventNum = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, KAU_ID);
                pst.setString(2, eventNum);
                return pst.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
   public static boolean joinClub(String KAU_ID, String clubCode) {
        try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            Date joinDate = new Date(Calendar.getInstance().getTime().getTime());

            if (!isClubEnrollmentExists(con, KAU_ID, clubCode)) {
                String query = "INSERT INTO studentClubs (KAU_ID, clubCode, JoinDate) VALUES (?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, KAU_ID);
                    pst.setString(2, clubCode);
                    pst.setDate(3, joinDate);
                     pst.executeUpdate();
                    return true;
                }
            } else {
                System.out.println("Club enrollment already exists!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public static boolean leaveClub(String KAU_ID, String clubCode) {
        try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            if (isClubEnrollmentExists(con, KAU_ID, clubCode)) {
                String query = "DELETE FROM studentClubs WHERE KAU_ID = ? AND clubCode = ?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, KAU_ID);
                    pst.setString(2, clubCode);
                     pst.executeUpdate();
                     return true;
                }
            } else {
                System.out.println("Club enrollment does not exist!");
            }
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
