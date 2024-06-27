package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Department;
import it.portfolio.hr.humanResource.exceptions.department.DepartmentException;
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


    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request, String companyName) throws DepartmentException {
        if (departmentValidator.isDepartmpentValid(request, companyName)) {
            Department department = modelMapper.map(request, Department.class);
            department.setCompanyName(companyName);
            department.setDeleted(false);
            departmentRepository.saveAndFlush(department);
            return modelMapper.map(department, DepartmentResponseDTO.class);
        }
        throw new DepartmentException("The inserted department informations are not valid", 400);
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

    public DepartmentResponseDTO getById(Long id, String companyName) throws DepartmentException {
        Department department = departmentRepository.findById(id, companyName).orElseThrow(() -> new DepartmentException("No department retireved with id: " + id, 400));
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    public DepartmentResponseDTO updateById(Long id, DepartmentRequestDTO request, String companyName) throws DepartmentException {
        Department department = departmentRepository.findById(id, companyName).orElseThrow(() -> new DepartmentException("No department retireved with id: " + id, 400));

        department.setName(request.getName());
        department.setDescription(request.getDescription());
        departmentRepository.saveAndFlush(department);
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    public DepartmentResponseDTO deleteById(Long id, String companyName) throws DepartmentException {
        Department department = departmentRepository.findById(id, companyName).orElseThrow(() -> new DepartmentException("No department retireved with id: " + id, 400));

        //departmentRepository.deleteById(id);
        department.setDeleted(true);
        departmentRepository.saveAndFlush(department);
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }
}
