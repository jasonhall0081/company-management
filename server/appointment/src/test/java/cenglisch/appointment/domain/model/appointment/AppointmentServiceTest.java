package cenglisch.appointment.domain.model.appointment;

import cenglisch.domain.model.EventHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @Mock
    private AppointmentSecondaryPort appointmentSecondaryPort;
    @Mock
    private EventHandler eventHandler;
    @InjectMocks
    private AppointmentService appointmentService;

}
