package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.vacation.VacationException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.VacantionRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.VacantionResponseDTO;
import it.portfolio.hr.humanResource.services.VacantionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacantion")
public class VacationController {

    @Autowired
    private VacantionService vacantionService;

    @PostMapping("/")
    public ResponseEntity<Response> createVacation(@RequestBody VacantionRequestDTO vacantionRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            VacantionResponseDTO responseDTO = vacantionService.createVacation(vacantionRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Vacation created successfully", responseDTO));
        } catch (EmployeesException | VacationException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<VacantionResponseDTO> responseDTO = vacantionService.getAll(companyName);
        if (responseDTO.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseValidNoData(200, "No data retrieved from database"));
        }
        return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", responseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            VacantionResponseDTO responseDTO = vacantionService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", responseDTO));
        } catch (VacationException e) {
            return ResponseEntity.status(400).body(new ResponseValidNoData(400, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody VacantionRequestDTO vacantionRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            VacantionResponseDTO responseDTO = vacantionService.updateById(vacantionRequestDTO, id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data updated correctly", responseDTO));
        } catch (VacationException | EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            VacantionResponseDTO responseDTO = vacantionService.deleteById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data deleted correctly", responseDTO));
        } catch (VacationException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
