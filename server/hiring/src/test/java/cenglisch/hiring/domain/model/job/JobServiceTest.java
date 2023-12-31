package cenglisch.hiring.domain.model.job;

import cenglisch.domain.model.EventHandler;
import cenglisch.domain.model.PersonId;
import cenglisch.hiring.domain.model.job.event.AddedResponsibleEmployee;
import cenglisch.hiring.domain.model.job.event.JobCapacitiesReduced;
import cenglisch.hiring.domain.model.job.event.NoMoreCapacitiesAvailable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    @Mock
    EventHandler eventHandler;

    @Mock
    JobSecondaryPort jobSecondaryPort;

    @InjectMocks
    JobService jobService;

    private final JobId jobId = new JobId("J-123");

    @Test
    public void testReduceCapacitiesNoMoreAvailable(){
        Optional<Job> optionalJob = Optional.of(new Job("Anwendungsentwickler", 1));
        when(jobSecondaryPort.find(any(JobId.class))).thenAnswer(invocation -> optionalJob);

        Job job = optionalJob.get();
        assertTrue(job.isPublished());
        jobService.reduceCapacities(jobId);

        verify(jobSecondaryPort).save(any(Job.class));
        verify(eventHandler).publish(any(NoMoreCapacitiesAvailable.class));
        assertFalse(job.isPublished());
    }

    @Test
    public void testReduceCapacitiesMoreAvailable(){
        Optional<Job> optionalJob = Optional.of(new Job("Anwendungsentwickler", 2));
        when(jobSecondaryPort.find(any(JobId.class))).thenAnswer(invocation -> optionalJob);

        Job job = optionalJob.get();

        assertTrue(job.isPublished());
        jobService.reduceCapacities(jobId);

        verify(jobSecondaryPort).save(any(Job.class));
        verify(eventHandler).publish(any(JobCapacitiesReduced.class));
        assertTrue(job.isPublished());
    }

    @Test
    public void testAddResponsibleEmployee(){
        Optional<Job> optionalJob = Optional.of(new Job("Anwendungsentwickler", 2));
        when(jobSecondaryPort.find(any(JobId.class))).thenAnswer(invocation -> optionalJob);

        Job job = optionalJob.get();
        assertEquals(0, job.getResponsibleEmployees().size());
        jobService.addResponsibleEmployee(new JobId("dasdasd"), new ResponsibleEmployee(new PersonId("1231")));
        assertEquals(1, job.getResponsibleEmployees().size());

        verify(jobSecondaryPort).save(any(Job.class));
        verify(eventHandler).publish(any(AddedResponsibleEmployee.class));
    }
}