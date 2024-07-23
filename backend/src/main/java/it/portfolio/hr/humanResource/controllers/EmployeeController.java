package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.exceptions.employee.EmployeesException;
import it.portfolio.hr.humanResource.exceptions.hirirng.HiringException;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import it.portfolio.hr.humanResource.models.DTOs.request.EmployeesRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.EmployeesResponseDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.SituationResponseDTO;
import it.portfolio.hr.humanResource.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeesRequestDTO employees, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            EmployeesResponseDTO response = employeeService.createEmployee(employees, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Employee created correctly", response));
        } catch (HiringException | EmployeesException | IOException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @PostMapping("/profileImg/{id}")
    public ResponseEntity<Response> uploadProfileImage(@RequestParam("profileImage") MultipartFile profileImage, @PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            boolean isUploaded = employeeService.uploadProfileImage(profileImage, id, companyName);
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "Profile image uploaded correctly",
                            isUploaded
                    )
            );
        } catch (IOException e) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping(value = "/profileImg/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Response> getProfileImage(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            byte[] profileImg = employeeService.getProfileImage(id, companyName);
            String base64Image = Base64.encodeBase64String(profileImg);
            return ResponseEntity.ok().body(
                    new ResponseValid(200,
                            "Profile image retrieved correctly",
                            base64Image
                    )
            );
        } catch (IOException e) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            e.getMessage()
                    )
            );
        }
    }

    @DeleteMapping("/profileImg/{id}")
    public ResponseEntity<Response> deleteProfileImage(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            boolean isDeleted = employeeService.deleteProfileImage(id, companyName);
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "Deleted",
                            isDeleted
                    )
            );
        } catch (IOException e) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "Unable to delete"
                    )
            );
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getEmployees(HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        List<EmployeesResponseDTO> responseDTOList = employeeService.getAllEmployees(companyName);
        if (responseDTOList.isEmpty()) {
            return ResponseEntity.status(204).body(new ResponseValidNoData(204, "No data retrieved from database"));
        }

        return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", responseDTOList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            EmployeesResponseDTO employees = employeeService.getById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", employees));
        } catch (EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Response> getByFiscalCode(@RequestParam String fiscalCode, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            EmployeesResponseDTO employees = employeeService.getByFiscalCode(fiscalCode, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly from database", employees));
        } catch (EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }


    @GetMapping("/situation/{name}")
    public ResponseEntity<Response> getSituationByName(@PathVariable String name, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            SituationResponseDTO situationResponseDTO = employeeService.getSituationByName(name, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data retrieved correctly fro employee: " + name, situationResponseDTO));
        } catch (EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @RequestBody EmployeesRequestDTO employeesRequestDTO, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            EmployeesResponseDTO employeesResponseDTO = employeeService.updateById(id, employeesRequestDTO, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data updated correctly", employeesResponseDTO));
        } catch (HiringException | EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            EmployeesResponseDTO response = employeeService.deleteById(id, companyName);
            return ResponseEntity.ok().body(new ResponseValid(200, "Data deleted correctly", response));
        } catch (EmployeesException e) {
            return ResponseEntity.status(400).body(new ResponseInvalid(400, e.getMessage()));
        }
    }
}
