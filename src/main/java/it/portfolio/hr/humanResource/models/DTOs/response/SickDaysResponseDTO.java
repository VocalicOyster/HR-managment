package it.portfolio.hr.humanResource.models.DTOs.response;

import it.portfolio.hr.humanResource.entities.Employees;
import org.springframework.stereotype.Component;

@Component
public class SickDaysResponseDTO {

    private Long id;
    private int days;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
