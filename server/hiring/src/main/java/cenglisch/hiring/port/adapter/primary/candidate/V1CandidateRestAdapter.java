package cenglisch.hiring.port.adapter.primary.candidate;

import cenglisch.hiring.application.candidate.command.*;
import cenglisch.hiring.application.candidate.query.CandidateQueryApplicationPort;
import cenglisch.hiring.application.candidate.query.ShowApplicantCandidates;
import cenglisch.hiring.domain.model.candidate.Candidate;
import cenglisch.hiring.domain.model.candidate.CandidateId;
import cenglisch.hiring.domain.model.job.JobId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Candidate", description = "APIs für die Bewerberverwaltung.")
@RestController
@RequestMapping("v1/candidates")
public final class V1CandidateRestAdapter {

    private final CandidateQueryApplicationPort candidateQueryApplicationPort;
    private final CandidateCommandApplicationPort candidateCommandApplicationPort;

    public V1CandidateRestAdapter(final CandidateCommandApplicationPort candidateCommandApplicationPort, final CandidateQueryApplicationPort candidateQueryApplicationPort) {
        this.candidateCommandApplicationPort = candidateCommandApplicationPort;
        this.candidateQueryApplicationPort = candidateQueryApplicationPort;
    }

    @GetMapping(params = "jobId")
    @Operation(summary = "fetch candidates related to job")
    public List<Candidate> getCandidates(@RequestParam("jobId") final String jobId) {
        return candidateQueryApplicationPort.showApplicantCandidates(
                new ShowApplicantCandidates(new JobId(jobId))
        );
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "candidate applies to job")
    public void candidateApplies(@RequestBody final CandidateApplies candidateApplies) {
        candidateCommandApplicationPort.candidateApplies(candidateApplies);
    }

    @PatchMapping("{candidateId}/approveApplication")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "application gets approved after cv watched")
    public void approveCandidateApplication(@PathVariable("candidateId") final String candidateId) {
        candidateCommandApplicationPort.approveCandidateApplication(new ApproveCandidateApplication(new CandidateId(candidateId)));
    }

    @PatchMapping("{candidateId}/rejectApplication")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "application is not fitting to requirements or company")
    public void rejectCandidateApplication(@PathVariable("candidateId") final String candidateId) {
        candidateCommandApplicationPort.rejectCandidateApplication(new RejectCandidateApplication(new CandidateId(candidateId)));
    }

    @PatchMapping("{candidateId}/adopt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "after interview - candidate gets adopted and been added to the company")
    public void adoptCandidate(@PathVariable("candidateId") final String candidateId) {
        candidateCommandApplicationPort.adoptCandidate(new AdoptCandidate(new CandidateId(candidateId)));
    }

    @PatchMapping("{candidateId}/reject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "after interview - candidate interview was bad")
    public void rejectCandidate(@PathVariable("candidateId") final String candidateId) {
        candidateCommandApplicationPort.rejectCandidate(new RejectCandidate(new CandidateId(candidateId)));
    }
}
