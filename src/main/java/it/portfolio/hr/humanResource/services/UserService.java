package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.User;
import it.portfolio.hr.humanResource.models.DTOs.request.UserRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.UserResponseDTO;
import it.portfolio.hr.humanResource.repositories.UserRepository;
import it.portfolio.hr.humanResource.validator.UserValidator;
import jdk.jshell.spi.ExecutionControl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        if (validator.isUserValid(userRequestDTO)) {
            User entity = modelMapper.map(userRequestDTO, User.class);
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            User saved = userRepository.saveAndFlush(entity);
            return modelMapper.map(saved, UserResponseDTO.class);
        }
        return null;
    }

    public List<UserResponseDTO> getAllUsers() {
        List <User> users = userRepository.findAll();
        List<UserResponseDTO> usersDTOList = new ArrayList<>();
        for(User user : users){
            UserResponseDTO userDTO = modelMapper.map(user, UserResponseDTO.class);
            usersDTOList.add(userDTO);
        }
        return usersDTOList;
    }

    public UserResponseDTO getUserById(Long id)  {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return modelMapper.map(user, UserResponseDTO.class);
        } else {
            return null;
        }
    }


    public UserResponseDTO updateUserById(Long id, UserRequestDTO userDTO) {
        User optionalUser = userRepository.findById(id).orElse(null);
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

    public UserResponseDTO deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return null;
        }
        userRepository.deleteById(id);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User getUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()) {
            return user.get();
        }
        throw new BadCredentialsException("");
    }

}
