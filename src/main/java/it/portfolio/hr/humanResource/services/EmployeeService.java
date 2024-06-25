package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.*;
import it.portfolio.hr.humanResource.exceptions.employee.HiringNotFoundException;
import it.portfolio.hr.humanResource.exceptions.employee.InvalidEmployeeException;
import it.portfolio.hr.humanResource.models.DTOs.request.EmployeesRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.*;
import it.portfolio.hr.humanResource.repositories.*;
import it.portfolio.hr.humanResource.validator.EmployeesValidator;
import org.hibernate.sql.ast.tree.expression.Over;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private EmployeesValidator employeesValidator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HiringRepository hiringRepository;
    @Autowired
    private PermitsRepository permitsRepository;

    @Autowired
    private OvertimeRepository overtimeRepository;
    @Autowired
    private SickDaysRepository sickDaysRepository;

    public EmployeesResponseDTO createEmployee(EmployeesRequestDTO employeesRequestDTO, String companyName){
        if(employeesValidator.isEmployeeValid(employeesRequestDTO, companyName)) {
            Employees employees = modelMapper.map(employeesRequestDTO, Employees.class);
            Hiring hiring = hiringRepository.findById(employeesRequestDTO.getHiring_id()).orElse(null);
            if(hiring == null) {
                return null;
            }
            employees.setPaymentDate(LocalDate.now().toString());
            employees.setCompanyName(companyName);
            employees.setDeleted(false);
            employeesRepository.saveAndFlush(employees);
            return modelMapper.map(employees, EmployeesResponseDTO.class);
        }
        return null;
    }

    public List<EmployeesResponseDTO> getAllEmployees(String companyName) {
        List<Employees> employees = employeesRepository.findAll(companyName);
        List<EmployeesResponseDTO> responseDTO = new ArrayList<>();
        for(Employees employees1 : employees) {
            EmployeesResponseDTO response = modelMapper.map(employees1, EmployeesResponseDTO.class);
            responseDTO.add(response);
        }
        return responseDTO;
    }

    public EmployeesResponseDTO getById(Long id, String companyName) {
        Employees employees = employeesRepository.findById(id, companyName).orElse(null);
        if(employees != null) {
            return modelMapper.map(employees, EmployeesResponseDTO.class);
        }
        return null;
    }

    public EmployeesResponseDTO getByFiscalCode(String fiscalCode, String companyName) {
        Employees employees = employeesRepository.findByFiscalCode(fiscalCode, companyName).orElse(null);
        System.out.println(employees);
        if(employees != null) {
            return modelMapper.map(employees, EmployeesResponseDTO.class);
        }
        return null;
    }

    public EmployeesResponseDTO updateById(Long id, EmployeesRequestDTO employeesRequestDTO, String companyName) {
        if(employeesValidator.isEmployeeValid(employeesRequestDTO, companyName)) {
            Employees employees = employeesRepository.findById(id).orElse(null);

            if (employees == null) {
                return null;
            }

            employees.setAddress(employeesRequestDTO.getAddress());
            employees.setName(employeesRequestDTO.getName());
            employees.setFiscalCode(employeesRequestDTO.getFiscalCode());
            Hiring hiring = hiringRepository.findById(employeesRequestDTO.getHiring_id()).orElse(null);
            if (hiring == null) {
                return null;
            }
            employeesRepository.saveAndFlush(employees);
            return modelMapper.map(employees, EmployeesResponseDTO.class);
        }
        return null;
    }

    public EmployeesResponseDTO deleteById(Long id, String companyName) {
        Employees employees = employeesRepository.findById(id, companyName).orElse(null);
        if(employees == null) {
            return null;
        }

        //employeesRepository.deleteById(id);
        employees.setDeleted(true);
        employeesRepository.saveAndFlush(employees);
        return modelMapper.map(employees, EmployeesResponseDTO.class);
    }

    public SituationResponseDTO getSituationByName(String name, String companyName) {

        Employees employees = employeesRepository.findByName(name, companyName).orElse(null);
        if(employees == null) return null;
        List<SickDays> sickDays = sickDaysRepository.findByName(name, companyName);
        List<Permits> permits = permitsRepository.findByName(name, companyName);
        List<Overtime> overtime = overtimeRepository.findByName(name, companyName);
        List<SickDaysResponseDTO> sickDaysResponseDTOList = new ArrayList<>();
        List<PermitsResponseDTO> permitsResponseDTOList = new ArrayList<>();
        List<OvertimeResponseDTO> overtimeResponseDTOList = new ArrayList<>();

        for(SickDays days: sickDays) {
            SickDaysResponseDTO sickDaysResponseDTO = modelMapper.map(days, SickDaysResponseDTO.class);
            sickDaysResponseDTOList.add(sickDaysResponseDTO);
        }
        for(Permits perm: permits) {
            PermitsResponseDTO permitsResponseDTO = modelMapper.map(perm, PermitsResponseDTO.class);
            permitsResponseDTOList.add(permitsResponseDTO);
        }
        for(Overtime over: overtime) {
            OvertimeResponseDTO overtimeResponseDTO = modelMapper.map(over, OvertimeResponseDTO.class);
            overtimeResponseDTOList.add(overtimeResponseDTO);
        }


        SituationResponseDTO situationResponseDTO = new SituationResponseDTO();
        situationResponseDTO.setEmployees(employees);
        situationResponseDTO.setSickDays(sickDaysResponseDTOList);
        situationResponseDTO.setPermits(permitsResponseDTOList);
        situationResponseDTO.setOvertime(overtimeResponseDTOList);

        return situationResponseDTO;
    }
}
