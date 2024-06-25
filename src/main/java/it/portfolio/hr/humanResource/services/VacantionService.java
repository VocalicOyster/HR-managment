package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.entities.Vacations;
import it.portfolio.hr.humanResource.models.DTOs.request.VacantionRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.VacantionResponseDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import it.portfolio.hr.humanResource.repositories.VacationsRepository;
import it.portfolio.hr.humanResource.validator.VacantionValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacantionService {
    @Autowired
    private VacantionValidator vacantionValidator;
    @Autowired
    private VacationsRepository vacationsRepository;
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private ModelMapper modelMapper;


    public VacantionResponseDTO createVacation(VacantionRequestDTO vacantionRequestDTO, String companyName) {
        if(vacantionValidator.isVacantionValid(vacantionRequestDTO, companyName)) {
            Vacations vacations = modelMapper.map(vacantionRequestDTO, Vacations.class);
            Employees employees = employeesRepository.findById(vacantionRequestDTO.getEmployees_id()).orElse(null);
            if(employees == null) {
                return null;
            }
            vacations.setEmployees(employees);
            vacations.setCompanyName(companyName);
            vacations.setDeleted(false);
            vacationsRepository.saveAndFlush(vacations);
            return modelMapper.map(vacations, VacantionResponseDTO.class);
        }
        return null;
    }


    public List<VacantionResponseDTO> getAll(String companyName) {
        List<Vacations> vacationsList = vacationsRepository.findAll(companyName);
        List<VacantionResponseDTO> responseDTOList = new ArrayList<>();
        for(Vacations vacation : vacationsList) {
            VacantionResponseDTO responseDTO = modelMapper.map(vacation, VacantionResponseDTO.class);
            responseDTO.setEmployees(vacation.getEmployees());
        }
        return responseDTOList;
    }

    public VacantionResponseDTO getById(Long id, String companyName) {
        Vacations vacations = vacationsRepository.findById(id, companyName).orElse(null);
        if(vacations == null) {
            return null;
        }
        VacantionResponseDTO responseDTO = modelMapper.map(vacations, VacantionResponseDTO.class);
        responseDTO.setEmployees(vacations.getEmployees());
        return responseDTO;
    }

    public VacantionResponseDTO updateById(VacantionRequestDTO vacantionRequestDTO, Long id, String companyName) {
        if(vacantionValidator.isVacantionValid(vacantionRequestDTO, companyName)) {
            Vacations vacations = vacationsRepository.findById(id, companyName).orElse(null);
            if(vacations == null) {
                return null;
            }
            vacations.setDays(vacantionRequestDTO.getDuration());
            vacations.setFinishDate(vacations.getFinishDate());
            Employees employees = employeesRepository.findById(vacantionRequestDTO.getEmployees_id(), companyName).orElse(null);
            if (employees == null) {
                return null;
            }
            vacations.setEmployees(employees);
            vacations.setStartDate(vacations.getStartDate());

            return modelMapper.map(vacations, VacantionResponseDTO.class);

        }
        return null;
    }

    public VacantionResponseDTO deleteById(Long id, String companyName) {
        Vacations vacations = vacationsRepository.findById(id, companyName).orElse(null);
        if(vacations == null) {
            return null;
        }
        //vacantionsRepository.deleteById(id);
        vacations.setDeleted(true);
        vacationsRepository.saveAndFlush(vacations);
        VacantionResponseDTO vacantionResponseDTO = modelMapper.map(vacations, VacantionResponseDTO.class);
        vacantionResponseDTO.setEmployees(vacations.getEmployees());
        return vacantionResponseDTO;
    }
}
