package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Overtime;
import it.portfolio.hr.humanResource.entities.SickDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SickDaysRepository extends JpaRepository<SickDays, Long> {

    @Query("SELECT s FROM SickDays s WHERE s.id = :id AND s.companyName = :companyName AND s.isDeleted = false")
    Optional<SickDays> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT s FROM SickDays s WHERE s.companyName = :companyName AND s.isDeleted = false")
    List<SickDays> findAll(@Param("companyName") String companyName);

    @Query("SELECT s FROM SickDays s WHERE LOWER(s.employees.name) LIKE LOWER(CONCAT('%', :name, '%')) AND s.companyName = :companyName AND s.isDeleted = false")
    List<SickDays> findByName(@Param("name") String name, @Param("companyName") String companyName);
}
