package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.entities.CandidateEvaluations;
import it.portfolio.hr.humanResource.exceptions.applicant.ApplicantException;
import it.portfolio.hr.humanResource.exceptions.candidateEv.CandidateEvaluationException;
import it.portfolio.hr.humanResource.models.DTOs.request.CandidateEvaluationRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.CandidatesEvaluationResponseDTO;
import it.portfolio.hr.humanResource.repositories.ApplicantRepository;
import it.portfolio.hr.humanResource.repositories.CandidateEvaluationRepository;
import it.portfolio.hr.humanResource.validator.CandidateEvaluationValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateEvaluationService {
    @Autowired
    private CandidateEvaluationValidator candidateValidator;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateEvaluationRepository candidateRepository;
    @Autowired
    private ApplicantRepository applicantRepository;

    public CandidatesEvaluationResponseDTO createCandidateEv(CandidateEvaluationRequestDTO candidateEvaluationRequestDTO, String companyName) throws CandidateEvaluationException, ApplicantException {
        if (candidateValidator.isCandidateEvaluationValid(candidateEvaluationRequestDTO, companyName)) {
            CandidateEvaluations candidate = modelMapper.map(candidateEvaluationRequestDTO, CandidateEvaluations.class);
            Applicants applicants = applicantRepository.findById(candidateEvaluationRequestDTO.getApplicants_id(), companyName).orElseThrow(() -> new ApplicantException("Unable to retrieve the indicated applicant", 400));

            candidate.setApplicants(applicants);
            candidate.setDescription(candidateEvaluationRequestDTO.getDescription());
            candidate.setCompanyName(companyName);
            candidate.setDeleted(false);
            candidateRepository.saveAndFlush(candidate);
            return modelMapper.map(candidate, CandidatesEvaluationResponseDTO.class);
        }
        throw new CandidateEvaluationException("The inserted candidate evaluation is not valid", 400);
    }

    public List<CandidatesEvaluationResponseDTO> getAllCanEv(String companyName) {
        List<CandidateEvaluations> list = candidateRepository.findAll(companyName);

        List<CandidatesEvaluationResponseDTO> candidates = new ArrayList<>();

        for (CandidateEvaluations can : list) {
            CandidatesEvaluationResponseDTO candidate = modelMapper.map(can, CandidatesEvaluationResponseDTO.class);
            candidate.setApplicants(candidate.getApplicants());
            candidates.add(candidate);
        }
        return candidates;
    }

    public CandidatesEvaluationResponseDTO getById(Long id, String companyName) throws CandidateEvaluationException {
        CandidateEvaluations candidate = candidateRepository.findById(id, companyName).orElseThrow(() -> new CandidateEvaluationException("No candidate evaluation retrieved with id: " + id, 400));
        CandidatesEvaluationResponseDTO candidateDTO = modelMapper.map(candidate, CandidatesEvaluationResponseDTO.class);
        candidateDTO.setApplicants(candidateDTO.getApplicants());
        return candidateDTO;

    }

    public CandidatesEvaluationResponseDTO updateCandidate(Long id, CandidateEvaluationRequestDTO candidateEvaluationRequestDTO, String companyName) throws CandidateEvaluationException {
        CandidateEvaluations candidate = candidateRepository.findById(id, companyName).orElseThrow(() -> new CandidateEvaluationException("No candidate evaluation retrieved with id: " + id, 400));

        Long idApp = candidate.getApplicants().getId();
        Applicants applicants = applicantRepository.findById(idApp, companyName).orElse(null);
        CandidateEvaluations candidateEvaluations = modelMapper.map(candidateEvaluationRequestDTO, CandidateEvaluations.class);
        candidate.setApplicants(applicants);
        candidate.setId(candidate.getId());
        candidate.setDescription(candidateEvaluations.getDescription());
        candidate.setPossibilityOfApplication(candidateEvaluations.getPossibilityOfApplication());
        candidateRepository.saveAndFlush(candidate);

        return modelMapper.map(candidate, CandidatesEvaluationResponseDTO.class);

    }

    public CandidatesEvaluationResponseDTO deleteById(Long id, String companyName) throws CandidateEvaluationException {
        CandidateEvaluations candidateEvaluations = candidateRepository.findById(id, companyName).orElseThrow(() -> new CandidateEvaluationException("No candidate evaluation retrieved with id: " + id, 400));

        Long idApp = candidateEvaluations.getApplicants().getId();
        Applicants applicants = applicantRepository.findById(idApp, companyName).orElse(null);
        candidateEvaluations.setApplicants(applicants);
        candidateEvaluations.setDeleted(true);
        candidateRepository.saveAndFlush(candidateEvaluations);
        return modelMapper.map(candidateEvaluations, CandidatesEvaluationResponseDTO.class);
    }
}
