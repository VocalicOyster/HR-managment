package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.interviews.InterviewException;
import it.portfolio.hr.humanResource.exceptions.applicant.ApplicantException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.InterviewRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.InterviewResponseDTO;
import it.portfolio.hr.humanResource.services.InterviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping("/")
    public ResponseEntity<Response> createInterview(@RequestBody InterviewRequestDTO interviewRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            InterviewResponseDTO interviewResponseDTO = interviewService.createInterview(interviewRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Interview created successfully", interviewResponseDTO));
        } catch (ApplicantException | InterviewException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllInterview(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<InterviewResponseDTO> interview = interviewService.getAllInterview(companyName);
        if (interview.isEmpty()) {
            return ResponseEntity.status(200).body(new ResponseValid(200, "No interview retrieved from database", interview));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Interviews retrieved correctly from database", interview));
    }

    @PostMapping("/control")
    public ResponseEntity<Response> controlInterview(@RequestBody InterviewRequestDTO interviewRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        boolean isPresent =  interviewService.controlInterview(interviewRequestDTO, companyName);
        if(isPresent) {
            return ResponseEntity.status(400).body(
                    new ResponseValidNoData(
                            400,
                            "Interview at this time is already programmed"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new ResponseValidNoData(
                        200,
                        "No interview at this time"
                )
        );
    }


    @PostMapping("/time")
    public ResponseEntity<Response> getAllAvailableTime(@RequestParam String date, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<String> availableTimes = interviewService.getAvailableTimes(date, companyName);
        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "retrieved",
                        availableTimes
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            InterviewResponseDTO interview = interviewService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Interviews retrieved correctly from database", interview));
        } catch (InterviewException e) {
            return ResponseEntity.status(400).body(new ResponseValidNoData(400, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody InterviewRequestDTO interviewRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            InterviewResponseDTO interviewResponseDTO = interviewService.updateById(id, interviewRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Interviews updated correctly", interviewResponseDTO));
        } catch (InterviewException | ApplicantException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            InterviewResponseDTO interviewResponseDTO = interviewService.deleteById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Interviews deleted correctly", interviewResponseDTO));
        } catch (InterviewException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
