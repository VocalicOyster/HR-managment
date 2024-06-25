package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.hirirng.*;
import it.portfolio.hr.humanResource.models.DTOs.Response;
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
        String companyName = (String)request.getAttribute("companyName");
        HiringResponseDTO hiringResponseDTO = hiringService.createHiring(hiringRequestDTO, companyName);
        if (hiringResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Unable to create an hiring due to data error"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Hiring create successfully"
                )
        );
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        List<HiringResponseDTO> hiringResponseDTOList = hiringService.getAll(companyName);
        if (hiringResponseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(
                    new Response(
                            200,
                            "No data retieved from database",
                            hiringResponseDTOList
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retieved correctly from database",
                        hiringResponseDTOList
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        HiringResponseDTO hiringResponseDTO = hiringService.getById(id, companyName);
        if (hiringResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "No data retieved from database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retieved correctly from database",
                        hiringResponseDTO
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody HiringRequestDTO hiringRequestDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        HiringResponseDTO hiringResponseDTO = hiringService.updateById(id, hiringRequestDTO, companyName);
        if (hiringResponseDTO == null) {
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
                        "Data updated correctly from database",
                        hiringResponseDTO
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        HiringResponseDTO hiringResponseDTO = hiringService.deleteById(id, companyName);
        if (hiringResponseDTO == null) {
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
                        "Data updated correctly from database",
                        hiringResponseDTO
                )
        );
    }
}
