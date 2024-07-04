package it.portfolio.hr.humanResource.services;


import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.entities.Overtime;
import it.portfolio.hr.humanResource.entities.Permits;
import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.permits.PermitsException;
import it.portfolio.hr.humanResource.models.DTOs.request.PermitsRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.OvertimeResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.PermitsResponseDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import it.portfolio.hr.humanResource.repositories.PermitsRepository;
import it.portfolio.hr.humanResource.validator.PermitsValidator;
import jakarta.persistence.PersistenceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermitsService {

    @Autowired
    private PermitsValidator permitsValidator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PermitsRepository permitsRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    public PermitsResponseDTO create(PermitsRequestDTO permitsRequestDTO, String companyName) throws PermitsException, EmployeesException {
        if(permitsValidator.isPermitsValid(permitsRequestDTO) ) {
            Permits permits = modelMapper.map(permitsRequestDTO, Permits.class);
            permits.setCompanyName(companyName);
            permits.setDeleted(false);
            Employees employees = employeesRepository.findById(permitsRequestDTO.getEmployees_id(), companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with id: " + permitsRequestDTO.getEmployees_id() , 400));
            permits.setEmployees(employees);
            permitsRepository.saveAndFlush(permits);
            return modelMapper.map(permits, PermitsResponseDTO.class);
        }
        throw new PermitsException("The inserted permits' info are not valid", 400);
    }

    public List<PermitsResponseDTO> getAll(String companyName) {
        List<Permits> permitsList = permitsRepository.findAll(companyName);
        List<PermitsResponseDTO> permitsResponseList = new ArrayList<>();


        for(Permits permits : permitsList) {
            PermitsResponseDTO permitsResponseDTO = modelMapper.map(permits, PermitsResponseDTO.class);
            permitsResponseList.add(permitsResponseDTO);
        }

        return permitsResponseList;
    }

    public PermitsResponseDTO getById(Long id, String companyName) throws PermitsException {
        Permits permits = permitsRepository.findById(id, companyName).orElseThrow(() -> new PermitsException("No permits retrieved with id: " + id, 400));
        return modelMapper.map(permits, PermitsResponseDTO.class);
    }

    public List<PermitsResponseDTO> getByName(String name, String companyName) {
        List<Permits> permitsList = permitsRepository.findByName(name, companyName);
        List<PermitsResponseDTO> permitsResponseDTOList = new ArrayList<>();
        for(Permits permits: permitsList) {
            PermitsResponseDTO permitsResponseDTO = modelMapper.map(permits, PermitsResponseDTO.class);
            permitsResponseDTOList.add(permitsResponseDTO);
        }
        return permitsResponseDTOList;
    }

    public PermitsResponseDTO update(Long id, PermitsRequestDTO permitsRequestDTO, String companyName) throws PermitsException, EmployeesException {
        Permits permits = permitsRepository.findById(id, companyName).orElseThrow(() -> new PermitsException("No permits retrieved with id: " + id, 400));
        permits.setHours(permitsRequestDTO.getHours());
        Employees employees = employeesRepository.findById(permitsRequestDTO.getEmployees_id(), companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with id: " + permitsRequestDTO.getEmployees_id() , 400));
        permits.setEmployees(employees);
        permitsRepository.saveAndFlush(permits);
        return modelMapper.map(permits, PermitsResponseDTO.class);
    }

    public PermitsResponseDTO delete(Long id, String companyName) throws PermitsException {
        Permits permits = permitsRepository.findById(id, companyName).orElseThrow(() -> new PermitsException("No permits retrieved with id: " + id, 400));
        permits.setDeleted(true);
        permitsRepository.saveAndFlush(permits);
        PermitsResponseDTO permitsResponseDTO = modelMapper.map(permits, PermitsResponseDTO.class);
        return modelMapper.map(permits, PermitsResponseDTO.class);
    }

}
