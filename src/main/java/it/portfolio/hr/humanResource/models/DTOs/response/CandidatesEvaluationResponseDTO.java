package it.portfolio.hr.humanResource.models.DTOs.response;

import it.portfolio.hr.humanResource.entities.Applicants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatesEvaluationResponseDTO {
    private Long id;
    private Applicants applicants;
    private String description;
    private boolean possibilityOfApplication;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getPossibilityOfApplication() {
        return possibilityOfApplication;
    }

    public void setPossibilityOfApplication(boolean possibilityOfApplication) {
        this.possibilityOfApplication = possibilityOfApplication;
    }
}
