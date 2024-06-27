package it.portfolio.hr.humanResource.models.DTOs.response;

import it.portfolio.hr.humanResource.entities.Department;
import it.portfolio.hr.humanResource.models.Enums.PerTimeEnum;
import it.portfolio.hr.humanResource.models.Enums.ControctsEnum;
import it.portfolio.hr.humanResource.models.Enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringResponseDTO {

    private Long id;


    private String hiringDate;

    private ControctsEnum controctsEnum;

    private PerTimeEnum perTimeEnum;

    private RoleEnum role;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
