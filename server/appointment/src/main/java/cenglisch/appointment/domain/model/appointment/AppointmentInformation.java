package cenglisch.appointment.domain.model.appointment;;

@org.jmolecules.ddd.annotation.Entity
public class AppointmentInformation {
    private String caption;
    private String note;
    private String place;

    public AppointmentInformation(final String caption, final String note, final String place) {
        this.caption = caption;
        this.note = note;
        this.place = place;
    }

    public final String getCaption() {
        return caption;
    }

    public final void setCaption(final String caption) {
        this.caption = caption;
    }

    public final String getNote() {
        return note;
    }

    public final void setNote(final String note) {
        this.note = note;
    }

    public final String getPlace() {
        return place;
    }

    public final void setPlace(final String place) {
        this.place = place;
    }
}
