package cenglisch.appointment.domain.model.appointment.date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    void testValidTime() {
        Time time = new Time("12:34");
        assertEquals("12:34", time.getTime());
    }

    @Test
    void testInvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Time("24:00");
        });
    }

    @Test
    void testEquals() {
        Time time1 = new Time("12:34");
        Time time2 = new Time("12:34");
        assertEquals(time1, time2);
    }

    @Test
    void testNotEquals() {
        Time time1 = new Time("12:34");
        Time time2 = new Time("23:45");
        assertNotEquals(time1, time2);
    }

    @Test
    void testHashCode() {
        Time time1 = new Time("12:34");
        Time time2 = new Time("12:34");
        assertEquals(time1.hashCode(), time2.hashCode());
    }

    @Test
    void testToString() {
        Time time = new Time("12:34");
        assertEquals("Time[time=12:34]", time.toString());
    }

    @Test
    void testMinimumTime() {
        Time time = new Time("00:00");
        assertEquals("00:00", time.getTime());
    }

    @Test
    void testMaximumTime() {
        Time time = new Time("23:59");
        assertEquals("23:59", time.getTime());
    }

    @Test
    void testInvalidMinimumTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Time("-01:00");
        });
    }

    @Test
    void testInvalidMaximumTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Time("24:00");
        });
    }

}
