package cenglisch.hiring.port.adapter.primary.candidate;

import cenglisch.hiring.application.candidate.command.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Candidate", description = "APIs für die Bewerberverwaltung.")
@RestController
@RequestMapping("v1/candidate")
public final class V1CandidateCommandRestAdapter {

    private final CandidateCommandApplicationPort candidateCommandApplicationPort;

    public V1CandidateCommandRestAdapter(final CandidateCommandApplicationPort candidateCommandApplicationPort) {
        this.candidateCommandApplicationPort = candidateCommandApplicationPort;
    }

    @PostMapping("candidateApplies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void candidateApplies(@RequestBody final CandidateApplies candidateApplies) {
        candidateCommandApplicationPort.candidateApplies(candidateApplies);
    }

    @PostMapping("approveCandidateApplication")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approveCandidateApplication(
            @RequestBody final ApproveCandidateApplication approveCandidateApplication
    ) {
        candidateCommandApplicationPort.approveCandidateApplication(approveCandidateApplication);
    }

    @PostMapping("rejectCandidateApplication")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectCandidateApplication(@RequestBody final RejectCandidateApplication rejectCandidateApplication) {
        candidateCommandApplicationPort.rejectCandidateApplication(rejectCandidateApplication);
    }

    @PostMapping("adoptCandidate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adoptCandidate(@RequestBody final AdoptCandidate adoptCandidate) {
        candidateCommandApplicationPort.adoptCandidate(adoptCandidate);
    }

    @PostMapping("rejectCandidate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectCandidate(@RequestBody final RejectCandidate rejectCandidate) {
        candidateCommandApplicationPort.rejectCandidate(rejectCandidate);
    }
}
