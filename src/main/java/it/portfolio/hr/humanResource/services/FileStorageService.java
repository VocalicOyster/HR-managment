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
import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public boolean deleteById(String fileName) {
        File file = new File(filePath + "\\" + fileName);
        if (!file.exists()) return false;
        return file.delete();
    }

    public String downloadAllBYEmployeeId(Long id, String companyName) throws IOException {
        Employees employees = employeesRepository.findById(id, companyName).orElse(null);
        if (employees == null) return null;
        List<String> files = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String m = Month.of(i).toString().toUpperCase();
            files.add(filePath + "\\" + employees.getName() + m + String.valueOf(LocalDate.now().getYear()) + ".pdf");
        }

        if (files.isEmpty()) {
            return null;
        }

        Iterator<String> iterator = files.iterator();
        while (iterator.hasNext()) {
            File file = new File(iterator.next());
            if (!file.exists()) {
                iterator.remove();
            }
        }
        if (files.isEmpty()) {
            return null;
        }
        List<File> fileToAppend = new ArrayList<>();

        for(String file: files) {
            fileToAppend.add(new File(file));
        }
        File zipFile = new File(filePath+"\\"+employees.getName()+".zip");
        createZipFile(fileToAppend, zipFile);
        downloadById(zipFile.getName());
        return zipFile.getName();
    }

    public void createZipFile(List<File> fileToAppend, File zipFile) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (File file : fileToAppend) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }
                    zipOutputStream.closeEntry();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}




