package gov.cdc.nccdphp.repositories;

import gov.cdc.nccdphp.configuration.RepositoryConfiguration;
import gov.cdc.nccdphp.domain.Division;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by MCQ1 on 07/14/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class DivisionRepositoryTest {

    @Autowired
    private DivisionRepository divisionRepository;

    @Before
    public void setUp() throws Exception {

    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateDivision() {
        Division div = new Division();
        div.setName("Office of Smoke and Health");
        div.setAbbreviation("OSH");

        divisionRepository.save(div);
        assertNotNull(div.getId());

        Division osh = divisionRepository.findOne(div.getId());
        assertNotNull(osh);


    }
}