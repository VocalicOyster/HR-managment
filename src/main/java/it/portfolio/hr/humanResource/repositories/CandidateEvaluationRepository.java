package it.portfolio.hr.humanResource.repositories;
import it.portfolio.hr.humanResource.entities.CandidateEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateEvaluationRepository extends JpaRepository<CandidateEvaluations, Long> {

    @Query("SELECT c FROM CandidateEvaluations c WHERE c.id = :id AND c.companyName = :companyName AND c.isDeleted = false")
    Optional<CandidateEvaluations> findById(@Param("id") Long id, @Param("companyName") String companyName);

    @Query("SELECT c FROM CandidateEvaluations c WHERE c.companyName = :companyName AND c.isDeleted = false")
    List<CandidateEvaluations> findAll(@Param("companyName") String companyName);

}
