package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.exceptions.applicant.BadApplicantCredentialsException;
import it.portfolio.hr.humanResource.models.DTOs.request.ApplicantRequestDTO;
import it.portfolio.hr.humanResource.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class ApplicantValidator {

    @Autowired
    private ApplicantRepository applicantRepository;
    public boolean isApplicantValid(ApplicantRequestDTO applicantRequestDTO, String companyName){
        return isApplicantNotNull(applicantRequestDTO) &&
                isFiscalCodeValid(applicantRequestDTO) &&
                applicantExist(applicantRequestDTO, companyName);
    }

    public boolean isApplicantValidPut(ApplicantRequestDTO applicantRequestDTO)  {
        return isApplicantNotNull(applicantRequestDTO) &&
                isFiscalCodeValid(applicantRequestDTO);
    }

    private boolean isApplicantNotNull(ApplicantRequestDTO applicantRequestDTO){
        return applicantRequestDTO.getName() != null &&
                applicantRequestDTO.getSurname() != null &&
                applicantRequestDTO.getFiscalCode() != null;
    }

    private boolean isFiscalCodeValid(ApplicantRequestDTO applicantRequestDTO) {
        return Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", applicantRequestDTO.getFiscalCode());
    }

    private boolean applicantExist(ApplicantRequestDTO applicantRequestDTO, String companyName) {
        Optional<Applicants> optionalApplicant = applicantRepository.findByFiscalCode(applicantRequestDTO.getFiscalCode(), companyName);
        return optionalApplicant.isEmpty();
    }
}
