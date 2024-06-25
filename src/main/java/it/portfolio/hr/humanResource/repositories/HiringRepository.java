package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Hiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HiringRepository extends JpaRepository<Hiring, Long> {

    @Query("SELECT h FROM Hiring h WHERE h.id = :id AND h.companyName = :companyName AND h.isDeleted = false")
    Optional<Hiring> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT h FROM Hiring h WHERE h.companyName = :companyName AND h.isDeleted = false")
    List<Hiring> findAll(@Param("companyName") String companyName);
}
