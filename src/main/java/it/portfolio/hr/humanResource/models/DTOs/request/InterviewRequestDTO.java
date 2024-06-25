package it.portfolio.hr.humanResource.models.DTOs.request;

import it.portfolio.hr.humanResource.entities.Applicants;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewRequestDTO {

    private Long id;

    private Long applicants_id;

    private String interviewDate;
    private String startTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicants_id() {
        return applicants_id;
    }

    public void setApplicants_id(Long applicants_id) {
        this.applicants_id = applicants_id;
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
