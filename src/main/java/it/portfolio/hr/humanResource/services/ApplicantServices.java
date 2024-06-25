package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.exceptions.applicant.BadApplicantCredentialsException;
import it.portfolio.hr.humanResource.exceptions.applicant.NoApplicantException;
import it.portfolio.hr.humanResource.models.DTOs.request.ApplicantRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.ApplicantResponseDTO;
import it.portfolio.hr.humanResource.repositories.ApplicantRepository;
import it.portfolio.hr.humanResource.validator.ApplicantValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServices {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApplicantValidator applicantValidator;

    public ApplicantResponseDTO createNewApplicant(ApplicantRequestDTO applicantRequestDTO, String companyName) {
        if (applicantValidator.isApplicantValid(applicantRequestDTO, companyName)) {
            Applicants newApplicant = modelMapper.map(applicantRequestDTO, Applicants.class);
            newApplicant.setCompanyName(companyName);
            newApplicant.setDeleted(false);
            applicantRepository.saveAndFlush(newApplicant);
            return modelMapper.map(newApplicant, ApplicantResponseDTO.class);
        }
        return null;
    }

    public List<ApplicantResponseDTO> getAllApplicant(String companyName) {
        List<Applicants> appList = applicantRepository.findAll(companyName);

        List<ApplicantResponseDTO> applicantResponseDTOList = new ArrayList<>();

        for (Applicants applicants : appList) {
            ApplicantResponseDTO applicantResponseDTO = modelMapper.map(applicants, ApplicantResponseDTO.class);
            applicantResponseDTOList.add(applicantResponseDTO);
        }
        return applicantResponseDTOList;
    }

    public ApplicantResponseDTO getById(Long id, String companyName) {
        Optional<Applicants> applicants = applicantRepository.findById(id, companyName);

        if (applicants.isPresent()) {
            return modelMapper.map(applicants, ApplicantResponseDTO.class);
        }
        return null;
    }

    public ApplicantResponseDTO updateById(Long id, ApplicantRequestDTO applicantRequestDTO, String companyName)  {
        if(applicantValidator.isApplicantValid(applicantRequestDTO, companyName)) {
            Applicants existingApplicant = applicantRepository.findById(id, companyName).orElse(null);

            if (existingApplicant == null) {
                return null;
            }

            existingApplicant.setName(applicantRequestDTO.getName());
            existingApplicant.setFiscalCode(applicantRequestDTO.getFiscalCode());
            existingApplicant.setSurname(applicantRequestDTO.getSurname());

            applicantRepository.saveAndFlush(existingApplicant);
            ApplicantResponseDTO response = modelMapper.map(applicantRequestDTO, ApplicantResponseDTO.class);
            response.setId(existingApplicant.getId());
            return response;
        }
        return null;
    }

    public ApplicantResponseDTO deleteById(Long id, String companyName){
        Applicants existingApplicant = applicantRepository.findById(id, companyName).orElse(null);

        if (existingApplicant == null) {
            return null;
        }

        //applicantRepository.deleteById(id);
        existingApplicant.setDeleted(true);
        applicantRepository.saveAndFlush(existingApplicant);
        return modelMapper.map(existingApplicant, ApplicantResponseDTO.class);
    }
}
