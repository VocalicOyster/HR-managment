package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.applicant.ApplicantException;
import it.portfolio.hr.humanResource.exceptions.candidateEv.CandidateEvaluationException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.CandidateEvaluationRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.CandidatesEvaluationResponseDTO;
import it.portfolio.hr.humanResource.services.CandidateEvaluationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateEvaluationController {

    @Autowired
    private CandidateEvaluationService candidateEvaluationService;


    @PostMapping("/")
    public ResponseEntity<Response> createCandidateEv(@RequestBody CandidateEvaluationRequestDTO candidateEvaluationRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            CandidatesEvaluationResponseDTO response = candidateEvaluationService.createCandidateEv(candidateEvaluationRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Candidate Evaluation created successfully", response));
        } catch (ApplicantException | CandidateEvaluationException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllCandidateEv(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<CandidatesEvaluationResponseDTO> response = candidateEvaluationService.getAllCanEv(companyName);
        if (response.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValidNoData(200, "No data retrieved from database"));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            CandidatesEvaluationResponseDTO candidateDTO = candidateEvaluationService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved Successfully", candidateDTO));
        } catch (CandidateEvaluationException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody CandidateEvaluationRequestDTO candidateDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            CandidatesEvaluationResponseDTO responseDTO = candidateEvaluationService.updateCandidate(id, candidateDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved Successfully", responseDTO));
        } catch (CandidateEvaluationException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            CandidatesEvaluationResponseDTO response = candidateEvaluationService.deleteById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Candidate evaluation deleted successfully", response));
        } catch (CandidateEvaluationException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
