package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.overtime.OvertimeException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.request.OvertimeRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.OvertimeResponseDTO;
import it.portfolio.hr.humanResource.services.OvertimeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overtime")
public class OvertimeController {
    @Autowired
    private OvertimeService overtimeService;

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody OvertimeRequestDTO overtimeRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            OvertimeResponseDTO overtimeResponseDTO = overtimeService.create(overtimeRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Overtime created correctly for employee id: " + overtimeRequestDTO.getEmployees_id(), overtimeResponseDTO));
        } catch (EmployeesException | OvertimeException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "Unable to create a new overtime"));
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<OvertimeResponseDTO> overtimeResponseDTO = overtimeService.getAll(companyName);
        if (overtimeResponseDTO.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValid(200, "No data retrieved from database", overtimeResponseDTO));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Overtimes retrieved correctly", overtimeResponseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            OvertimeResponseDTO overtimeResponseDTO = overtimeService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", overtimeResponseDTO));
        } catch (OvertimeException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "No overtime day retrieved from database"));
        }
    }

    @GetMapping("/name")
    public ResponseEntity<Response> getByName(@RequestParam String name, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<OvertimeResponseDTO> overtimeResponseDTO = overtimeService.getAllByName(name, companyName);
        if (overtimeResponseDTO.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValid(200, "No data retrieved from database", overtimeResponseDTO));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Overtime retrieved from database for employee: " + name, overtimeResponseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody OvertimeRequestDTO overtimeRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            OvertimeResponseDTO overtimeResponseDTO = overtimeService.update(id, overtimeRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data updated correctly in database", overtimeResponseDTO));
        } catch (EmployeesException | OvertimeException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "No overtime retrieved from database"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            OvertimeResponseDTO overtimeResponseDTO = overtimeService.delete(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data deleted correctly in database", overtimeResponseDTO));
        } catch (OvertimeException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, "No overtime day retrieved from database"));
        }
    }

}
