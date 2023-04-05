package cenglisch.appointment.domain.model.appointment.date;

@org.jmolecules.ddd.annotation.ValueObject
public final class Time {
    private String time;

    public Time(final String time) {
        if (!isValidTime(time)) {
            throw new IllegalArgumentException("Invalid time format. Expected format: HH:mm in 24-hour format");
        }
        setTime(time);
    }

    private void setTime(final String time) {
        this.time = time;
    }

    private boolean isValidTime(final String time) {
        return time.matches("^([01][0-9]|2[0-3]):[0-5][0-9]$");
    }

    public String getTime() {
        return time;
    }
}
