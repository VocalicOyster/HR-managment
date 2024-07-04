package it.portfolio.hr.humanResource.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class CandidateEvaluations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Applicants applicants;
    private String description;
    private boolean possibilityOfApplication;
    private String companyName;
    private boolean isDeleted;


    public CandidateEvaluations(Applicants applicants, String description, boolean possibilityOfApplication, String companyName, boolean isDeleted) {
        this.applicants = applicants;
        this.description = description;
        this.possibilityOfApplication = possibilityOfApplication;
        this.companyName = companyName;
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isPossibilityOfApplication() {
        return possibilityOfApplication;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

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
