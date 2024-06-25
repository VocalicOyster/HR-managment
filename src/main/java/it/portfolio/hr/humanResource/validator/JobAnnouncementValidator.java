package it.portfolio.hr.humanResource.validator;

import it.portfolio.hr.humanResource.entities.JobAnnouncement;
import it.portfolio.hr.humanResource.models.DTOs.request.JobAnnouncementRequestDTO;
import it.portfolio.hr.humanResource.repositories.JobAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class JobAnnouncementValidator {

    @Autowired
    private JobAnnouncementRepository jobAnnouncementRepository;
    public boolean isJobAnnouncementValid(JobAnnouncementRequestDTO jobAnnouncementRequestDTO, String companyName) {
        return isJobAnnouncementNotNull(jobAnnouncementRequestDTO) &&
                jobAnnounceExist(jobAnnouncementRequestDTO, companyName);
    }

    private boolean isJobAnnouncementNotNull(JobAnnouncementRequestDTO jobAnnouncementRequestDTO) {
        return jobAnnouncementRequestDTO.getDescription() != null &&
                jobAnnouncementRequestDTO.getTitle() != null &&
                jobAnnouncementRequestDTO.getDepartment_id() != null;
    }

    private boolean jobAnnounceExist(JobAnnouncementRequestDTO jobAnnouncementRequestDTO, String companyName)  {
        List<JobAnnouncement> jobAnnouncementList = jobAnnouncementRepository.findAll(companyName);

        for(JobAnnouncement job: jobAnnouncementList) {
            if(Objects.equals(job.getDepartment().getId(), jobAnnouncementRequestDTO.getDepartment_id())) {
                return false;
            }
        }
        return true;
    }
}
