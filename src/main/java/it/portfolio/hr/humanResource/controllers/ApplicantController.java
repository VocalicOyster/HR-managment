package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.applicant.ApplicantException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.ApplicantRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.ApplicantResponseDTO;
import it.portfolio.hr.humanResource.services.ApplicantServices;
import it.portfolio.hr.humanResource.validator.ApplicantValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app")
public class ApplicantController {

    @Autowired
    private ApplicantServices applicantServices;
    @Autowired
    private ApplicantValidator applicantValidator;

    @PostMapping("/")
    public ResponseEntity<Response> createApplicant(@RequestBody ApplicantRequestDTO applicantRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            ApplicantResponseDTO applicantResponseDTO = applicantServices.createNewApplicant(applicantRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "New Applicant created Correctly", applicantResponseDTO));
        } catch (ApplicantException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllApplicant(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<ApplicantResponseDTO> applicantResponseDTOList = applicantServices.getAllApplicant(companyName);

        if (applicantResponseDTOList.isEmpty()) {
            return ResponseEntity.status(200).body(new ResponseValidNoData(200, "No data retrieved from database"));
        }

        return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly", applicantResponseDTOList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            ApplicantResponseDTO applicantResponseDTO = applicantServices.getById(id, companyName);

            return ResponseEntity.status(200).body(new ResponseValid(200, "Data retrieved correctly", applicantResponseDTO));
        } catch (ApplicantException e) {
            return ResponseEntity.status(400).body(new ResponseValidNoData(400, e.getMessage()));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response> updateApplicantById(@PathVariable Long id, @RequestBody ApplicantRequestDTO applicantRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            ApplicantResponseDTO applicantResponseDTO = applicantServices.updateById(id, applicantRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Applicant updates correctly", applicantResponseDTO));

        } catch (ApplicantException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "An error accurred with data"));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            ApplicantResponseDTO applicantResponseDTO = applicantServices.deleteById(id, companyName);

            return ResponseEntity.ok().body(new ResponseValid(200, "Applicant deleted correctly", applicantResponseDTO));
        } catch (ApplicantException e) {
            return ResponseEntity.status(400).body(new ResponseValidNoData(400, e.getMessage()));
        }

    }
}
