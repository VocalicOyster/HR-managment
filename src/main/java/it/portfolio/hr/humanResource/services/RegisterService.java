package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.User;
import it.portfolio.hr.humanResource.exceptions.registration.RegistrationException;
import it.portfolio.hr.humanResource.models.DTOs.request.RegistrationRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.RegistrationResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.UserResponseDTO;
import it.portfolio.hr.humanResource.repositories.UserRepository;
import it.portfolio.hr.humanResource.validator.RegistrationValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userDAO;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RegistrationValidator registrationValidator;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegistrationResponseDTO saveUser(RegistrationRequestDTO registrationDTO) throws RegistrationException {
        if (registrationValidator.isRegistratioValid(registrationDTO)) {
            User user = new User();
            user.setEmail(registrationDTO.getEmail());
            user.setName(registrationDTO.getName());
            user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            user.setUsername(registrationDTO.getUsername());
            user.setSurname(registrationDTO.getSurname());
            user.setCompanyName(registrationDTO.getCompanyName());
            userDAO.saveAndFlush(user);
            return modelMapper.map(user, RegistrationResponseDTO.class);
        }
        throw new RegistrationException("The inserted registartion's info re not valid", 400);
    }


}
