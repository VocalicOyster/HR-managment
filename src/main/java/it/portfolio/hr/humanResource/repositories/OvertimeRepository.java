package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Long> {
    @Query("SELECT o FROM Overtime o WHERE o.id = :id AND o.companyName = :companyName AND o.isDeleted = false")
    Optional<Overtime> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT o FROM Overtime o WHERE o.companyName = :companyName")
    List<Overtime> findAll(@Param("companyName") String companyName);

    @Query("SELECT o FROM Overtime o WHERE LOWER(o.employees.name) LIKE LOWER(CONCAT('%', :name, '%')) AND o.companyName = :companyName AND o.isDeleted = false")
    List<Overtime> findByName(@Param("name") String name, @Param("companyName") String companyName);
}
