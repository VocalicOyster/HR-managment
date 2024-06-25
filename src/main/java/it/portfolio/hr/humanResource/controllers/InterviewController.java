package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.request.InterviewRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.InterviewResponseDTO;
import it.portfolio.hr.humanResource.services.InterviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping("/")
    public ResponseEntity<Response> createInterview(@RequestBody InterviewRequestDTO interviewRequestDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        InterviewResponseDTO interviewResponseDTO = interviewService.createInterview(interviewRequestDTO, companyName);
        if(interviewResponseDTO != null) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "Intirview created succefully",
                            interviewResponseDTO
                    )
            );
        }

        return ResponseEntity.status(400).body(
                new Response(
                        400,
                        "Impossible to create a new interview"
                )
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllInterview(HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        List<InterviewResponseDTO> interview = interviewService.getAllInterview(companyName);
        if(interview.isEmpty()) {
            return ResponseEntity.status(200).body(
                    new Response(
                            200,
                            "No intirview retreived from database",
                            interview
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Interviews retreived correctly from database",
                        interview
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        InterviewResponseDTO interview = interviewService.getById(id, companyName);
        if(interview == null) {
            return ResponseEntity.status(200).body(
                    new Response(
                            200,
                            "No intirview retreived from database"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Interviews retreived correctly from database",
                        interview
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody InterviewRequestDTO interviewRequestDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        InterviewResponseDTO interviewResponseDTO = interviewService.updateById(id, interviewRequestDTO, companyName);
        if(interviewResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "An error accurred with data"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Interviews updated correctly",
                        interviewResponseDTO
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        InterviewResponseDTO interviewResponseDTO = interviewService.deleteById(id, companyName);
        if(interviewResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "An error accurred with data"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Interviews deleted correctly",
                        interviewResponseDTO
                )
        );
    }
}
