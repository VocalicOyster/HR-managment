package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.models.DTOs.request.PermitsRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PermitsValidator {

    public boolean isPermitsValid(PermitsRequestDTO permitsRequestDTO) {
        return isPermitsNotNull(permitsRequestDTO);
    }

    private boolean isPermitsNotNull(PermitsRequestDTO permitsRequestDTO) {
        return permitsRequestDTO.getHours() != null &&
                permitsRequestDTO.getEmployees_id() != null;
    }
}
