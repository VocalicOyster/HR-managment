package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.Hiring;
import it.portfolio.hr.humanResource.models.DTOs.request.HiringRequestDTO;
import it.portfolio.hr.humanResource.repositories.DepartmentRepository;
import it.portfolio.hr.humanResource.repositories.HiringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class HiringValidator {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private HiringRepository hiringRepository;
    public boolean isHiringValid(HiringRequestDTO hiringRequestDTO, String companyName)  {
        return isHiringNotNUll(hiringRequestDTO) &&
                isIbanValid(hiringRequestDTO) &&
                isDateValid(hiringRequestDTO) &&
                DepartmentAlreadyExist(hiringRequestDTO, companyName);
    }

    private boolean isHiringNotNUll(HiringRequestDTO hiringRequestDTO) {
        return hiringRequestDTO.getHiringDate() != null &&
                hiringRequestDTO.getControctsEnum() != null &&
                hiringRequestDTO.getDepartment_id() != null &&
                hiringRequestDTO.getIBan() != null &&
                hiringRequestDTO.getPerTimeEnum() != null;

    }

    private boolean isIbanValid(HiringRequestDTO hiringRequestDTO) {
         return Pattern.matches("^[A-Z]{2}[0-9]{2}[A-Z]{1}([0-9]{22})?$", hiringRequestDTO.getIBan());
    }

    private boolean isDateValid(HiringRequestDTO hiringRequestDTO)  {
        return Pattern.matches("\\d\\d+/+\\d\\d+/+\\d\\d\\d\\d", hiringRequestDTO.getHiringDate());
    }

    private boolean DepartmentAlreadyExist(HiringRequestDTO hiringRequestDTO, String companyName) {
        List<Hiring> hiringList = hiringRepository.findAll(companyName);
        for(Hiring hiring : hiringList) {
            if(Objects.equals(hiringRequestDTO.getDepartment_id(), hiring.getDepartment().getId())) {
                return false;
            }
        }

        return true;
    }
}
