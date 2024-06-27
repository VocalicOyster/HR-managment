package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Applicants;
import it.portfolio.hr.humanResource.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    @Query("SELECT e FROM Employees e WHERE e.fiscalCode = :fiscalCode AND e.companyName = :companyName AND e.isDeleted = false")
    Optional<Employees> findByFiscalCode(@Param("fiscalCode") String fiscalCode,@Param("companyName") String companyName);

    @Query("SELECT e FROM Employees e WHERE e.id = :id AND e.companyName = :companyName AND e.isDeleted = false")
    Optional<Employees> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT e FROM Employees e WHERE e.companyName = :companyName AND e.isDeleted = false")
    List<Employees> findAll(@Param("companyName") String companyName);

    @Query("SELECT e FROM Employees e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%' , :name , '%')) AND e.companyName = :companyName AND e.isDeleted = false")
    Optional<Employees> findByName(@Param("name") String name, @Param("companyName") String companyName);
}
