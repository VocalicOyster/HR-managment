package it.portfolio.hr.humanResource.controllers;

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
        PermitsResponseDTO permitsResponseDTO = permitsService.create(permitsRequestDTO, companyName);
        if(permitsResponseDTO != null) {
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "Permits created successfully",
                            permitsResponseDTO
                    )
            );
        }
        return ResponseEntity.status(400).body(
                new ResponseInvalid(
                        400,
                        "Impossible to create a permit for employees id: " + permitsRequestDTO.getEmployees_id()
                )
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");

        PermitsResponseDTO permitsResponseDTO = permitsService.getById(id, companyName);
        if(permitsResponseDTO != null) {
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "Permits retrieved successfully",
                            permitsResponseDTO
                    )
            );
        }
        return ResponseEntity.status(400).body(
                new ResponseInvalid(
                        400,
                        "Impossible to retrieve a permit for employees id: " + id
                )
        );
    }

    @GetMapping
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");

        List<PermitsResponseDTO> responseDTOList = permitsService.getAll(companyName);

        if(responseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ResponseValidNoData(
                            200,
                            "No Data retrieved from database"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "Permits retrieved successfully",
                        responseDTOList
                )
        );
    }

    @GetMapping("/name")
    public ResponseEntity<Response> getByName(@RequestParam String name, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<PermitsResponseDTO> permitsResponseDTOList = permitsService.getByName(name, companyName);
        if(permitsResponseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ResponseValidNoData(
                            200,
                            "No data retrieved from database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "Permits retrieved from database for employee: " + name,
                        permitsResponseDTOList
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody PermitsRequestDTO permitsRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        PermitsResponseDTO permitsResponseDTO = permitsService.update(id, permitsRequestDTO, companyName);

        if(permitsResponseDTO != null) {
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "Permits updated successfully",
                            permitsResponseDTO
                    )
            );
        }
        return ResponseEntity.status(400).body(
                new ResponseInvalid(
                        400,
                        "Unable to update the permits for the id: " + id
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        PermitsResponseDTO permitsResponseDTO = permitsService.delete(id, companyName);

        if(permitsResponseDTO != null) {
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "Permits deleted successfully",
                            permitsResponseDTO
                    )
            );
        }
        return ResponseEntity.status(400).body(
                new ResponseInvalid(
                        400,
                        "Unable to delete the permits for the id: " + id
                )
        );
    }
}
