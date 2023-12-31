package testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminTest {

    private static final String TEST_EVENT_NAME = "TestEvenT";
    private static final String TEST_UPDATED_EVENT_NAME = "UpdatedTestEvent";
    private static final String TEST_CLUB_CODE = "65";
    private static final String TEST_UPDATED_CLUB_CODE = "53";
    private static final java.util.Date TEST_DATE = new java.util.Date(System.currentTimeMillis());
    private static final String TEST_ADMIN_KAU_ID = "123";
    private static final String TEST_ADMIN_PASSWORD = "123";

    @BeforeEach
    public void setUp() {
         // Set up code if needed before each test
    }

    @AfterEach
    public void tearDown() {
         // Clean up code if needed after each test
    }

     @Test
      public void testValidateAdminCredentials() {
         assertTrue(Admin.validateAdmin(TEST_ADMIN_KAU_ID,TEST_ADMIN_PASSWORD));

       }

    @Test
    public void testLoginAdminInvalidCredentials() {
        assertFalse(Admin.validateAdmin(TEST_ADMIN_KAU_ID,"9999"));
    }

     @Test
      public void testInsertAndUpdateAndDeleteEvent() throws SQLException {
         // Insert Event
         assertTrue(insertTestEvent());

         //   Update Event
         assertTrue(updateTestEvent());

         //   Check if the updated event exists
           assertTrue(checkIfUpdatedEventExists());

         //    Delete the updated event
           assertTrue(deleteUpdatedEvent());

         //    Check if the event is deleted
          assertFalse(checkIfUpdatedEventExists());
    }

    //    Separate functions for each step:
    private boolean insertTestEvent() throws SQLException {
        return new Admin().insertEvent(TEST_EVENT_NAME, "TestDescription", TEST_DATE, "123");
    }

    private boolean updateTestEvent() throws SQLException {
        return Admin.updateEventInDatabase(TEST_EVENT_NAME, TEST_UPDATED_EVENT_NAME, "UpdatedTestDescription", TEST_DATE, "123");
    }

    private boolean checkIfUpdatedEventExists() throws SQLException {
        return new Admin().checkIfEventExistsInTable(TEST_UPDATED_EVENT_NAME);
    }

    private boolean deleteUpdatedEvent() throws SQLException {
        return Admin.deleteEventFromDatabase(TEST_UPDATED_EVENT_NAME);
    }
    @Test


    public void testInsertAndUpdateAndDeleteInvalidEvent() throws SQLException {


        //  Update Event (should fail)
        assertFalse(updateInvalidTestEvent());

        //   Check if the updated event exists (should be false)
        assertFalse(checkIfInvalidUpdatedEventExists());

        //   Delete the updated event (should fail)
        assertFalse(deleteInvalidUpdatedEvent());

        //   Check if the event is deleted (should be false)
        assertFalse(checkIfInvalidUpdatedEventExists());
    }


    private boolean updateInvalidTestEvent() throws SQLException {
        //   Attempt to update an event that does not exist
        return Admin.updateEventInDatabase("NonexistentEvent", TEST_UPDATED_EVENT_NAME, "UpdatedTestDescription", TEST_DATE, "123");
    }

    private boolean checkIfInvalidUpdatedEventExists() throws SQLException {
        //    Check for an event that does not exist
        return new Admin().checkIfEventExistsInTable("NonexistentEvent");
    }

    private boolean deleteInvalidUpdatedEvent() throws SQLException {
        //    Attempt to delete an event that does not exist
        return Admin.deleteEventFromDatabase("NonexistentEvent");
    }

    @Test
    public void testInsertAndUpdateAndDeleteInvalidClub() {

        //  Update Club (should fail)
        assertFalse(updateInvalidTestClub());

        //   Delete the updated club (should fail)
        assertFalse(deleteInvaliClub());
    }


    private boolean updateInvalidTestClub() {
        //   Attempt to update a club that does not exist
        return Admin.updateClub("NonexistentClub", TEST_UPDATED_CLUB_CODE, "UpdatedTestClub", "UpdatedTestClubDescription", new Date(TEST_DATE.getTime()));
    }

    private boolean deleteInvaliClub() {
        //    Attempt to delete a club that does not exist
        return Admin.deleteClub("NonexistentClub");
    }

      @Test
      public void testInsertAndUpdateAndDeleteClub() {
          //  Insert Club
           assertTrue(insertTestClub());
          //  Update Club
           assertTrue(updateTestClub());
          //    delete Club
           assertTrue(deleteClub());

          }

    //  Separate functions for each step:
    private boolean insertTestClub() {
        return Admin.insertClub(TEST_CLUB_CODE, "TestClub", "TestClubDescription", new Date(TEST_DATE.getTime()));
    }

    private boolean updateTestClub() {
        return Admin.updateClub(TEST_CLUB_CODE, TEST_UPDATED_CLUB_CODE, "UpdatedTestClub", "UpdatedTestClubDescription", new Date(TEST_DATE.getTime()));
    }

    private boolean deleteClub() {
        return Admin.deleteClub(TEST_UPDATED_CLUB_CODE);
    }

}