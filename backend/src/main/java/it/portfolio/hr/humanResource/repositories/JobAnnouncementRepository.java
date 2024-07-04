package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.JobAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobAnnouncementRepository extends JpaRepository<JobAnnouncement, Long> {

    @Query("SELECT j FROM JobAnnouncement j WHERE j.id = :id AND j.companyName = :companyName AND j.isDeleted = false")
    Optional<JobAnnouncement> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT j FROM JobAnnouncement j WHERE  j.companyName = :companyName AND j.isDeleted = false")
    List<JobAnnouncement> findAll(@Param("companyName") String companyName);
}
