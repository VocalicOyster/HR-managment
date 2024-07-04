package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Applicants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicants, Long> {

    @Query("SELECT a FROM Applicants a where a.fiscalCode = :fiscalCode AND a.companyName = :companyName AND a.isDeleted = false")
    Optional<Applicants> findByFiscalCode(@Param("fiscalCode") String fiscalCode, @Param("companyName") String companyName);

    @Query("SELECT a FROM Applicants a WHERE a.id = :id AND a.companyName = :companyName AND a.isDeleted = false")
    Optional<Applicants> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT a FROM Applicants a WHERE a.companyName = :companyName AND a.isDeleted = false")
    List<Applicants> findAll(@Param("companyName") String companyName);
}
