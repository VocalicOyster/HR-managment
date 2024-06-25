package it.portfolio.hr.humanResource.entities;

import it.portfolio.hr.humanResource.models.Enums.ControctsEnum;
import it.portfolio.hr.humanResource.models.Enums.PerTimeEnum;
import jakarta.persistence.*;

@Entity
@Table
public class JobAnnouncement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ControctsEnum controctsEnum;
    private String description;
    private PerTimeEnum perTimeEnum;
    private String title;
    @ManyToOne
    private Department department;
    private String companyName;

    private boolean isDeleted;

    public JobAnnouncement(ControctsEnum controctsEnum, String description, PerTimeEnum perTimeEnum, String title, Department department, String companyName, boolean isDeleted) {
        this.controctsEnum = controctsEnum;
        this.description = description;
        this.perTimeEnum = perTimeEnum;
        this.title = title;
        this.department = department;
        this.companyName = companyName;
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public ControctsEnum getControctsEnum() {
        return controctsEnum;
    }

    public void setControctsEnum(ControctsEnum controctsEnum) {
        this.controctsEnum = controctsEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PerTimeEnum getPerTimeEnum() {
        return perTimeEnum;
    }

    public void setPerTimeEnum(PerTimeEnum perTimeEnum) {
        this.perTimeEnum = perTimeEnum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
