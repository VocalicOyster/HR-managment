package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.user.UserException;
import it.portfolio.hr.humanResource.exceptions.registration.RegistrationException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.request.RegistrationRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.request.UserRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.RegistrationResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.UserResponseDTO;
import it.portfolio.hr.humanResource.services.RegisterService;
import it.portfolio.hr.humanResource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Response> registerNewUser(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        try {
            RegistrationResponseDTO registrationResponseDTO = registerService.saveUser(registrationRequestDTO);
            return ResponseEntity.ok().body(new ResponseValid(200, "User registered with success", registrationResponseDTO));
        } catch (RegistrationException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "Error with data"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.updateUserById(id, userRequestDTO);
            return ResponseEntity.ok().body(new ResponseValid(200, "User registered with success", userResponseDTO));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
