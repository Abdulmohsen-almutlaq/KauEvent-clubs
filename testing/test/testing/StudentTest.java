package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StudentTest {

    private static final String VALID_KAU_ID = "2135100";
    private static final String VALID_PASSWORD = "12345";
    private static final String INVALID_KAU_ID = "2135710";
    private static final String INVALID_PASSWORD = "1234";

    private static final String TEST_ID = "2135100";
    private static final String TEST_EVENT_NUM = "2";
    private static final String TEST_EVENT_NAME = "space";
    private static final String NON_EXISTING_EVENT_NUM = "99";

    private static final String TEST_CLUB_CODE = "10";
    private static final String EXISTING_CLUB_CODE = "10";
    private static final String NON_EXISTING_CLUB_CODE = "99";

    @Test
    public void testValidateStudent() {
        assertTrue(Student.validateStudent(VALID_KAU_ID, VALID_PASSWORD));
        assertFalse(Student.validateStudent(INVALID_KAU_ID, INVALID_PASSWORD));
    }

    @Test
    public void testJoinAndLeaveClub() {
        // Join the club first
        assertTrue(Student.joinClub(TEST_ID, TEST_CLUB_CODE));

        // Now, try to leave the club
        assertTrue(Student.leaveClub(TEST_ID, TEST_CLUB_CODE));

        // Try to leave a non-existing club, should return false
        assertFalse(Student.leaveClub(TEST_ID, NON_EXISTING_CLUB_CODE));
    }

    @Test
    public void testRegisterEventStudent() {
        assertTrue(Student.RegisterEventStudent(TEST_ID, TEST_EVENT_NUM, TEST_EVENT_NAME));
        assertFalse(Student.RegisterEventStudent(TEST_ID, TEST_EVENT_NUM, "Non"));
    }

    @Test
    public void testDeleteEvent() {
        assertTrue(Student.deleteEvent(TEST_ID, TEST_EVENT_NUM));
        assertFalse(Student.deleteEvent(TEST_ID, NON_EXISTING_EVENT_NUM));
    }
}
