package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Interview;
import it.portfolio.hr.humanResource.models.DTOs.request.InterviewRequestDTO;
import it.portfolio.hr.humanResource.repositories.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class InterviewValidator {

    @Autowired
    private InterviewRepository interviewRepository;


    public boolean isInterviewValid(InterviewRequestDTO interviewRequestDTO, String companyName) {
        return isInterviewNotNull(interviewRequestDTO) &&
                isStartTimeValid(interviewRequestDTO) &&
                isDateValid(interviewRequestDTO) &&
                isDateAndTimeFree(interviewRequestDTO, companyName);
    }

    private boolean isInterviewNotNull(InterviewRequestDTO interviewRequestDTO) {
        return interviewRequestDTO.getInterviewDate() != null &&
                interviewRequestDTO.getApplicants_id() != null &&
                interviewRequestDTO.getStartTime() != null;
    }

    private boolean isDateValid(InterviewRequestDTO interviewRequestDTO) {
        return Pattern.matches("\\d\\d+/+\\d\\d+/+\\d\\d\\d\\d", interviewRequestDTO.getInterviewDate()) ||
                Pattern.matches("\\d\\d+/+\\d\\d+/+\\d\\d", interviewRequestDTO.getInterviewDate()) ||
                Pattern.matches("\\d+/+\\d+/+\\d\\d", interviewRequestDTO.getInterviewDate());
    }

    private boolean isStartTimeValid(InterviewRequestDTO interviewRequestDTO) {
        return Pattern.matches("\\d\\d+:+\\d\\d", interviewRequestDTO.getStartTime());
    }

    private boolean isDateAndTimeFree(InterviewRequestDTO interviewRequestDTO, String companyName) {
        Optional<Interview> startTimeInterview = interviewRepository.findByStartTime(interviewRequestDTO.getStartTime(), companyName);

        return startTimeInterview.isEmpty();
    }
}
