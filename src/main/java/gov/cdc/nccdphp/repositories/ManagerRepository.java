package gov.cdc.nccdphp.repositories;

import gov.cdc.nccdphp.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by MCQ1 on 07/14/2016.
 */
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);

    List<Manager> findByActiveTrue();
}
