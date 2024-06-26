package it.portfolio.hr.humanResource.controllers;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import it.portfolio.hr.humanResource.services.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("/api/paycheck")
public class PaycheckController {


    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload/{id}")
    public ResponseEntity<Response> upload(@RequestParam MultipartFile file, @PathVariable Long id, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            File uploadedFile = fileStorageService.upload(file, id, companyName);
            return ResponseEntity.ok().body(
                    new it.portfolio.hr.humanResource.models.DTOs.ResponseValid(200,
                            "File uploaded correctly",
                            uploadedFile
                    )
            );
        } catch (IOException e) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(400,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/download/{id}/{month}")
    public byte[] download(@PathVariable Long id, @PathVariable int month, HttpServletRequest request, HttpServletResponse response) {
        String companyName = (String) request.getAttribute("companyName");
        try {
            Employees employees = employeesRepository.findById(id, companyName).orElse(null);
            if (employees == null) {
                response.setStatus(400);
                return null;
            }
            String m = Month.of(month).toString().toUpperCase();
            String fileName = employees.getName() + m + String.valueOf(LocalDate.now().getYear()) + ".pdf";

            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            return fileStorageService.downloadById(fileName);
        } catch (IOException e) {
            response.setStatus(400);
            return null;
        }
    }

    @PostMapping("/delete/{id}/{month}/{year}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id, @PathVariable int month, @PathVariable String year, HttpServletRequest request) {
        String companyName = (String) request.getAttribute("companyName");
        Employees employees = employeesRepository.findById(id, companyName).orElse(null);

        if (employees == null) {
            return ResponseEntity.status(400).body(
                    new ResponseInvalid(
                            400,
                            "No employee retrieved for id " + id
                    )
            );
        }
        String m = Month.of(month).toString().toUpperCase();
        String fileName = employees.getName() + m + year + ".pdf";
        if (fileStorageService.deleteById(fileName)) {
            return ResponseEntity.ok().body(
                    new ResponseValid(
                            200,
                            "Employee paycheck deleted correctly",
                            fileName
                    )
            );
        }

        return ResponseEntity.status(400).body(
                new ResponseInvalid(
                        400,
                        "Paycheck for " + m + " " + year + " of employee " + employees.getName() + " is not in database"
                )
        );
    }

    @GetMapping("/downloadAll/{id}")
    public byte[] downloadAll(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String companyName = (String) request.getAttribute("companyName");
        String fileName = fileStorageService.downloadAllBYEmployeeId(id, companyName);
        if(fileName == null) {
            response.setStatus(400);
            return null;
        }
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        return fileStorageService.downloadById(fileName);
    }
}
