package cenglisch.hiring.startup;

import cenglisch.hiring.application.candidate.command.CandidateCommandApplicationPort;
import cenglisch.hiring.application.candidate.query.CandidateQueryApplicationPort;
import cenglisch.hiring.application.interview.command.state.InterviewStateCommandApplicationPort;
import cenglisch.hiring.application.interview.command.type.InterviewTypeCommandApplicationPort;
import cenglisch.hiring.application.interview.query.InterviewQueryApplicationPort;
import cenglisch.hiring.application.job.command.JobCommandApplicationPort;
import cenglisch.hiring.application.job.query.JobQueryApplicationPort;
import cenglisch.hiring.domain.model.candidate.CandidateService;
import cenglisch.hiring.domain.model.interview.InterviewService;
import cenglisch.hiring.domain.model.job.JobService;
import cenglisch.hiring.port.adapter.secondary.messaging.EventPublisherAdapter;
import cenglisch.hiring.port.adapter.secondary.persistence.candidate.CandidateSecondaryPortAdapter;
import cenglisch.hiring.port.adapter.secondary.persistence.interview.InterviewSecondaryPortAdapter;
import cenglisch.hiring.port.adapter.secondary.persistence.job.JobSecondaryPortAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("checkstyle:DesignForExtension")
public class BeanConfiguration {

    private final EventPublisherAdapter eventHandler;

    private final CandidateSecondaryPortAdapter candidateRepositoryAdapter;
    private final InterviewSecondaryPortAdapter interviewRepositoryAdapter;

    @Bean
    CandidateCommandApplicationPort candidateApplicationPort() {
        return new CandidateCommandApplicationPort(candidateService(), interviewService(), jobService());
    }

    @Bean
    CandidateService candidateService() {
        return new CandidateService(candidateRepositoryAdapter, eventHandler);
    }
    private final JobSecondaryPortAdapter jobRepositoryAdapter;

    @Bean
    InterviewTypeCommandApplicationPort interviewTypeApplicationPort() {
        return new InterviewTypeCommandApplicationPort(interviewService());
    }

    @Bean
    InterviewStateCommandApplicationPort interviewStateApplicationPort() {
        return new InterviewStateCommandApplicationPort(
                interviewService(),
                candidateService(),
                eventHandler
        );
    }

    @Bean
    InterviewService interviewService() {
        return new InterviewService(interviewRepositoryAdapter, eventHandler);
    }

    public BeanConfiguration(
        final EventPublisherAdapter eventHandler,
        final CandidateSecondaryPortAdapter candidateRepositoryAdapter,
        final InterviewSecondaryPortAdapter interviewRepositoryAdapter,
        final JobSecondaryPortAdapter jobRepositoryAdapter
    ) {
        this.eventHandler = eventHandler;
        this.candidateRepositoryAdapter = candidateRepositoryAdapter;
        this.interviewRepositoryAdapter = interviewRepositoryAdapter;
        this.jobRepositoryAdapter = jobRepositoryAdapter;
    }

    @Bean
    JobService jobService() {
        return new JobService(jobRepositoryAdapter, eventHandler);
    }

    @Bean
    JobCommandApplicationPort jobApplicationPort() {
        return new JobCommandApplicationPort(jobService(), candidateRepositoryAdapter, eventHandler);
    }

    @Bean
    JobQueryApplicationPort jobQueryApplicationPort() {
        return new JobQueryApplicationPort(jobRepositoryAdapter);
    }

    @Bean
    CandidateQueryApplicationPort candidateQueryApplicationPort() {
        return new CandidateQueryApplicationPort(candidateRepositoryAdapter);
    }

    @Bean
    InterviewQueryApplicationPort interviewQueryApplicationPort() {
        return new InterviewQueryApplicationPort(interviewRepositoryAdapter);
    }
}
