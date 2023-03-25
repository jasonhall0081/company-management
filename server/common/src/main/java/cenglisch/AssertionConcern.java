package cenglisch;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class AssertionConcern {

    protected final void assertArgumentNotNull(final Object object, final String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    protected final void assertArgumentLength(
            final String aString,
            final int aMinimum,
            final int aMaximum,
            final String aMessage
    ) {
        int length = aString.trim().length();
        if (length < aMinimum || length > aMaximum) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected final void assertArgumentRange(
            final int aValue,
            final int aMinimum,
            final int aMaximum, final String aMessage
    ) {
        if (aValue < aMinimum || aValue > aMaximum) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected final void assertArgumentFuture(final Time aTime, final String aMessage) {
        LocalTime now = LocalTime.now();
        if (aTime.toLocalTime().isBefore(now)) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected final void assertArgumentFuture(final Date aDate, final String aMessage) {
        LocalDate now = LocalDate.now();
        if (aDate.toLocalDate().isBefore(now)) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected final void assertArgumentPast(final Time aTime, final String aMessage) {
        LocalTime now = LocalTime.now();
        if (aTime.toLocalTime().isAfter(now)) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected final void assertArgumentPast(final Date aDate, final String aMessage) {
        LocalDate now = LocalDate.now();
        if (aDate.toLocalDate().isAfter(now)) {
            throw new IllegalArgumentException(aMessage);
        }
    }
}
