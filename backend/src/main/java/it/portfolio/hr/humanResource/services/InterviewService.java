package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.entities.Interview;
import it.portfolio.hr.humanResource.exceptions.interviews.InterviewException;
import it.portfolio.hr.humanResource.exceptions.applicant.ApplicantException;
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


    public InterviewResponseDTO createInterview(InterviewRequestDTO interviewRequestDTO, String companyName) throws ApplicantException, InterviewException {
        if (interviewValidator.isInterviewValid(interviewRequestDTO, companyName)) {
            Interview interview = modelMapper.map(interviewRequestDTO, Interview.class);
            Applicants applicants = applicantRepository.findById(interviewRequestDTO.getApplicants_id(), companyName).orElseThrow(() -> new ApplicantException("No applicants retrieved with id: " + interviewRequestDTO.getApplicants_id(), 400));

            interview.setApplicants(applicants);
            interview.setCompanyName(companyName);
            interview.setDeleted(false);
            interviewRepository.saveAndFlush(interview);
            return modelMapper.map(interview, InterviewResponseDTO.class);
        }
        throw new InterviewException("The inserted interview's information are not valid", 400);
    }

    public List<InterviewResponseDTO> getAllInterview(String companyName) {
        List<Interview> interviewList = interviewRepository.findAll(companyName);
        List<InterviewResponseDTO> interviewResponseDTO = new ArrayList<>();
        for (Interview interview : interviewList) {
            InterviewResponseDTO interviewResponseDTOSingle = modelMapper.map(interview, InterviewResponseDTO.class);
            interviewResponseDTOSingle.setApplicants(interview.getApplicants());
            interviewResponseDTO.add(interviewResponseDTOSingle);
        }
        return interviewResponseDTO;
    }

    public InterviewResponseDTO getById(Long id, String companyName) throws InterviewException {
        Interview interview = interviewRepository.findById(id, companyName).orElseThrow(() -> new InterviewException("No interview retrieved with id" + id, 400));
        return modelMapper.map(interview, InterviewResponseDTO.class);
    }

    public InterviewResponseDTO updateById(Long id, InterviewRequestDTO interviewRequestDTO, String companyName) throws InterviewException, ApplicantException {
        Interview interview = interviewRepository.findById(id, companyName).orElseThrow(() -> new InterviewException("No interview retrieved with id" + id, 400));

        Applicants applicants = applicantRepository.findById(interviewRequestDTO.getApplicants_id(), companyName).orElseThrow(() ->new ApplicantException("No applicants retrieved with id" + interviewRequestDTO.getApplicants_id(), 400) );

        interview.setApplicants(applicants);
        interview.setInterviewDate(interviewRequestDTO.getInterviewDate());
        interview.setStartTime(interviewRequestDTO.getStartTime());

        interviewRepository.saveAndFlush(interview);
        return modelMapper.map(interview, InterviewResponseDTO.class);
    }

    public InterviewResponseDTO deleteById(Long id, String companyName) throws InterviewException {
        Interview interview = interviewRepository.findById(id, companyName).orElseThrow(() -> new InterviewException("No interview retrieved with id" + id, 400));
        ;
        interview.setDeleted(true);
        interviewRepository.saveAndFlush(interview);
        return modelMapper.map(interview, InterviewResponseDTO.class);

    }
}
