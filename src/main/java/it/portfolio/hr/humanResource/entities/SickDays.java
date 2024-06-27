package it.portfolio.hr.humanResource.entities;

import jakarta.persistence.*;

@Entity
@Table
public class SickDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Employees employees;

    private int days;

    private String companyName;

    private boolean isDeleted;

    public SickDays() {
    }

    public SickDays(Employees employees, int days, String companyName, boolean isDeleted) {
        this.employees = employees;
        this.days = days;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
