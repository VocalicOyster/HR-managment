package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.entities.User;
import it.portfolio.hr.humanResource.exceptions.user.UserException;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.request.LoginRequest;
import it.portfolio.hr.humanResource.models.DTOs.response.LoginResponse;
import it.portfolio.hr.humanResource.models.UserDetailsImpl;
import it.portfolio.hr.humanResource.services.UserService;
import it.portfolio.hr.humanResource.utilities.TokenUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private TokenUtilities jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin("http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws BadCredentialsException {
        try {
            User user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
            if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
                final String jwt = jwtUtil.createToken(new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getCompanyName()));
                return ResponseEntity.ok().body(new LoginResponse(jwt, 200, "Token created successfully!"));
            }
            return ResponseEntity.status(400).body(new LoginResponse(400, "The inserted password or username are invalid. Unable to create token"));
        } catch (UserException e) {
            return ResponseEntity.status(400).body(new LoginResponse(400, e.getMessage()));
        }
    }
}
