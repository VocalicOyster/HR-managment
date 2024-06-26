package it.portfolio.hr.humanResource.seeding;

import it.portfolio.hr.humanResource.models.DTOs.Response;
import it.portfolio.hr.humanResource.models.DTOs.ResponseInvalid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValid;
import it.portfolio.hr.humanResource.models.DTOs.ResponseValidNoData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/seeding")
public class SeedingController {

    @Autowired
    private SeedingService seedingService;


    @PostMapping("/init")
    public ResponseEntity<Response> initDatabase(@RequestBody SeedingObjectDTO key, HttpServletRequest request) {
        if (Objects.equals(key.getKey(), SeedingKeyData.SEEDING_KEY)) {
            String companyName = (String) request.getAttribute("companyName");

            if(!seedingService.initDatabase(companyName) ) {
                return ResponseEntity.status(400).body(
                        new ResponseInvalid(
                                400,
                                "Database seeding already performed!"
                        )
                );
            }
            return ResponseEntity.ok().body(
                    new ResponseValidNoData(
                            200,
                            "Database successfully initialized!"
                    )
            );
        }

        return ResponseEntity.status(400).body(
                new ResponseInvalid(
                        400,
                        "Unable to perform cleaning, wrong key!"
                )
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> cleanDatabase(@RequestBody SeedingObjectDTO key, HttpServletRequest request) {
        if (Objects.equals(key.getKey(), SeedingKeyData.SEEDING_KEY)) {
            String companyName = (String) request.getAttribute("companyName");
            seedingService.cleanDatabase(companyName);
            return ResponseEntity.ok().body(
                    new ResponseValidNoData(
                            200,
                            "Database cleaning performed correctly"
                    )
            );
        }
        return ResponseEntity.status(400).body(
                new ResponseInvalid(
                        400,
                        "Unable to perform cleaning, wrong key!"
                )
        );
    }
}
