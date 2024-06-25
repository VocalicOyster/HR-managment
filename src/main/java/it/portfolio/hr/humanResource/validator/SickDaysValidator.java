package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.models.DTOs.request.SickDaysRequestDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SickDaysValidator {

    @Autowired
    private EmployeesRepository employeesRepository;

    public boolean isSickDaysValid(SickDaysRequestDTO sickDaysRequestDTO, String companyName) {
        return isSickDaysNotNull(sickDaysRequestDTO) &&
                isEmployeePresent(sickDaysRequestDTO, companyName);
    }

    private boolean isSickDaysNotNull(SickDaysRequestDTO sickDaysRequestDTO) {
        return sickDaysRequestDTO.getDays() != null &&
                sickDaysRequestDTO.getEmployees_id() != null;
    }

    private boolean isEmployeePresent(SickDaysRequestDTO sickDaysRequestDTO, String companyName) {
        Employees employees = employeesRepository.findById(sickDaysRequestDTO.getEmployees_id(), companyName).orElse(null);
        return employees != null;
    }
}
