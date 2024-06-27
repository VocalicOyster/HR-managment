package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.department.DepartmentException;
import it.portfolio.hr.humanResource.exceptions.jobAnnouncement.JobAnnouncementExceptions;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
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
        try {
            JobAnnouncementResponseDTO responseDTO = jobAnnouncementService.createAnnounce(jobAnnouncementRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Announce created successfully", responseDTO));
        } catch (DepartmentException | JobAnnouncementExceptions e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<JobAnnouncementResponseDTO> jobList = jobAnnouncementService.getAll(companyName);
        if (jobList.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValid(200, "No data retrieved from Database", jobList));
        }

        return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from Database", jobList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            JobAnnouncementResponseDTO jobAnnouncementResponseDTO = jobAnnouncementService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from Database", jobAnnouncementResponseDTO));
        } catch (JobAnnouncementExceptions e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody JobAnnouncementRequestDTO requestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            JobAnnouncementResponseDTO responseDTO = jobAnnouncementService.updateById(requestDTO, id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data updated correctly", responseDTO));
        } catch (DepartmentException | JobAnnouncementExceptions e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            JobAnnouncementResponseDTO responseDTO = jobAnnouncementService.deleteById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data deleted correctly", responseDTO));
        } catch (JobAnnouncementExceptions e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
