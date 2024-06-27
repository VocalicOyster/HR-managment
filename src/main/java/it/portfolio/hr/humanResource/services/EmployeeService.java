package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.*;
import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.hirirng.HiringException;
import it.portfolio.hr.humanResource.models.DTOs.request.EmployeesRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.*;
import it.portfolio.hr.humanResource.repositories.*;
import it.portfolio.hr.humanResource.validator.EmployeesValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public EmployeesResponseDTO createEmployee(EmployeesRequestDTO employeesRequestDTO, String companyName) throws HiringException, EmployeesException {
        if (employeesValidator.isEmployeeValid(employeesRequestDTO, companyName)) {
            Employees employees = modelMapper.map(employeesRequestDTO, Employees.class);
            Hiring hiring = hiringRepository.findById(employeesRequestDTO.getHiring_id()).orElseThrow(() -> new HiringException("No Hiring retrieved from database", 400));
            employees.setPaymentDate(LocalDate.now().toString());
            employees.setCompanyName(companyName);
            employees.setDeleted(false);
            employeesRepository.saveAndFlush(employees);
            return modelMapper.map(employees, EmployeesResponseDTO.class);
        }
        throw new EmployeesException("The inserted employee's information's are not valid", 400);
    }

    public List<EmployeesResponseDTO> getAllEmployees(String companyName) {
        List<Employees> employees = employeesRepository.findAll(companyName);
        List<EmployeesResponseDTO> responseDTO = new ArrayList<>();
        for (Employees employees1 : employees) {
            EmployeesResponseDTO response = modelMapper.map(employees1, EmployeesResponseDTO.class);
            responseDTO.add(response);
        }
        return responseDTO;
    }

    public EmployeesResponseDTO getById(Long id, String companyName) throws EmployeesException {
        Employees employees = employeesRepository.findById(id, companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with id: " + id, 400));
        return modelMapper.map(employees, EmployeesResponseDTO.class);
    }

    public EmployeesResponseDTO getByFiscalCode(String fiscalCode, String companyName) throws EmployeesException {
        Employees employees = employeesRepository.findByFiscalCode(fiscalCode, companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with Fiscal Code: " + fiscalCode, 400));

            return modelMapper.map(employees, EmployeesResponseDTO.class);
    }

    public EmployeesResponseDTO updateById(Long id, EmployeesRequestDTO employeesRequestDTO, String companyName) throws EmployeesException, HiringException {
        if (employeesValidator.isEmployeeValid(employeesRequestDTO, companyName)) {
            Employees employees = employeesRepository.findById(id).orElseThrow(() -> new EmployeesException("The inserted employee's information's are not valid", 400));

            employees.setAddress(employeesRequestDTO.getAddress());
            employees.setName(employeesRequestDTO.getName());
            employees.setFiscalCode(employeesRequestDTO.getFiscalCode());
            Hiring hiring = hiringRepository.findById(employeesRequestDTO.getHiring_id()).orElseThrow(() -> new HiringException("No Hiring retrieved from database", 400));

            employeesRepository.saveAndFlush(employees);
            return modelMapper.map(employees, EmployeesResponseDTO.class);
        }
        throw new EmployeesException("The inserted employee's information's aree not valid", 400);
    }

    public EmployeesResponseDTO deleteById(Long id, String companyName) throws EmployeesException {
        Employees employees = employeesRepository.findById(id, companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with id: " + id, 400));
        employees.setDeleted(true);
        employeesRepository.saveAndFlush(employees);
        return modelMapper.map(employees, EmployeesResponseDTO.class);
    }

    public SituationResponseDTO getSituationByName(String name, String companyName) throws EmployeesException {

        Employees employees = employeesRepository.findByName(name, companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with name : " + name, 400));
        List<SickDays> sickDays = sickDaysRepository.findByName(name, companyName);
        List<Permits> permits = permitsRepository.findByName(name, companyName);
        List<Overtime> overtime = overtimeRepository.findByName(name, companyName);
        List<SickDaysResponseDTO> sickDaysResponseDTOList = new ArrayList<>();
        List<PermitsResponseDTO> permitsResponseDTOList = new ArrayList<>();
        List<OvertimeResponseDTO> overtimeResponseDTOList = new ArrayList<>();

        for (SickDays days : sickDays) {
            SickDaysResponseDTO sickDaysResponseDTO = modelMapper.map(days, SickDaysResponseDTO.class);
            sickDaysResponseDTOList.add(sickDaysResponseDTO);
        }
        for (Permits perm : permits) {
            PermitsResponseDTO permitsResponseDTO = modelMapper.map(perm, PermitsResponseDTO.class);
            permitsResponseDTOList.add(permitsResponseDTO);
        }
        for (Overtime over : overtime) {
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
