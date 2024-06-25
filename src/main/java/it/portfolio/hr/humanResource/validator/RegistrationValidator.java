package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.models.DTOs.request.RegistrationRequestDTO;
import it.portfolio.hr.humanResource.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegistrationValidator {

    @Autowired
    private UserRepository userDAO;

    public boolean isRegistratioValid(RegistrationRequestDTO registrationDTO) {
        return isRegistrationNotNull(registrationDTO) &&
                isPasswordsEquals(registrationDTO) &&
                isUsernameGreaterThanFive(registrationDTO) &&
                isPasswordGreaterThenEigth(registrationDTO) &&
                isEmailValid(registrationDTO);
    }

    private boolean isRegistrationNotNull(RegistrationRequestDTO registrationDTO) {
        return registrationDTO.getUsername() != null &&
                registrationDTO.getPassword() != null &&
                registrationDTO.getRepeatPassword() != null;
    }

    private boolean isPasswordsEquals(RegistrationRequestDTO registrationDTO) {
        return registrationDTO.getPassword().equals(registrationDTO.getRepeatPassword());
    }

    private boolean isUsernameGreaterThanFive(RegistrationRequestDTO registrationDTO) {
        return registrationDTO.getUsername().length() >= 5;
    }

    private boolean isPasswordGreaterThenEigth(RegistrationRequestDTO registrationDTO) {
        return registrationDTO.getPassword().length() >= 8;
    }

    private boolean isEmailValid(RegistrationRequestDTO registrationDTO) {
        return (Pattern.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}", registrationDTO.getEmail()));
    }

}
