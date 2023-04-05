package cenglisch.hiring.port.adapter.primary.candidate;

import cenglisch.hiring.application.candidate.query.CandidateQueryApplicationPort;
import cenglisch.hiring.application.candidate.query.ShowApplicantCandidates;
import cenglisch.hiring.domain.model.candidate.Candidate;
import cenglisch.hiring.domain.model.job.JobId;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Candidate", description = "APIs für die Bewerberverwaltung.")
@RestController
@RequestMapping("v1/candidate")
public final class V1CandidateQueryRestAdapter {

    private final CandidateQueryApplicationPort candidateQueryApplicationPort;

    public V1CandidateQueryRestAdapter(final CandidateQueryApplicationPort candidateQueryApplicationPort) {
        this.candidateQueryApplicationPort = candidateQueryApplicationPort;
    }

    @GetMapping(params = "jobId")
    public List<Candidate> getCandidates(@RequestParam("jobId") final String jobId) {
        return candidateQueryApplicationPort.showApplicantCandidates(
                new ShowApplicantCandidates(new JobId(jobId))
        );
    }
}
