package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Employees;
import it.portfolio.hr.humanResource.repositories.EmployeesRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${pathStorage}")
    private String filePath;

    @Autowired
    private EmployeesRepository employeesRepository;

    public File upload(MultipartFile file, Long id, String companyName) throws IOException {
        Employees employees = employeesRepository.findById(id, companyName).orElse(null);
        if (employees == null) throw new IOException("Employee Id is not valid");

        String month = LocalDate.now().getMonth().toString();
        String year = String.valueOf(LocalDate.now().getYear());
        String completeName = employees.getName() +
                month +
                year +
                "." + FilenameUtils.getExtension(file.getOriginalFilename());

        File destinationDirectory = new File(filePath + "\\" + completeName);
        File controlFile = new File(filePath);
        if (!controlFile.exists()) throw new IOException("Folder doesn't exist");
        if (!controlFile.isDirectory()) throw new IOException("This is not a directory");
        if (destinationDirectory.exists()) throw new IOException("File already exist");
        file.transferTo(destinationDirectory.toPath());
        return destinationDirectory;
    }

    public byte[] downloadById(String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        if (!file.exists()) throw new IOException("File doesn't exist");
        return IOUtils.toByteArray(new FileInputStream(file));
    }

}
