package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Vacations;
import it.portfolio.hr.humanResource.models.DTOs.request.VacantionRequestDTO;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import it.portfolio.hr.humanResource.repositories.VacationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class VacantionValidator {

    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private VacationsRepository vacantionsRepository;
    public boolean isVacantionValid(VacantionRequestDTO vacantionRequestDTO, String companyName) {
        return isVacantionNotNull(vacantionRequestDTO) &&
                isDateValid(vacantionRequestDTO) &&
                vacantionExist(vacantionRequestDTO, companyName);
    }

    private boolean isVacantionNotNull(VacantionRequestDTO vacantionRequestDTO) {
        if (vacantionRequestDTO.getDuration() == null) {
            return false;
        }
        return vacantionRequestDTO.getFinishDate() != null &&
                vacantionRequestDTO.getStartDate() != null &&
                vacantionRequestDTO.getEmployees_id() != null;
    }

    private boolean isDateValid(VacantionRequestDTO vacantionRequestDTO) {
        return Pattern.matches("\\d\\d+/+\\d\\d+/+\\d\\d\\d\\d", vacantionRequestDTO.getStartDate()) &&
                Pattern.matches("\\d\\d+/+\\d\\d+/+\\d\\d\\d\\d", vacantionRequestDTO.getFinishDate());
    }

    private boolean vacantionExist(VacantionRequestDTO vacantionRequestDTO, String companyName) {
        List<Vacations> vacationsList = vacantionsRepository.findAll(companyName);
        for(Vacations vacations : vacationsList) {
            if(Objects.equals(vacations.getEmployees().getId(), vacantionRequestDTO.getEmployees_id())) {
                return false;
            }
        }
        return true;
    }

}
