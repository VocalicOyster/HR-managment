package it.portfolio.hr.humanResource.models.DTOs.request;

public class PermitsRequestDTO {

    private Long employees_id;
    private Integer hours;


    public Long getEmployees_id() {
        return employees_id;
    }

    public void setEmployees_id(Long employees_id) {
        this.employees_id = employees_id;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
