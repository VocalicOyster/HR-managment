package it.portfolio.hr.humanResource.services;

import it.portfolio.hr.humanResource.entities.Department;
import it.portfolio.hr.humanResource.entities.JobAnnouncement;
import it.portfolio.hr.humanResource.exceptions.department.DepartmentException;
import it.portfolio.hr.humanResource.exceptions.jobAnnouncement.JobAnnouncementExceptions;
import it.portfolio.hr.humanResource.models.DTOs.request.JobAnnouncementRequestDTO;
import it.portfolio.hr.humanResource.models.DTOs.response.JobAnnouncementResponseDTO;
import it.portfolio.hr.humanResource.repositories.DepartmentRepository;
import it.portfolio.hr.humanResource.repositories.JobAnnouncementRepository;
import it.portfolio.hr.humanResource.validator.JobAnnouncementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobAnnouncementService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JobAnnouncementValidator jobAnnouncementValidator;

    @Autowired
    private JobAnnouncementRepository jobAnnouncementRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public JobAnnouncementResponseDTO createAnnounce(JobAnnouncementRequestDTO jobAnnouncementRequestDTO, String companyName) throws JobAnnouncementExceptions, DepartmentException {
        if (jobAnnouncementValidator.isJobAnnouncementValid(jobAnnouncementRequestDTO, companyName)) {
            JobAnnouncement jobAnnouncement = modelMapper.map(jobAnnouncementRequestDTO, JobAnnouncement.class);
            Department department = departmentRepository.findById(jobAnnouncementRequestDTO.getDepartment_id(), companyName).orElseThrow(() -> new DepartmentException("No department retrieved with id: " + jobAnnouncementRequestDTO.getDepartment_id(), 400));

            jobAnnouncement.setDepartment(department);
            jobAnnouncement.setCompanyName(companyName);
            jobAnnouncement.setDeleted(false);
            jobAnnouncementRepository.saveAndFlush(jobAnnouncement);
            JobAnnouncementResponseDTO responseDTO = modelMapper.map(jobAnnouncement, JobAnnouncementResponseDTO.class);
            responseDTO.setDepartment(jobAnnouncement.getDepartment());
            return responseDTO;
        }
        throw new JobAnnouncementExceptions("The job announcement's info are not valid", 400);
    }

    public List<JobAnnouncementResponseDTO> getAll(String companyName) {
        List<JobAnnouncement> jobList = jobAnnouncementRepository.findAll(companyName);
        List<JobAnnouncementResponseDTO> jobListDTO = new ArrayList<>();

        for (JobAnnouncement job : jobList) {
            JobAnnouncementResponseDTO jobDTO = modelMapper.map(job, JobAnnouncementResponseDTO.class);
            jobDTO.setDepartment(job.getDepartment());
            jobListDTO.add(jobDTO);
        }

        return jobListDTO;
    }

    public JobAnnouncementResponseDTO getById(Long id, String companyName) throws JobAnnouncementExceptions {
        JobAnnouncement jobAnnouncement = jobAnnouncementRepository.findById(id, companyName).orElseThrow(() -> new JobAnnouncementExceptions("No job announcement retrieved with id: " + id, 400));
        JobAnnouncementResponseDTO jobDTO = modelMapper.map(jobAnnouncement, JobAnnouncementResponseDTO.class);
        jobDTO.setDepartment(jobAnnouncement.getDepartment());
        return jobDTO;
    }

    public JobAnnouncementResponseDTO updateById(JobAnnouncementRequestDTO jobAnnouncementRequestDTO, Long id, String companyName) throws JobAnnouncementExceptions, DepartmentException {
        if (jobAnnouncementValidator.isJobAnnouncementValid(jobAnnouncementRequestDTO, companyName)) {
            JobAnnouncement jobAnnouncement = jobAnnouncementRepository.findById(id).orElseThrow(() -> new JobAnnouncementExceptions("No job announcement retrieved with id: " + id, 400));

            jobAnnouncement.setTitle(jobAnnouncementRequestDTO.getTitle());
            jobAnnouncement.setDescription(jobAnnouncement.getDescription());
            Department department = departmentRepository.findById(jobAnnouncementRequestDTO.getDepartment_id(), companyName).orElseThrow(() -> new DepartmentException("No department retrieved with id: " + jobAnnouncementRequestDTO.getDepartment_id(), 400));

            jobAnnouncement.setDepartment(department);
            jobAnnouncement.setControctsEnum(jobAnnouncement.getControctsEnum());
            jobAnnouncement.setPerTimeEnum(jobAnnouncement.getPerTimeEnum());
            jobAnnouncementRepository.saveAndFlush(jobAnnouncement);
            JobAnnouncementResponseDTO responseDTO = modelMapper.map(jobAnnouncement, JobAnnouncementResponseDTO.class);
            responseDTO.setDepartment(jobAnnouncement.getDepartment());
            return responseDTO;
        }
        throw new JobAnnouncementExceptions("The job announcement's info are not valid", 400);
    }

    public JobAnnouncementResponseDTO deleteById(Long id, String companyName) throws JobAnnouncementExceptions {
        JobAnnouncement jobAnnouncement = jobAnnouncementRepository.findById(id, companyName).orElseThrow(() -> new JobAnnouncementExceptions("No job announcement retrieved with id: " + id, 400));
        jobAnnouncement.setDeleted(true);
        jobAnnouncementRepository.saveAndFlush(jobAnnouncement);
        JobAnnouncementResponseDTO responseDTO = modelMapper.map(jobAnnouncement, JobAnnouncementResponseDTO.class);
        responseDTO.setDepartment(jobAnnouncement.getDepartment());
        return responseDTO;
    }
}
