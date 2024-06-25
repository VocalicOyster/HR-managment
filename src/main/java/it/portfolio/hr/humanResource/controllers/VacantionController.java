package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.models.DTOs.Response;
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
public class VacantionController {

    @Autowired
    private VacantionService vacantionService;

    @PostMapping("/")
    public ResponseEntity<Response> createVacantion(@RequestBody VacantionRequestDTO vacantionRequestDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        VacantionResponseDTO responseDTO = vacantionService.createVacation(vacantionRequestDTO, companyName);
        if(responseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Unable to create a vacantion due to data error"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Vacantion created successfully",
                        responseDTO
                )
        );
     }
    @GetMapping("/list")
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        List<VacantionResponseDTO> responseDTO = vacantionService.getAll(companyName);
        if(responseDTO.isEmpty()) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "No data retrieved from database",
                            responseDTO
                            )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved correctly from database",
                        responseDTO
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        VacantionResponseDTO responseDTO = vacantionService.getById(id, companyName);
        if(responseDTO == null) {
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
                        "Data retrieved correctly from database",
                        responseDTO
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody VacantionRequestDTO vacantionRequestDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        VacantionResponseDTO responseDTO = vacantionService.updateById(vacantionRequestDTO, id, companyName);
        if(responseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Unable to update a vacantion due to data error"
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
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request){
        String companyName = (String)request.getAttribute("companyName");
        VacantionResponseDTO responseDTO = vacantionService.deleteById(id, companyName);
        if(responseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Unable to delete a vacantion due to data error"
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
