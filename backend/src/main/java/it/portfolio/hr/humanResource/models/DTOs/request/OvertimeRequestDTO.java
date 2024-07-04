package it.portfolio.hr.humanResource.models.DTOs.request;

import it.portfolio.hr.humanResource.entities.Employees;
import org.springframework.stereotype.Component;

@Component
public class OvertimeRequestDTO {
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
