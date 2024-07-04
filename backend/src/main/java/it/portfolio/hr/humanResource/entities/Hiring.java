package it.portfolio.hr.humanResource.entities;

import it.portfolio.hr.humanResource.models.Enums.ControctsEnum;
import it.portfolio.hr.humanResource.models.Enums.PerTimeEnum;
import it.portfolio.hr.humanResource.models.Enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Hiring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float hourlyPay;
    @OneToOne
    private Department department;

    @Column(nullable = false)
    private String hiringDate;

    @Column(nullable = false)
    private ControctsEnum controctsEnum;

    @Column(nullable = false)
    private PerTimeEnum perTimeEnum;

    @Column(nullable = false)
    private String IBan;

    @Column(nullable = false)
    private RoleEnum role;

    @OneToOne
    private Employees employees;


    public Hiring(float hourlyPay, Department department, String hiringDate, ControctsEnum controctsEnum, PerTimeEnum perTimeEnum, String IBan, RoleEnum role, Employees employees, String companyName, boolean isDeleted) {
        this.hourlyPay = hourlyPay;
        this.department = department;
        this.hiringDate = hiringDate;
        this.controctsEnum = controctsEnum;
        this.perTimeEnum = perTimeEnum;
        this.IBan = IBan;
        this.role = role;
        this.employees = employees;
        this.companyName = companyName;
        this.isDeleted = isDeleted;
    }

    private String companyName;

    private boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public float getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(float hourlyPay) {
        this.hourlyPay = hourlyPay;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        return IBan;
    }

    public void setIBan(String IBan) {
        this.IBan = IBan;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
