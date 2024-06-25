package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.request.JobAnnouncementRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.JobAnnouncementResponseDTO;
import it.portfolio.hr.humanResource.services.JobAnnouncementService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
public class JobAnnouncementController {

    @Autowired
    private JobAnnouncementService jobAnnouncementService;

    @PostMapping("/")
    public ResponseEntity<Response> createAnnouce(@RequestBody JobAnnouncementRequestDTO jobAnnouncementRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        JobAnnouncementResponseDTO responseDTO = jobAnnouncementService.createAnnounce(jobAnnouncementRequestDTO, companyName);
        if(responseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Unable to create an announce due to data error"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Announce created successfully",
                        responseDTO
                )
        );
    }
    @GetMapping("/list")
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<JobAnnouncementResponseDTO> jobList = jobAnnouncementService.getAll(companyName);
        if(jobList.isEmpty()) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "No data retrieved from Database",
                            jobList
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved correctly from Database",
                        jobList
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        JobAnnouncementResponseDTO jobAnnouncementResponseDTO = jobAnnouncementService.getById(id, companyName);
        if(jobAnnouncementResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "No data retrieved from Database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved correctly from Database",
                        jobAnnouncementResponseDTO
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody JobAnnouncementRequestDTO requestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        JobAnnouncementResponseDTO responseDTO = jobAnnouncementService.updateById(requestDTO, id, companyName);
        if(responseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Unable to update due to data error"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data updated correctly",
                        responseDTO
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        JobAnnouncementResponseDTO responseDTO = jobAnnouncementService.deleteById(id, companyName);
        if(responseDTO == null) {
                return ResponseEntity.status(400).body(
                        new Response(
                                400,
                                "Unable to delete due to data error"
                        )
                );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data deleted correctly",
                        responseDTO
                )
        );
    }
}
