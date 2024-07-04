package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.User;
import it.portfolio.hr.humanResource.exceptions.user.UserException;
import it.portfolio.hr.humanResource.models.DTOs.request.UserRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.UserResponseDTO;
import it.portfolio.hr.humanResource.repositories.UserRepository;
import it.portfolio.hr.humanResource.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator validator;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserResponseDTO updateUserById(Long id, UserRequestDTO userDTO) throws UserException {
        User optionalUser = userRepository.findById(id).orElseThrow(() -> new UserException("No users found with id: " + id, 400));
        if (optionalUser != null) {
            optionalUser.setName(userDTO.getName());
            optionalUser.setSurname(userDTO.getSurname());
            optionalUser.setUsername(userDTO.getUsername());
            optionalUser.setEmail(userDTO.getEmail());
            optionalUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User userEdited = userRepository.saveAndFlush(optionalUser);
            return modelMapper.map(userEdited, UserResponseDTO.class);
        }
        return null;
    }

    public User getUserByUsername(String username) throws UserException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserException("No users found with username: " + username, 400));
    }

}
