package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.entities.CandidateEvaluations;
import it.portfolio.hr.humanResource.exceptions.candidateEv.CandidateEvaluationException;
import it.portfolio.hr.humanResource.models.DTOs.request.CandidateEvaluationRequestDTO;
import it.portfolio.hr.humanResource.repositories.CandidateEvaluationRepository;
import it.portfolio.hr.humanResource.services.CandidateEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CandidateEvaluationValidator {

    @Autowired
    private CandidateEvaluationRepository repository;

    public boolean isCandidateEvaluationValid(CandidateEvaluationRequestDTO candidateEvaluationRequestDTO, String companyName) {
        return isCandidatesEvaluationsNotNull(candidateEvaluationRequestDTO) &&
                isApplicantAlreadyTaken(candidateEvaluationRequestDTO, companyName);
    }

    private boolean isCandidatesEvaluationsNotNull(CandidateEvaluationRequestDTO candidateEvaluationRequestDTO)  {
        if(candidateEvaluationRequestDTO.getPossibilityOfApplication() == null) {
            return false;
        }
        return candidateEvaluationRequestDTO.getApplicants_id() != null &&
                candidateEvaluationRequestDTO.getDescription() != null;
    }

    private boolean isApplicantAlreadyTaken(CandidateEvaluationRequestDTO candidateEvaluationRequestDTO, String companyName){
        List<CandidateEvaluations> list = repository.findAll(companyName);

        for (CandidateEvaluations candidateEvaluations : list) {
            Applicants applicants = candidateEvaluations.getApplicants();
            if (applicants != null) {
                if (Objects.equals(applicants.getId(), candidateEvaluationRequestDTO.getApplicants_id())) {
                    return false;
                }
            }
        }
        return true;
    }
}
