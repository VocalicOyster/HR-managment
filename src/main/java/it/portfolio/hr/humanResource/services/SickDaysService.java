package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.entities.Overtime;
import it.portfolio.hr.humanResource.entities.Permits;
import it.portfolio.hr.humanResource.entities.SickDays;
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


    public SickDaysResponseDTO create(SickDaysRequestDTO sickDaysRequestDTO, String companyName) {
        if(sickDaysValidator.isSickDaysValid(sickDaysRequestDTO, companyName)) {
            Employees employees = employeesRepository.findById(sickDaysRequestDTO.getEmployees_id(), companyName).orElse(null);
            if(employees == null) return null;
            SickDays sickDays = modelMapper.map(sickDaysRequestDTO, SickDays.class);
            sickDays.setEmployees(employees);
            sickDays.setCompanyName(companyName);
            sickDays.setDeleted(false);
            sickDaysRepository.saveAndFlush(sickDays);
            return modelMapper.map(sickDays, SickDaysResponseDTO.class);
        }
        return null;
    }

    public List<SickDaysResponseDTO> getAll(String companyName) {
        List<SickDays> sickDaysList = sickDaysRepository.findAll(companyName);
        List<SickDaysResponseDTO> responseDTOList = new ArrayList<>();

        for(SickDays sickDays: sickDaysList) {
            SickDaysResponseDTO sickDaysResponseDTO = modelMapper.map(sickDays, SickDaysResponseDTO.class);
            responseDTOList.add(sickDaysResponseDTO);
        }
        return responseDTOList;
    }

    public SickDaysResponseDTO getById(Long id, String companyName) {
        SickDays sickDays = sickDaysRepository.findById(id, companyName).orElse(null);
        if(sickDays == null) return null;
        return modelMapper.map(sickDays, SickDaysResponseDTO.class);
    }

    public List<SickDaysResponseDTO> getByName(String name, String companyName) {
        List<SickDays> sickDaysList = sickDaysRepository.findByName(name, companyName);
        List<SickDaysResponseDTO> sickDaysResponseDTOList = new ArrayList<>();
        for(SickDays sickDays: sickDaysList) {
            SickDaysResponseDTO sickDaysResponseDTO = modelMapper.map(sickDays, SickDaysResponseDTO.class);
            sickDaysResponseDTOList.add(sickDaysResponseDTO);
        }
        return sickDaysResponseDTOList;
    }

    public SickDaysResponseDTO update(Long id, SickDaysRequestDTO sickDaysRequestDTO, String companyName) {
        if(sickDaysValidator.isSickDaysValid(sickDaysRequestDTO, companyName)) {
            SickDays sickDays = sickDaysRepository.findById(id, companyName).orElse(null);
            if(sickDays == null) return null;
            sickDays.setDays(sickDaysRequestDTO.getDays());
            Employees employees = employeesRepository.findById(sickDaysRequestDTO.getEmployees_id(), companyName).orElse(null);
            if (employees == null) return null;
            sickDays.setEmployees(employees);
            sickDaysRepository.saveAndFlush(sickDays);
            return modelMapper.map(sickDays, SickDaysResponseDTO.class);
        }
        return null;
    }

    public SickDaysResponseDTO delete(Long id, String companyName) {
        SickDays sickDays = sickDaysRepository.findById(id, companyName).orElse(null);
        if(sickDays == null) return null;
        //sickDaysRepository.delete(sickDays);
        sickDays.setDeleted(true);
        sickDaysRepository.saveAndFlush(sickDays);
        return modelMapper.map(sickDays, SickDaysResponseDTO.class);
    }
}
