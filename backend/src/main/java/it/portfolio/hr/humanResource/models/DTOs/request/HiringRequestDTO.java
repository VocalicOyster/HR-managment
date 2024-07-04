package it.portfolio.hr.humanResource.models.DTOs.request;

import it.portfolio.hr.humanResource.models.Enums.PerTimeEnum;
import it.portfolio.hr.humanResource.models.Enums.ControctsEnum;
import it.portfolio.hr.humanResource.models.Enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HiringRequestDTO {

    private Long id;

    private Long department_id;

    private String hiringDate;

    private ControctsEnum controctsEnum;

    private PerTimeEnum perTimeEnum;

    private String iban;

    private RoleEnum role;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
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

    public String getIBan() {
        return iban;
    }

    public void setIBan(String IBan) {
        this.iban = IBan;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
