package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Overtime;
import it.portfolio.hr.humanResource.entities.Permits;
import it.portfolio.hr.humanResource.entities.SickDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermitsRepository extends JpaRepository<Permits, Long> {

    @Query("SELECT p FROM Permits p WHERE p.id = :id AND p.companyName = :companyName AND p.isDeleted = false")
    Optional<Permits> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT p FROM Permits p WHERE p.companyName = :companyName")
    List<Permits> findAll(@Param("companyName") String companyName);

    @Query("SELECT p FROM Permits p WHERE LOWER(p.employees.name) LIKE LOWER(CONCAT('%', :name, '%'))  AND p.companyName = :companyName AND p.isDeleted = false")
    List<Permits> findByName(@Param("name") String name, @Param("companyName") String companyName);
}
