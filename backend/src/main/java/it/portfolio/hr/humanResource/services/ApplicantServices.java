package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.exceptions.applicant.ApplicantException;
import it.portfolio.hr.humanResource.models.DTOs.request.ApplicantRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.ApplicantResponseDTO;
import it.portfolio.hr.humanResource.repositories.ApplicantRepository;
import it.portfolio.hr.humanResource.validator.ApplicantValidator;
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

    public ApplicantResponseDTO createNewApplicant(ApplicantRequestDTO applicantRequestDTO, String companyName) throws ApplicantException {
        if (applicantValidator.isApplicantValid(applicantRequestDTO, companyName)) {
            Applicants newApplicant = modelMapper.map(applicantRequestDTO, Applicants.class);
            newApplicant.setCompanyName(companyName);
            newApplicant.setDeleted(false);
            applicantRepository.saveAndFlush(newApplicant);
            return modelMapper.map(newApplicant, ApplicantResponseDTO.class);
        }
        throw new ApplicantException("Inserted applicant is not valid", 400);
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

    public boolean controlApplicant(ApplicantRequestDTO applicantRequestDTO, String companyName) {
            Optional<Applicants> applicants = applicantRepository.findByFiscalCode(applicantRequestDTO.getFiscalCode(), companyName);
            return applicants.isPresent();
    }

    public ApplicantResponseDTO getById(Long id, String companyName) throws ApplicantException {
        Applicants applicants = applicantRepository.findById(id, companyName).orElseThrow(() ->  new ApplicantException("No applicant retrieved with id: " + id, 400));
        return modelMapper.map(applicants, ApplicantResponseDTO.class);
    }

    public ApplicantResponseDTO updateById(Long id, ApplicantRequestDTO applicantRequestDTO, String companyName) throws ApplicantException {
        if(applicantValidator.isApplicantValid(applicantRequestDTO, companyName)) {
            Applicants existingApplicant = applicantRepository.findById(id, companyName).orElseThrow(() -> new ApplicantException("No applicant retrieved with id: " + id, 400));

            existingApplicant.setName(applicantRequestDTO.getName());
            existingApplicant.setFiscalCode(applicantRequestDTO.getFiscalCode());

            applicantRepository.saveAndFlush(existingApplicant);
            ApplicantResponseDTO response = modelMapper.map(applicantRequestDTO, ApplicantResponseDTO.class);
            response.setId(existingApplicant.getId());
            return response;
        }
        throw new ApplicantException("Inserted applicant is not valid", 400);
    }

    public ApplicantResponseDTO deleteById(Long id, String companyName) throws ApplicantException {
        Applicants existingApplicant = applicantRepository.findById(id, companyName).orElseThrow(() -> new ApplicantException("No applicant retrieved with id: " + id, 400));
        existingApplicant.setDeleted(true);
        applicantRepository.saveAndFlush(existingApplicant);
        return modelMapper.map(existingApplicant, ApplicantResponseDTO.class);
    }
}
