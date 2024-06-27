package it.portfolio.hr.humanResource.repositories;

import it.portfolio.hr.humanResource.entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    @Query("SELECT i FROM Interview i WHERE i.startTime = :startTime AND i.companyName = :companyName AND i.isDeleted = false")
    Optional<Interview> findByStartTime(@Param("startTime") String startTime, @Param("companyName") String companyName);

    @Query("SELECT i FROM Interview i WHERE i.interviewDate = :interviewDate AND i.companyName = :companyName AND i.isDeleted = false")
    Optional<Interview> findByInterviewDate(@Param("interviewDate") String interviewDate, @Param("companyName") String companyName);

    @Query("SELECT i FROM Interview i WHERE i.id = :id AND i.companyName = :companyName AND i.isDeleted = false")
    Optional<Interview> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT i FROM Interview i WHERE  i.companyName = :companyName AND i.isDeleted = false")
    List<Interview> findAll(@Param("companyName") String companyName);


}
