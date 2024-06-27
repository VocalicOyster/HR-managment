package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.entities.Overtime;
import it.portfolio.hr.humanResource.entities.Permits;
import it.portfolio.hr.humanResource.entities.SickDays;
import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.sickdays.SickDaysException;
import it.portfolio.hr.humanResource.models.DTOs.request.SickDaysRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.OvertimeResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.SickDaysResponseDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import it.portfolio.hr.humanResource.repositories.SickDaysRepository;
import it.portfolio.hr.humanResource.validator.SickDaysValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class SickDaysService {

    @Autowired
    private SickDaysValidator sickDaysValidator;
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SickDaysRepository sickDaysRepository;


    public SickDaysResponseDTO create(SickDaysRequestDTO sickDaysRequestDTO, String companyName) throws EmployeesException, SickDaysException {
        if (sickDaysValidator.isSickDaysValid(sickDaysRequestDTO, companyName)) {
            Employees employees = employeesRepository.findById(sickDaysRequestDTO.getEmployees_id(), companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with id: " + sickDaysRequestDTO.getEmployees_id(), 400));

            SickDays sickDays = modelMapper.map(sickDaysRequestDTO, SickDays.class);
            sickDays.setEmployees(employees);
            sickDays.setCompanyName(companyName);
            sickDays.setDeleted(false);
            sickDaysRepository.saveAndFlush(sickDays);
            return modelMapper.map(sickDays, SickDaysResponseDTO.class);
        }
        throw new SickDaysException("The inserted sick day's info are not valid", 400);
    }

    public List<SickDaysResponseDTO> getAll(String companyName) {
        List<SickDays> sickDaysList = sickDaysRepository.findAll(companyName);
        List<SickDaysResponseDTO> responseDTOList = new ArrayList<>();

        for (SickDays sickDays : sickDaysList) {
            SickDaysResponseDTO sickDaysResponseDTO = modelMapper.map(sickDays, SickDaysResponseDTO.class);
            responseDTOList.add(sickDaysResponseDTO);
        }
        return responseDTOList;
    }

    public SickDaysResponseDTO getById(Long id, String companyName) throws SickDaysException {
        SickDays sickDays = sickDaysRepository.findById(id, companyName).orElseThrow(() -> new SickDaysException("No sick days retrieved with id: " + id, 400));
        return modelMapper.map(sickDays, SickDaysResponseDTO.class);
    }

    public List<SickDaysResponseDTO> getByName(String name, String companyName) {
        List<SickDays> sickDaysList = sickDaysRepository.findByName(name, companyName);
        List<SickDaysResponseDTO> sickDaysResponseDTOList = new ArrayList<>();
        for (SickDays sickDays : sickDaysList) {
            SickDaysResponseDTO sickDaysResponseDTO = modelMapper.map(sickDays, SickDaysResponseDTO.class);
            sickDaysResponseDTOList.add(sickDaysResponseDTO);
        }
        return sickDaysResponseDTOList;
    }

    public SickDaysResponseDTO update(Long id, SickDaysRequestDTO sickDaysRequestDTO, String companyName) throws SickDaysException, EmployeesException {
        if (sickDaysValidator.isSickDaysValid(sickDaysRequestDTO, companyName)) {
            SickDays sickDays = sickDaysRepository.findById(id, companyName).orElseThrow(() -> new SickDaysException("No sick days retrieved with id: " + id, 400));
            sickDays.setDays(sickDaysRequestDTO.getDays());
            Employees employees = employeesRepository.findById(sickDaysRequestDTO.getEmployees_id(), companyName).orElseThrow(() -> new EmployeesException("No Employees retrieved with id: " + sickDaysRequestDTO.getEmployees_id(), 400));
            sickDays.setEmployees(employees);
            sickDaysRepository.saveAndFlush(sickDays);
            return modelMapper.map(sickDays, SickDaysResponseDTO.class);
        }
        throw new SickDaysException("The inserted sick days' info are not valid", 400);
    }

    public SickDaysResponseDTO delete(Long id, String companyName) throws SickDaysException {
        SickDays sickDays = sickDaysRepository.findById(id, companyName).orElseThrow(() -> new SickDaysException("No sick days retrieved with id: " + id, 400));
        sickDays.setDeleted(true);
        sickDaysRepository.saveAndFlush(sickDays);
        return modelMapper.map(sickDays, SickDaysResponseDTO.class);
    }
}
