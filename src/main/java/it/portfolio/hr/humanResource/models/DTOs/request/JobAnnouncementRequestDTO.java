package it.portfolio.hr.humanResource.models.DTOs.request;

import it.portfolio.hr.humanResource.models.Enums.PerTimeEnum;
import it.portfolio.hr.humanResource.models.Enums.ControctsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAnnouncementRequestDTO {
    private Long id;

    private String title;

    private Long department_id;

    private String description;

    private ControctsEnum controctsEnum;

    private PerTimeEnum perTimeEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ControctsEnum getControctsEnum() {
        return controctsEnum;
    }

    public void setControctsEnum(ControctsEnum controctsEnum) {
        this.controctsEnum = controctsEnum;
    }

    public PerTimeEnum getPerTimeEnum() {
        return perTimeEnum;
    }

    public void setPerTimeEnum(PerTimeEnum perTimeEnum) {
        this.perTimeEnum = perTimeEnum;
    }
}
