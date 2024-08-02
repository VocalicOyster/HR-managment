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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Scheduled(cron = "0 0 2 * * 3")
    private void deleteAppAndInterview() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        List<Interview> interviewList = interviewRepository.findAllUndeleted();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            for(Interview interview : interviewList) {
                LocalDate interviewDate = LocalDate.parse(interview.getInterviewDate(), dateTimeFormatter);
                if(interviewDate.isBefore(now)) {
                    Long applicantId = interview.getApplicants().getId();
                    interviewRepository.deleteById(interview.getId());
                    applicantRepository.deleteById(applicantId);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<InterviewResponseDTO> getAllInterview(String companyName) {
        List<Interview> interviewList = interviewRepository.findAllByCompanyName(companyName);
        List<InterviewResponseDTO> interviewResponseDTO = new ArrayList<>();
        for (Interview interview : interviewList) {
            InterviewResponseDTO interviewResponseDTOSingle = modelMapper.map(interview, InterviewResponseDTO.class);
            interviewResponseDTOSingle.setApplicants(interview.getApplicants());
            interviewResponseDTO.add(interviewResponseDTOSingle);
        }
        return interviewResponseDTO;
    }

    public List<String> getAvailableTimes(String date, String companyName) {
        String[] array = {
                "10:00",
                "10:30",
                "11:00",
                "11:30",
                "12:00",
                "12:30",
                "13:00",
                "13:30",
                "14:00",
                "14:30",
                "15:00",
                "15:30",
                "16:00",
                "16:30"
        };
        List<String> initialTimes = new ArrayList<>(Arrays.asList(array));
        List<Interview> unavailableTimes = interviewRepository.findByDate(date, companyName);
        for(Interview interview : unavailableTimes) {
            initialTimes.removeIf(time -> Objects.equals(interview.getStartTime(), time));
        }

        return initialTimes;
    }

    public boolean controlInterview(InterviewRequestDTO interviewRequestDTO, String companyName) {
        List<Interview> interviewList = interviewRepository.findByInterviewDate(interviewRequestDTO.getInterviewDate(), companyName);
        if (interviewList.isEmpty()) {
            return false;
        }

        for (Interview interview : interviewList) {
            if (Objects.equals(interview.getStartTime(), interviewRequestDTO.getStartTime())) {
                return true;
            }
        }
        return false;
    }

    public InterviewResponseDTO getById(Long id, String companyName) throws InterviewException {
        Interview interview = interviewRepository.findById(id, companyName).orElseThrow(() -> new InterviewException("No interview retrieved with id" + id, 400));
        return modelMapper.map(interview, InterviewResponseDTO.class);
    }

    public InterviewResponseDTO updateById(Long id, InterviewRequestDTO interviewRequestDTO, String companyName) throws InterviewException, ApplicantException {
        Interview interview = interviewRepository.findById(id, companyName).orElseThrow(() -> new InterviewException("No interview retrieved with id" + id, 400));

        Applicants applicants = applicantRepository.findById(interviewRequestDTO.getApplicants_id(), companyName).orElseThrow(() -> new ApplicantException("No applicants retrieved with id" + interviewRequestDTO.getApplicants_id(), 400));

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
