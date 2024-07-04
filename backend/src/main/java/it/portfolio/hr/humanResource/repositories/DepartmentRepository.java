package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.CandidateEvaluations;
import it.portfolio.hr.humanResource.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d WHERE d.name = :name AND d.companyName = :companyName AND d.isDeleted = false")
    Optional<Department> findByName(@Param("name") String name, @Param("companyName") String companyName);

    @Query("SELECT d FROM Department d WHERE d.id = :id AND d.companyName = :companyName AND d.isDeleted = false")
    Optional<Department> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT d FROM Department d WHERE d.companyName = :companyName AND d.isDeleted = false")
    List<Department> findAll(@Param("companyName") String companyName);
}
