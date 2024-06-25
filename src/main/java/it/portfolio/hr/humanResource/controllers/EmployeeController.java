package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.employee.HiringNotFoundException;
import it.portfolio.hr.humanResource.exceptions.employee.InvalidEmployeeException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.request.EmployeesRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.EmployeesResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.SituationResponseDTO;
import it.portfolio.hr.humanResource.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeesRequestDTO employees, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        EmployeesResponseDTO response = employeeService.createEmployee(employees, companyName);
        if(response == null) {
            return ResponseEntity.status(400).body(
                    new Response(400,
                            "Unable to create due to data error"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(200,
                        "Employee created correctly",
                        response)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getEmployees(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<EmployeesResponseDTO> responseDTOList = employeeService.getAllEmployees(companyName);
        if (responseDTOList.isEmpty()) {
            return ResponseEntity.status(200).body(
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
                        responseDTOList
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        EmployeesResponseDTO employees = employeeService.getById(id, companyName);
        if (employees == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Invalid ID"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved correctly from database",
                        employees
                )
        );
    }

    @GetMapping
    public ResponseEntity<Response> getByFiscalCode(@RequestParam String fiscalCode, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        EmployeesResponseDTO employees = employeeService.getByFiscalCode(fiscalCode, companyName);
        if (employees == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Invalid Fiscal Code"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved correctly from database",
                        employees
                )
        );
    }


    @GetMapping("/situation/{name}")
    public ResponseEntity<Response> getSituationByName(@PathVariable String name,HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        SituationResponseDTO situationResponseDTO = employeeService.getSituationByName(name, companyName);

        if(situationResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Employees name in not in database"
                    )
            );
        }
        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data retrieved correctly fro employee: " + name,
                        situationResponseDTO
                )
        );

    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody EmployeesRequestDTO employeesRequestDTO, HttpServletRequest request)  {
        String companyName = (String) request.getAttribute("companyName");
        EmployeesResponseDTO employeesResponseDTO = employeeService.updateById(id, employeesRequestDTO, companyName);
        if (employeesResponseDTO == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Invalid Fiscal Code"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data updated correctly",
                        employeesResponseDTO
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        EmployeesResponseDTO response = employeeService.deleteById(id, companyName);
        if (response == null) {
            return ResponseEntity.status(400).body(
                    new Response(
                            400,
                            "Invalid Id"
                    )
            );
        }

        return ResponseEntity.ok().body(
                new Response(
                        200,
                        "Data deleted correctly",
                        response
                )
        );
    }
}
