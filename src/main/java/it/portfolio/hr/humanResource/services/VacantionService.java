package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.entities.Vacations;
import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.vacation.VacationException;
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


    public VacantionResponseDTO createVacation(VacantionRequestDTO vacantionRequestDTO, String companyName) throws EmployeesException, VacationException {
        if (vacantionValidator.isVacantionValid(vacantionRequestDTO, companyName)) {
            Vacations vacations = modelMapper.map(vacantionRequestDTO, Vacations.class);
            Employees employees = employeesRepository.findById(vacantionRequestDTO.getEmployees_id()).orElseThrow(() -> new EmployeesException("No employees retrieved with id " + vacantionRequestDTO.getEmployees_id(), 400));
            vacations.setEmployees(employees);
            vacations.setCompanyName(companyName);
            vacations.setDeleted(false);
            vacationsRepository.saveAndFlush(vacations);
            return modelMapper.map(vacations, VacantionResponseDTO.class);
        }
        throw new VacationException("The inserted vacation's info are not valid", 400);
    }


    public List<VacantionResponseDTO> getAll(String companyName) {
        List<Vacations> vacationsList = vacationsRepository.findAll(companyName);
        List<VacantionResponseDTO> responseDTOList = new ArrayList<>();
        for (Vacations vacation : vacationsList) {
            VacantionResponseDTO responseDTO = modelMapper.map(vacation, VacantionResponseDTO.class);
            responseDTO.setEmployees(vacation.getEmployees());
        }
        return responseDTOList;
    }

    public VacantionResponseDTO getById(Long id, String companyName) throws VacationException {
        Vacations vacations = vacationsRepository.findById(id, companyName).orElseThrow(() -> new VacationException("No vacations retrieved with id: " + id, 400));
        VacantionResponseDTO responseDTO = modelMapper.map(vacations, VacantionResponseDTO.class);
        responseDTO.setEmployees(vacations.getEmployees());
        return responseDTO;
    }

    public VacantionResponseDTO updateById(VacantionRequestDTO vacantionRequestDTO, Long id, String companyName) throws VacationException, EmployeesException {
        if (vacantionValidator.isVacantionValid(vacantionRequestDTO, companyName)) {
            Vacations vacations = vacationsRepository.findById(id, companyName).orElseThrow(() -> new VacationException("No vacations retrieved with id: " + id, 400));
            vacations.setDays(vacantionRequestDTO.getDuration());
            vacations.setFinishDate(vacations.getFinishDate());
            Employees employees = employeesRepository.findById(vacantionRequestDTO.getEmployees_id(), companyName).orElseThrow(() -> new EmployeesException("No employees retrieved with id " + vacantionRequestDTO.getEmployees_id(), 400));
            vacations.setEmployees(employees);
            vacations.setStartDate(vacations.getStartDate());
            return modelMapper.map(vacations, VacantionResponseDTO.class);
        }
        throw new VacationException("The inserted vacation's info are not valid", 400);
    }

    public VacantionResponseDTO deleteById(Long id, String companyName) throws VacationException {
        Vacations vacations = vacationsRepository.findById(id, companyName).orElseThrow(() -> new VacationException("No vacations retrieved with id: " + id, 400));
        vacations.setDeleted(true);
        vacationsRepository.saveAndFlush(vacations);
        VacantionResponseDTO vacantionResponseDTO = modelMapper.map(vacations, VacantionResponseDTO.class);
        vacantionResponseDTO.setEmployees(vacations.getEmployees());
        return vacantionResponseDTO;
    }
}
