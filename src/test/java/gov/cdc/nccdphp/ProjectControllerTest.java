package gov.cdc.nccdphp;

import gov.cdc.nccdphp.controllers.ProjectController;
import gov.cdc.nccdphp.domain.*;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import gov.cdc.nccdphp.repositories.ManagerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Date;

/**
 * Created by marcelo on 7/18/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChronicAppsApplication.class)
@WebAppConfiguration
public class ProjectControllerTest {

    @Autowired
    private ProjectController projectController;
    @Autowired
    private DivisionRepository divisionRepo;
    @Autowired
    private ManagerRepository managerRepo;

    @Test
    public void saveDeployment() throws Exception {

        Division OSH = divisionRepo.findByAbbreviation("OSH");
        Manager jeniffer = managerRepo.findByEmail("jwe8@cdc.gov");

        Project project = new Project();
        project.setName("Test");
        project.setAbbreviation("TST");
        project.setDivision(OSH);
        project.setManager(jeniffer);
        project.setEscNumber(1234);
        BindingResult bindingResult = new BeanPropertyBindingResult(projectController, "command");

        projectController.saveProject(project, bindingResult, null);

        System.out.println("project.getId() = " + project.getId());

        Deployment deployment = new Deployment();
        deployment.setProject(project);
        deployment.setDueDate(new Date());
        deployment.setEnvironment(EnumEnvironmentName.TEST);
        deployment.setProjectVersion("1.0");
        deployment.setUrl("http://test.com");

        projectController.saveDeployment(deployment);
        System.out.println("deployment.id = " + deployment.getId());

    }

}