package com.cenglisch.appointment.domain.appointment.event;

import com.cenglisch.appointment.domain.appointment.AppointmentId;

public record AppointmentFinished(AppointmentId appointmentId) implements AppointmentEvent {
}
