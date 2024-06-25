package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.models.DTOs.Response;
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
        String companyName = (String)request.getAttribute("companyName");
        CandidatesEvaluationResponseDTO response = candidateEvaluationService.createCandidateEv(candidateEvaluationRequestDTO, companyName);
        if(response == null) {
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
                        "Candidate Evaluation created successfully",
                        response
                )
        );

    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllCandidateEv(HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        List<CandidatesEvaluationResponseDTO> response = candidateEvaluationService.getAllCanEv(companyName);
        if (response.isEmpty()) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "No data retrieved from database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved correctly",
                        response
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getByid(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        CandidatesEvaluationResponseDTO candidateDTO = candidateEvaluationService.getById(id, companyName);
        if (candidateDTO == null) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "No data retrieved from database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved Successfully",
                        candidateDTO
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody CandidateEvaluationRequestDTO candidateDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        CandidatesEvaluationResponseDTO responseDTO = candidateEvaluationService.updateCandidate(id, candidateDTO, companyName);
        if (responseDTO == null) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "No data retrieved from database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved Successfully",
                        responseDTO
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        CandidatesEvaluationResponseDTO response = candidateEvaluationService.deleteById(id, companyName);
        if (response == null) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "Candidate evaluation not retrieved from database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Candidate evaluation deleted successfully",
                        response
                )
        );
    }
}
