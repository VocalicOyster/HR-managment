package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.models.DTOs.Response;
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
        RegistrationResponseDTO registrationResponseDTO = registerService.saveUser(registrationRequestDTO);
        if(registrationResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Error with data"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "User registered with success",
                        registrationResponseDTO
                )
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUserById(id, userRequestDTO);
        if(userResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Error with data"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "User registered with success",
                        userResponseDTO
                )
        );
    }
}
