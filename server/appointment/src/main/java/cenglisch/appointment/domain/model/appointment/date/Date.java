package cenglisch.appointment.domain.model.appointment.date;

@org.jmolecules.ddd.annotation.ValueObject
public final class Date {
    private String date;
    public Date(final String date) {
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Expected format: DD.MM.YYYY");
        }
        setDate(date);
    }

    private boolean isValidDate(final String date) {
        return date.matches("^(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.]\\d{4}$");
    }

    public String getDate() {
        return date;
    }

    private void setDate(final String date) {
        this.date = date;
    }
}
