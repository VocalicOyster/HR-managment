package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Department;
import it.portfolio.hr.humanResource.exceptions.department.DepartmentException;
import it.portfolio.hr.humanResource.exceptions.department.DepartmentExistException;
import it.portfolio.hr.humanResource.models.DTOs.request.DepartmentRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.DepartmentResponseDTO;
import it.portfolio.hr.humanResource.repositories.DepartmentRepository;
import it.portfolio.hr.humanResource.validator.DepartmentValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentValidator departmentValidator;

    @Autowired
    private ModelMapper modelMapper;


    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request, String companyName) {
        if (departmentValidator.isDepartmpentValid(request, companyName)) {
            Department department = modelMapper.map(request, Department.class);
            department.setCompanyName(companyName);
            department.setDeleted(false);
            departmentRepository.saveAndFlush(department);
            return modelMapper.map(department, DepartmentResponseDTO.class);
        }
        return null;
    }

    public List<DepartmentResponseDTO> getAll(String companyName)  {
        List<Department> departmentList = departmentRepository.findAll(companyName);
        List<DepartmentResponseDTO> departmentResponseDTO = new ArrayList<>();
        for (Department department : departmentList) {
            DepartmentResponseDTO responseDTO = modelMapper.map(department, DepartmentResponseDTO.class);
            departmentResponseDTO.add(responseDTO);
        }

        return departmentResponseDTO;
    }

    public DepartmentResponseDTO getById(Long id, String companyName) {
        Department department = departmentRepository.findById(id, companyName).orElse(null);
        if (department == null) {
           return  null;
        }
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    public DepartmentResponseDTO updateById(Long id, DepartmentRequestDTO request, String companyName){
        Department department = departmentRepository.findById(id, companyName).orElse(null);

        if (department == null) {
           return null;
        }

        department.setName(request.getName());
        department.setDescription(request.getDescription());
        departmentRepository.saveAndFlush(department);
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    public DepartmentResponseDTO deleteById(Long id, String companyName) {
        Department department = departmentRepository.findById(id, companyName).orElse(null);

        if (department == null) {
           return null;
        }

        //departmentRepository.deleteById(id);
        department.setDeleted(true);
        departmentRepository.saveAndFlush(department);
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }
}
