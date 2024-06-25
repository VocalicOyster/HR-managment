package it.portfolio.hr.humanResource.models.DTOs.request;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacantionRequestDTO {

    private Long id;

    private String startDate;
    private String finishDate;
    private Integer duration; //INTESO IN GIORNI
    private Long employees_id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getEmployees_id() {
        return employees_id;
    }

    public void setEmployees_id(Long employees_id) {
        this.employees_id = employees_id;
    }
}
