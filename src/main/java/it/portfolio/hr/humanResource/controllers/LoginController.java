package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.entities.User;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws BadCredentialsException {
        User user = userDetailsService.getUserByUsername(authenticationRequest.getUsername());
        if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            final String jwt = jwtUtil.createToken(new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getCompanyName()));
            return ResponseEntity.ok().body(new LoginResponse(jwt, 200, "Token created successfully!"));
        }

        return ResponseEntity.status(400).body(
                new LoginResponse(
                        400,
                        "Impossible to create token. Wrong credentials!"
                )
        );
    }
}
