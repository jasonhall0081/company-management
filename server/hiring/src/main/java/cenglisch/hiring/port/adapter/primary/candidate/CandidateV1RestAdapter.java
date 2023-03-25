package cenglisch.hiring.port.adapter.primary.candidate;

import cenglisch.hiring.application.candidate.AdoptCandidate;
import cenglisch.hiring.application.candidate.ApproveCandidateApplication;
import cenglisch.hiring.application.candidate.CandidateApplies;
import cenglisch.hiring.application.candidate.CandidateCommandApplicationPort;
import cenglisch.hiring.application.candidate.RejectCandidate;
import cenglisch.hiring.application.candidate.RejectCandidateApplication;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Candidate", description = "APIs für die Bewerberverwaltung.")
@RestController
@RequestMapping("v1/candidate")
public final class CandidateV1RestAdapter {

    @Autowired
    private CandidateCommandApplicationPort candidateCommandApplicationPort;

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
