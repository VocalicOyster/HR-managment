package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.models.DTOs.request.UserRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public boolean isUserValid(UserRequestDTO userRequestDTO) {
        return isUserNotNull(userRequestDTO);
    }

    private boolean isUserNotNull(UserRequestDTO userRequestDTO) {
        return userRequestDTO.getEmail() != null &&
                userRequestDTO.getName() != null &&
                userRequestDTO.getPassword() != null &&
                userRequestDTO.getSurname() != null;
    }
}
