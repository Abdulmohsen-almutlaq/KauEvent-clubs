package testing;

import java.sql.*;

public class Admin {

    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String NGROK_URL = System.getenv("NGROK_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static String admin_KAU_ID;

    /**
     *
     */
    public Admin() {
        // Constructor if needed
    }

    public static boolean validateAdmin(String admin_KAU_ID, String adminPassword) {
        try {
            Class.forName(JDBC_DRIVER);
            try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM AdminBase WHERE admin_KAU_ID=? AND adminPassword=?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, admin_KAU_ID);
                    pst.setString(2, adminPassword);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            // If a matching record is found, set the details in the current instance
                            Admin.admin_KAU_ID = rs.getString("admin_KAU_ID");
                            // Set other fields as needed
                            return true;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return false;
    }

public boolean insertEvent(String eventName, String eventDescription, java.util.Date eventDate, String Admin_ID) {
    try {
        // Load the JDBC driver
        Class.forName(JDBC_DRIVER);

        // Establish a connection to the database
        try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            // Define the SQL query for event insertion
            String query = "insert into eventBase (eventName, eventDescription, eventDate, Admin_ID) values (?, ?, ?, ?)";

            // Create a PreparedStatement to safely execute the query
            try (PreparedStatement pst = con.prepareStatement(query)) {
                // Set values for the parameters in the query
                pst.setString(1, eventName);
                pst.setString(2, eventDescription);
                java.sql.Date sqlEventDate = new java.sql.Date(eventDate.getTime());
                pst.setDate(3, sqlEventDate);
                pst.setString(4, Admin_ID);

                // Execute the update (insert) operation
                pst.executeUpdate();

                // Return true indicating successful event insertion
                return true;
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }

    // Return false indicating event insertion failure
    return false;
}


public static boolean deleteEventFromDatabase(String eventName) {
    try {
        Class.forName(JDBC_DRIVER);
        try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM eventBase WHERE eventName = ?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, eventName);
                int rowsAffected = pst.executeUpdate();

                // Check if the deletion was successful
                return rowsAffected > 0;
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace(); // Log the exception or handle it as needed
        return false;
    }
}

public static boolean updateEventInDatabase(String eventName, String newEventName, String eventDescription, java.util.Date eventDate, String adminID) {
    try {
        Class.forName(JDBC_DRIVER);
        try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE eventBase SET eventName=?, eventDescription=?, eventDate=?, Admin_ID=? WHERE eventName=?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, newEventName);
                pst.setString(2, eventDescription);
                java.sql.Date sqlEventDate = new java.sql.Date(eventDate.getTime());
                pst.setDate(3, sqlEventDate);
                pst.setString(4, adminID);
                pst.setString(5, eventName);

                int rowsAffected = pst.executeUpdate();

                // Check if the update was successful
                return rowsAffected > 0;
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace(); // Log the exception or handle it as needed
        return false;
    }
}

    public boolean checkIfEventExistsInTable(String eventName) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM eventBase WHERE eventName=?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, eventName);
                    try (ResultSet rs = pst.executeQuery()) {
                        // Check if a record with the given event name exists
                        return rs.next();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Log or handle the exception as needed
            throw new SQLException("Error checking if event exists in table", e);
        }
    }

    public static boolean insertClub(String clubCode, String clubName, String clubDescription, Date clubFounded) {
        try {
            Class.forName(JDBC_DRIVER);
            try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
                String query = "insert into clubBase (clubCode,clubName,clubDescription,clubFounded)values(?,?,?,?)";

                try (PreparedStatement pst = con.prepareStatement(query)) {
                    int clubCodeInt = Integer.parseInt(clubCode);
                    pst.setInt(1, clubCodeInt);
                    pst.setString(2, clubName);
                    pst.setString(3, clubDescription);

                    java.sql.Date sqlClubFounded = new java.sql.Date(clubFounded.getTime());
                    pst.setDate(4, sqlClubFounded);
                    pst.executeUpdate();

                    return true; // Club insertion successful
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }

        return false; // Club insertion failed
    }

    public static  boolean updateClub(String clubCode, String newClubCode, String clubName, String clubDescription, Date clubFounded) {
        try {
            Class.forName(JDBC_DRIVER);
            try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
                String query = "UPDATE clubBase SET clubCode=?, clubName=?, clubDescription=?, clubFounded=? WHERE clubCode=?";

                try (PreparedStatement pst = con.prepareStatement(query)) {
                    int newCode = Integer.parseInt(newClubCode);
                    pst.setInt(1, newCode);
                    pst.setString(2, clubName);
                    pst.setString(3, clubDescription);
                    java.sql.Date sqlClubFounded = new java.sql.Date(clubFounded.getTime());
                    pst.setDate(4, sqlClubFounded);
                    pst.setString(5, clubCode);

                    int rowsAffected = pst.executeUpdate();

                    // Check if the update was successful
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());// Log the exception or handle it as needed
            return false;
        }
    }

    public static boolean deleteClub(String clubCode) {
        try {
            Class.forName(JDBC_DRIVER);
            try ( Connection con = DriverManager.getConnection(NGROK_URL, DB_USER, DB_PASSWORD)) {
                String query = "DELETE FROM clubBase WHERE clubCode=?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, clubCode);
                    int rowsAffected = pst.executeUpdate();

                    // Check if the deletion was successful
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static AdminFunctions loginAdmin(String admin_KAU_ID, String adminPassword) {
        if (validateAdmin(admin_KAU_ID, adminPassword)) {
            // If validation is successful, create an instance of AdminFunctions
            return new AdminFunctions();
        } else {
            return null; // Return null if validation fails
        }
    }
}
