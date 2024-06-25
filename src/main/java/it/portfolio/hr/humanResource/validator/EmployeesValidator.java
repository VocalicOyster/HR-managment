package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.exceptions.employee.InvalidEmployeeException;
import it.portfolio.hr.humanResource.models.DTOs.request.EmployeesRequestDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class EmployeesValidator {

    @Autowired
    private EmployeesRepository employeesRepository;

    public boolean isEmployeeValid(EmployeesRequestDTO employeesRequestDTO, String companyName) {
        return isEmployeeNotNull(employeesRequestDTO) &&
                employeeExist(employeesRequestDTO, companyName) &&
                employeeInsertedCorrect(employeesRequestDTO) &&
                isFiscalCodeValid(employeesRequestDTO);
    }

    private boolean isEmployeeNotNull(EmployeesRequestDTO employeesRequestDTO) {
        return employeesRequestDTO.getAddress() != null &&
                employeesRequestDTO.getFiscalCode() != null &&
                employeesRequestDTO.getName() != null &&
                employeesRequestDTO.getHiring_id() != null;
    }

    private boolean isFiscalCodeValid(EmployeesRequestDTO employeesRequestDTO) {
        return Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", employeesRequestDTO.getFiscalCode());
    }

    private boolean employeeExist(EmployeesRequestDTO employeesRequestDTO, String companyName) {
        Optional<Employees> optionalApplicant = employeesRepository.findByFiscalCode(employeesRequestDTO.getFiscalCode(), companyName);

        return optionalApplicant.isEmpty();
    }
    private boolean employeeInsertedCorrect(EmployeesRequestDTO employeesRequestDTO)  {
        try {
            employeesRequestDTO.getHiring_id();
            employeesRequestDTO.getName();
            employeesRequestDTO.getAddress();
            employeesRequestDTO.getFiscalCode();
            return true;
        }
        catch (Exception e) {
           return false;
        }
    }
}
