package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.department.DepartmentException;
import it.portfolio.hr.humanResource.exceptions.hirirng.*;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.HiringRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.HiringResponseDTO;
import it.portfolio.hr.humanResource.services.HiringService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hiring")
public class HiringController {

    @Autowired
    private HiringService hiringService;

    @PostMapping("/")
    public ResponseEntity<Response> createHiring(@RequestBody HiringRequestDTO hiringRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            HiringResponseDTO hiringResponseDTO = hiringService.createHiring(hiringRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Hiring create successfully", hiringResponseDTO));
        } catch (DepartmentException | HiringException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<HiringResponseDTO> hiringResponseDTOList = hiringService.getAll(companyName);
        if (hiringResponseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValid(200, "No data retrieved from database", hiringResponseDTOList));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", hiringResponseDTOList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            HiringResponseDTO hiringResponseDTO = hiringService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", hiringResponseDTO));
        } catch (HiringException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody HiringRequestDTO hiringRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            HiringResponseDTO hiringResponseDTO = hiringService.updateById(id, hiringRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data updated correctly from database", hiringResponseDTO));
        } catch (HiringException | DepartmentException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            HiringResponseDTO hiringResponseDTO = hiringService.deleteById(id, companyName);

            return ResponseEntity.ok().body(new ResponseValid(200, "Data updated correctly from database", hiringResponseDTO));
        } catch (HiringException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
