package gov.cdc.nccdphp.repositories;

import gov.cdc.nccdphp.domain.Division;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MCQ1 on 07/14/2016.
 */
public interface DivisionRepository extends JpaRepository<Division, Integer> {

     Division findByAbbreviation(String abbreviation);
}
