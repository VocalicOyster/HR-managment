package it.portfolio.hr.humanResource.models.DTOs.response;

import it.portfolio.hr.humanResource.entities.Applicants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewResponseDTO {
    private Long id;

    private Applicants applicants;

    private String interviewDate;
    private String startTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Applicants getApplicants() {
        return applicants;
    }

    public void setApplicants(Applicants applicants) {
        this.applicants = applicants;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
