/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import static org.junit.Assert.*;
import org.junit.Test;

public class StudentTest {

    private static final String VALID_KAU_ID = "2135011";
    private static final String VALID_PASSWORD = "12345";
    private static final String INVALID_KAU_ID = "2135010";
    private static final String INVALID_PASSWORD = "1234";

    private static final String TEST_ID = "2135011";
    private static final String TEST_EVENT_NUM = "29";
    private static final String TEST_EVENT_NAME = "a";
    private static final String NON_EXISTING_EVENT_NUM = "99";

    private static final String TEST_CLUB_CODE = "4";
    private static final String EXISTING_CLUB_CODE = "4";
    private static final String NON_EXISTING_CLUB_CODE = "9";

    @Test
    public void testValidateStudent() {
        assertTrue(Student.validateStudent(VALID_KAU_ID, VALID_PASSWORD));
        assertFalse(Student.validateStudent(INVALID_KAU_ID, INVALID_PASSWORD));
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

    @Test
    public void testJoinClub() {
        assertTrue(Student.joinClub(TEST_ID, TEST_CLUB_CODE));
        assertFalse(Student.joinClub(TEST_ID, EXISTING_CLUB_CODE));
    }

    @Test
    public void testLeaveClub() {
        assertTrue(Student.leaveClub(TEST_ID, TEST_CLUB_CODE));
        assertFalse(Student.leaveClub(TEST_ID, NON_EXISTING_CLUB_CODE));
    }
}
