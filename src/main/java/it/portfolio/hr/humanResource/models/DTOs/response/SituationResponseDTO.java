package it.portfolio.hr.humanResource.models.DTOs.response;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.entities.Overtime;
import it.portfolio.hr.humanResource.entities.Permits;
import it.portfolio.hr.humanResource.entities.SickDays;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SituationResponseDTO {
    private Employees employees;
    private List<SickDaysResponseDTO> sickDays;
    private List<PermitsResponseDTO> permits;
    private List<OvertimeResponseDTO> overtime;

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public List<SickDaysResponseDTO> getSickDays() {
        return sickDays;
    }

    public void setSickDays(List<SickDaysResponseDTO> sickDays) {
        this.sickDays = sickDays;
    }

    public List<PermitsResponseDTO> getPermits() {
        return permits;
    }

    public void setPermits(List<PermitsResponseDTO> permits) {
        this.permits = permits;
    }

    public List<OvertimeResponseDTO> getOvertime() {
        return overtime;
    }

    public void setOvertime(List<OvertimeResponseDTO> overtime) {
        this.overtime = overtime;
    }
}
