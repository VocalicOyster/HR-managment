package it.portfolio.hr.humanResource.models.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CandidateEvaluationRequestDTO {
    private Long id;

    private Long applicants_id;
    private String description;
    private Boolean possibilityOfApplication;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPossibilityOfApplication() {
        return possibilityOfApplication;
    }

    public void setPossibilityOfApplication(boolean possibilityOfApplication) {
        this.possibilityOfApplication = possibilityOfApplication;
    }
}
