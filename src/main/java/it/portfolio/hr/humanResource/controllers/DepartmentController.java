package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.department.DepartmentException;
import it.portfolio.hr.humanResource.exceptions.department.DepartmentExistException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.request.DepartmentRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.DepartmentResponseDTO;
import it.portfolio.hr.humanResource.services.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/")
    public ResponseEntity<Response> createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        DepartmentResponseDTO response = departmentService.createDepartment(departmentRequestDTO, companyName);
        if (response == null) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Unable to create due to data error"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "Data retrieved correctly from database",
                        response
                )
        );

    }

    @GetMapping("/list")
    public ResponseEntity<Response> getDepList(HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        List<DepartmentResponseDTO> responseDTOList = departmentService.getAll(companyName);
        if (responseDTOList.isEmpty()) {
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "No data retrieved from database",
                            responseDTOList
                    )
            );
        }
        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "Data retrieved correctly from database",
                        responseDTOList
                )
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        DepartmentResponseDTO response = departmentService.getById(id, companyName);
        if (response == null) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Unable to retrieve due to data error"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "Data retrieved from database successfully",
                        response
                )
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody DepartmentRequestDTO departmentRequestDTO, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        DepartmentResponseDTO response = departmentService.updateById(id, departmentRequestDTO, companyName);
        if (response == null) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Unable to update due to data error"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "Data updated successfully",
                        response
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String)request.getAttribute("companyName");
        DepartmentResponseDTO response = departmentService.deleteById(id, companyName);
        if (response == null) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Unable to delete due to data error"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new ResponseValid(
                        200,
                        "Data deleted successfully from database",
                        response
                )
        );
    }
}
