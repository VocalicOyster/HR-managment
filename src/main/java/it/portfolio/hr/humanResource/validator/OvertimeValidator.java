package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.models.DTOs.request.OvertimeRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.request.SickDaysRequestDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OvertimeValidator {
    @Autowired
    private EmployeesRepository employeesRepository;

    public boolean isOvertimeValid(OvertimeRequestDTO overtimeRequestDTO, String companyName) {
        return isOvertTimeNotNull(overtimeRequestDTO) &&
                isEmployeePresent(overtimeRequestDTO, companyName);
    }

    private boolean isOvertTimeNotNull(OvertimeRequestDTO overtimeRequestDTO) {
        return overtimeRequestDTO.getHours() != null &&
                overtimeRequestDTO.getEmployees_id() != null;
    }

    private boolean isEmployeePresent(OvertimeRequestDTO overtimeRequestDTO, String companyName) {
        Employees employees = employeesRepository.findById(overtimeRequestDTO.getEmployees_id(), companyName).orElse(null);
        return employees != null;
    }
}
