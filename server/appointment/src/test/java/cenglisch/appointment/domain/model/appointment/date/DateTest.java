package cenglisch.appointment.domain.model.appointment.date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testValidDate() {
        Date date = new Date("01.01.2022");
        assertEquals("01.01.2022", date.getDate());
    }

    @Test
    void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Date("01-01-2022");
        });
    }

    @Test
    void testEquals() {
        Date date1 = new Date("01.01.2022");
        Date date2 = new Date("01.01.2022");
        assertEquals(date1, date2);
    }

    @Test
    void testNotEquals() {
        Date date1 = new Date("01.01.2022");
        Date date2 = new Date("02.02.2022");
        assertNotEquals(date1, date2);
    }

    @Test
    void testHashCode() {
        Date date1 = new Date("01.01.2022");
        Date date2 = new Date("01.01.2022");
        assertEquals(date1.hashCode(), date2.hashCode());
    }

    @Test
    void testToString() {
        Date date = new Date("01.01.2022");
        assertEquals("Date[date=01.01.2022]", date.toString());
    }

    @Test
    void testMinimumDate() {
        Date date = new Date("01.01.0001");
        assertEquals("01.01.0001", date.getDate());
    }

    @Test
    void testMaximumDate() {
        Date date = new Date("31.12.9999");
        assertEquals("31.12.9999", date.getDate());
    }

    @Test
    void testInvalidMinimumDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Date("00.00.0000");
        });
    }

    @Test
    void testInvalidMaximumDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Date("32.13.10000");
        });
    }

}
