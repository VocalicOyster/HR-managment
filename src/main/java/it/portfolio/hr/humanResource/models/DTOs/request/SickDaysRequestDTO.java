package it.portfolio.hr.humanResource.models.DTOs.request;

import it.portfolio.hr.humanResource.entities.Employees;
import org.springframework.stereotype.Component;

@Component
public class SickDaysRequestDTO {

    private Long employees_id;
    private Integer days;

    public Long getEmployees_id() {
        return employees_id;
    }

    public void setEmployees_id(Long employees_id) {
        this.employees_id = employees_id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
