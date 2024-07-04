package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.permits.PermitsException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.PermitsRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.OvertimeResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.PermitsResponseDTO;
import it.portfolio.hr.humanResource.services.PermitsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permits")
public class PermitsController {

    @Autowired
    private PermitsService permitsService;


    @PostMapping
    public ResponseEntity<Response> create(@RequestBody PermitsRequestDTO permitsRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            PermitsResponseDTO permitsResponseDTO = permitsService.create(permitsRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Permits created successfully", permitsResponseDTO));
        } catch (PermitsException | EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            PermitsResponseDTO permitsResponseDTO = permitsService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Permits retrieved successfully", permitsResponseDTO));
        } catch (PermitsException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<PermitsResponseDTO> responseDTOList = permitsService.getAll(companyName);
        if (responseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValidNoData(200, "No Data retrieved from database"));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Permits retrieved successfully", responseDTOList));
    }

    @GetMapping("/name")
    public ResponseEntity<Response> getByName(@RequestParam String name, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<PermitsResponseDTO> permitsResponseDTOList = permitsService.getByName(name, companyName);
        if (permitsResponseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValidNoData(200, "No data retrieved from database"));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Permits retrieved from database for employee: " + name, permitsResponseDTOList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody PermitsRequestDTO permitsRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            PermitsResponseDTO permitsResponseDTO = permitsService.update(id, permitsRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Permits updated successfully", permitsResponseDTO));
        } catch (PermitsException | EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            PermitsResponseDTO permitsResponseDTO = permitsService.delete(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Permits deleted successfully", permitsResponseDTO));
        } catch (PermitsException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
