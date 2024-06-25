package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Vacations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacationsRepository extends JpaRepository<Vacations, Long> {

    @Query("SELECT v FROM Vacations v WHERE v.id = :id AND v.companyName = :companyName AND v.isDeleted = false")
    Optional<Vacations> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT v FROM Vacations v WHERE v.companyName = :companyName AND v.isDeleted = false")
    List<Vacations> findAll(@Param("companyName") String companyName);
}
