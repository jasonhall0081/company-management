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
import cenglisch.hiring.port.adapter.secondary.persistence.candidate.CandidateRepositoryAdapter;
import cenglisch.hiring.port.adapter.secondary.persistence.interview.InterviewRepositoryAdapter;
import cenglisch.hiring.port.adapter.secondary.persistence.job.JobRepositoryAdapter;
import cenglisch.hiring.port.adapter.secondary.messaging.EventPublisherAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("checkstyle:DesignForExtension")
public class BeanConfiguration {

    @Autowired
    private EventPublisherAdapter eventHandler;

    @Autowired
    private CandidateRepositoryAdapter candidateRepositoryAdapter;

    @Bean
    CandidateCommandApplicationPort candidateApplicationPort() {
        return new CandidateCommandApplicationPort(candidateService(), interviewService(), jobService());
    }

    @Bean
    CandidateService candidateService() {
        return new CandidateService(candidateRepositoryAdapter, eventHandler);
    }


    @Autowired
    private InterviewRepositoryAdapter interviewRepositoryAdapter;

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

    @Autowired
    private JobRepositoryAdapter jobRepositoryAdapter;

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
