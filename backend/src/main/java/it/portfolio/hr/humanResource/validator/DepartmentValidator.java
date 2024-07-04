package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Department;
import it.portfolio.hr.humanResource.models.DTOs.request.DepartmentRequestDTO;
import it.portfolio.hr.humanResource.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepartmentValidator {
    @Autowired
    private DepartmentRepository departmentRepository;

    public boolean isDepartmpentValid(DepartmentRequestDTO departmentRequestDTO, String companyName){
        return isDepartmentNotNull(departmentRequestDTO) &&
                departmentExist(departmentRequestDTO, companyName);
    }

    private boolean isDepartmentNotNull(DepartmentRequestDTO departmentRequestDTO) {
        return departmentRequestDTO.getDescription() != null &&
                departmentRequestDTO.getName() != null;
    }

    private boolean departmentExist(DepartmentRequestDTO departmentRequestDTO, String companyName) {
        Optional<Department> optionalDepartment = departmentRepository.findByName(departmentRequestDTO.getName(), companyName);
        return optionalDepartment.isEmpty();
    }
}
