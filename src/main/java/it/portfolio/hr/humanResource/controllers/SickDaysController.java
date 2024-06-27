package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.sickdays.SickDaysException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.SickDaysRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.OvertimeResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.SickDaysResponseDTO;
import it.portfolio.hr.humanResource.services.SickDaysService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sickdays")
public class SickDaysController {

    @Autowired
    private SickDaysService sickDaysService;

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody SickDaysRequestDTO sickDaysRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            SickDaysResponseDTO sickDaysResponseDTO = sickDaysService.create(sickDaysRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Sick day created correctly for employee id: " + sickDaysRequestDTO.getEmployees_id(), sickDaysResponseDTO));
        } catch (SickDaysException | EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<SickDaysResponseDTO> sickDaysResponseDTO = sickDaysService.getAll(companyName);
        if (sickDaysResponseDTO.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValidNoData(200, "No data retrieved from database"));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Sick day retrieved correctly", sickDaysResponseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            SickDaysResponseDTO sickDaysResponseDTO = sickDaysService.getById(id, companyName);
            if (sickDaysResponseDTO != null) {
                return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", sickDaysResponseDTO));
            }
            return ResponseEntity.ok().body(new ResponseValidNoData(200, "No Sick day retrieved from database"));
        } catch (SickDaysException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }

    }

    @GetMapping("/name")
    public ResponseEntity<Response> getByName(@RequestParam String name, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<SickDaysResponseDTO> sickDaysResponseDTOList = sickDaysService.getByName(name, companyName);
        if (sickDaysResponseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValidNoData(200, "No data retrieved from database"));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Sick Days retrieved from database for employee: " + name, sickDaysResponseDTOList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody SickDaysRequestDTO sickDaysRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            SickDaysResponseDTO sickDaysResponseDTO = sickDaysService.update(id, sickDaysRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data updated correctly in database", sickDaysResponseDTO));
        } catch (SickDaysException | EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseValidNoData(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            SickDaysResponseDTO sickDaysResponseDTO = sickDaysService.delete(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data deleted correctly in database", sickDaysResponseDTO));
        } catch (SickDaysException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "No Sick day retrieved from database"));
        }
    }
}
