package gov.cdc.nccdphp.repositories;

import gov.cdc.nccdphp.domain.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by MCQ1 on 07/14/2016.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.deployments where p.id = :id")
    Project findById(@Param("id") Long id);

    List<Project> findAllByOrderByDivisionAbbreviation();
    List<Project> findByDivisionAbbreviation(String abbreviation);
}
