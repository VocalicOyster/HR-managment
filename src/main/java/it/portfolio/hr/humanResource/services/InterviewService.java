package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.entities.Interview;
import it.portfolio.hr.humanResource.models.DTOs.request.InterviewRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.InterviewResponseDTO;
import it.portfolio.hr.humanResource.repositories.ApplicantRepository;
import it.portfolio.hr.humanResource.repositories.InterviewRepository;
import it.portfolio.hr.humanResource.validator.InterviewValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewService {


    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InterviewValidator interviewValidator;
    @Autowired
    private ApplicantRepository applicantRepository;


    public InterviewResponseDTO createInterview(InterviewRequestDTO interviewRequestDTO, String companyName) {
        if(interviewValidator.isInterviewValid(interviewRequestDTO, companyName)) {
            Interview interview = modelMapper.map(interviewRequestDTO, Interview.class);
            Applicants applicants = applicantRepository.findById(interviewRequestDTO.getApplicants_id(), companyName).orElse(null);
            if(applicants == null) {
                return null;
            }
            interview.setApplicants(applicants);
            interview.setCompanyName(companyName);
            interview.setDeleted(false);
            interviewRepository.saveAndFlush(interview);
            return modelMapper.map(interview, InterviewResponseDTO.class);
        }
        return null;
    }
    public List<InterviewResponseDTO> getAllInterview(String companyName) {
        List<Interview> interviewList = interviewRepository.findAll(companyName);
        List<InterviewResponseDTO> interviewResponseDTO = new ArrayList<>();
        for(Interview interview : interviewList) {
            InterviewResponseDTO interviewResponseDTOSingle = modelMapper.map(interview, InterviewResponseDTO.class);
            interviewResponseDTOSingle.setApplicants(interview.getApplicants());
            interviewResponseDTO.add(interviewResponseDTOSingle);
        }
        return interviewResponseDTO;
    }

    public InterviewResponseDTO getById(Long id, String companyName) {
        Interview interview = interviewRepository.findById(id, companyName).orElse(null);
        if(interview != null) {
            return modelMapper.map(interview, InterviewResponseDTO.class);
        }
        return null;
    }

    public InterviewResponseDTO updateById(Long id, InterviewRequestDTO interviewRequestDTO, String companyName) {
        Interview interview = interviewRepository.findById(id, companyName).orElse(null);
        System.out.println(interview);
        if(interview == null) {
            return null;
        }

        Applicants applicants = applicantRepository.findById(interviewRequestDTO.getApplicants_id(), companyName).orElse(null);
        if(applicants == null) {
            return null;
        }
        interview.setApplicants(applicants);
        interview.setInterviewDate(interviewRequestDTO.getInterviewDate());
        interview.setStartTime(interviewRequestDTO.getStartTime());

        interviewRepository.saveAndFlush(interview);
        return modelMapper.map(interview, InterviewResponseDTO.class);
    }

    public InterviewResponseDTO deleteById(Long id, String companyName) {
        Interview interview = interviewRepository.findById(id, companyName).orElse(null);
        if(interview == null) {
            return null;
        }
        //interviewRepository.deleteById(id);
        interview.setDeleted(true);
        interviewRepository.saveAndFlush(interview);
        return modelMapper.map(interview, InterviewResponseDTO.class);

    }
}
