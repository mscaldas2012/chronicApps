package gov.cdc.nccdphp.repositories;

import gov.cdc.nccdphp.configuration.RepositoryConfiguration;
import gov.cdc.nccdphp.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by marcelo on 7/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DivisionRepository divisionRepo;
    @Autowired
    private ManagerRepository managerRepo;
    @Autowired
    private DeploymentRepository deploymentRepositorty;


    private void saveDeployment() throws Exception {

        Division OSH = divisionRepo.findByAbbreviation("OSH");
        Manager jeniffer = managerRepo.findByEmail("jwe8@cdc.gov");

        Project project = new Project();
        project.setName("Test");
        project.setAbbreviation("TST");
        project.setDivision(OSH);
        project.setManager(jeniffer);
        project.setEscNumber(1234);
        projectRepository.save(project);


        Deployment deployment = new Deployment();
        deployment.setProject(project);
        deployment.setDueDate(new Date());
        deployment.setEnvironment(EnumEnvironmentName.DEV);
        deployment.setProjectVersion("1.0");
        deployment.setUrl("http://test.com");
        deploymentRepositorty.save(deployment);

    }
    @Test
    public void findByDivisionAbbreviation() throws Exception {
        List<Project> projByDivision = projectRepository.findByDivisionAbbreviation("OSH");
        System.out.println(projByDivision.size());
    }

    @Test
    public void testEagerLoading() throws Exception {
        this.saveDeployment();
        Project p = projectRepository.findById(1l);
        assertNotNull(p);
        assertNotNull(p.getDeployments());
        assertTrue(p.getDeployments().size() > 0);
        for (Deployment d: p.getDeployments()) {
            System.out.println("d = " + d);
        }

    }

}