package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.entities.Overtime;
import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.overtime.OvertimeException;
import it.portfolio.hr.humanResource.models.DTOs.request.OvertimeRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.OvertimeResponseDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import it.portfolio.hr.humanResource.repositories.OvertimeRepository;
import it.portfolio.hr.humanResource.validator.OvertimeValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OvertimeService {

    @Autowired
    private OvertimeValidator overtimeValidator;
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OvertimeRepository overtimeRepository;


    public OvertimeResponseDTO create(OvertimeRequestDTO overtimeRequestDTO, String companyName) throws OvertimeException, EmployeesException {
        if(overtimeValidator.isOvertimeValid(overtimeRequestDTO, companyName)) {
            Employees employees = employeesRepository.findById(overtimeRequestDTO.getEmployees_id(), companyName).orElseThrow(() -> new EmployeesException("No employees retrieved woth id: " + overtimeRequestDTO.getEmployees_id(), 400));

            Overtime overtime = modelMapper.map(overtimeRequestDTO, Overtime.class);
            overtime.setEmployees(employees);
            overtime.setCompanyName(companyName);
            overtime.setDeleted(false);
            overtimeRepository.saveAndFlush(overtime);
            return modelMapper.map(overtime, OvertimeResponseDTO.class);
        }
        throw new OvertimeException("The inserted overtime's information are not valid", 400);
    }

    public List<OvertimeResponseDTO> getAll(String companyName) {
        List<Overtime> overtimeList = overtimeRepository.findAll(companyName);
        List<OvertimeResponseDTO> responseDTOList = new ArrayList<>();

        for(Overtime overtime: overtimeList) {
            OvertimeResponseDTO overtimeResponseDTO = modelMapper.map(overtime, OvertimeResponseDTO.class);
            responseDTOList.add(overtimeResponseDTO);
        }
        return responseDTOList;
    }

    public List<OvertimeResponseDTO> getAllByName(String name, String companyName) {
        List<Overtime> overtimeList = overtimeRepository.findByName(name, companyName);
        List<OvertimeResponseDTO> overtimeResponseDTOList = new ArrayList<>();
        for(Overtime overtime: overtimeList) {
            OvertimeResponseDTO overtimeResponseDTO = modelMapper.map(overtime, OvertimeResponseDTO.class);
            overtimeResponseDTOList.add(overtimeResponseDTO);
        }
        return overtimeResponseDTOList;
    }

    public OvertimeResponseDTO getById(Long id, String companyName) throws OvertimeException {
        Overtime overtime = overtimeRepository.findById(id, companyName).orElseThrow(() -> new OvertimeException("No overtime retrieved with id: " + id, 400));
        return modelMapper.map(overtime, OvertimeResponseDTO.class);
    }

    public OvertimeResponseDTO update(Long id, OvertimeRequestDTO overtimeRequestDTO, String companyName) throws OvertimeException, EmployeesException {
        if(overtimeValidator.isOvertimeValid(overtimeRequestDTO, companyName)) {
            Overtime overtime = overtimeRepository.findById(id, companyName).orElseThrow(() -> new OvertimeException("No overtime retrieved with id: " + id, 400));
            overtime.setHours(overtimeRequestDTO.getHours());
            Employees employees = employeesRepository.findById(overtimeRequestDTO.getEmployees_id(), companyName).orElseThrow(() -> new EmployeesException("No employees retrieved woth id: " + overtimeRequestDTO.getEmployees_id(), 400));
            overtime.setEmployees(employees);
            overtimeRepository.saveAndFlush(overtime);
            return modelMapper.map(overtime, OvertimeResponseDTO.class);
        }
        throw new OvertimeException("The inserted overtime's info are not valid", 400);
    }

    public OvertimeResponseDTO delete(Long id, String companyName) throws OvertimeException {
        Overtime overtime = overtimeRepository.findById(id, companyName).orElseThrow(() -> new OvertimeException("No overtime retrieved with id: " + id, 400));
        overtime.setDeleted(true);
        overtimeRepository.saveAndFlush(overtime);
        return modelMapper.map(overtime, OvertimeResponseDTO.class);
    }
}
