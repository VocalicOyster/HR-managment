package it.portfolio.hr.humanResource.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Overtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employees employees;

    private int hours;
    private String companyName;

    private boolean isDeleted;

    public Overtime() {
    }

    public Overtime(Employees employees, int hours, String companyName, boolean isDeleted) {
        this.employees = employees;
        this.hours = hours;
        this.companyName = companyName;
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
