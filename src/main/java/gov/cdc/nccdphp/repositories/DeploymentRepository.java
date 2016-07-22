package gov.cdc.nccdphp.repositories;

import gov.cdc.nccdphp.domain.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by marcelo on 7/18/16.
 */
public interface DeploymentRepository extends JpaRepository<Deployment, Long> {

    List<Deployment>findByProjectId(Long id);

}
