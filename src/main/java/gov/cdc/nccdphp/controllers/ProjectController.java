package gov.cdc.nccdphp.controllers;

import gov.cdc.nccdphp.domain.Deployment;
import gov.cdc.nccdphp.domain.EnumDeploymentLifeCycle;
import gov.cdc.nccdphp.domain.Project;
import gov.cdc.nccdphp.repositories.DeploymentRepository;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import gov.cdc.nccdphp.repositories.ManagerRepository;
import gov.cdc.nccdphp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by MCQ1 on 07/14/2016.
 */
@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DeploymentRepository deploymentRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private DivisionRepository divisionRepository;

    @RequestMapping("/projects")
    public String list(Model model) {
        model.addAttribute("projects", projectRepository.findAllByOrderByDivisionAbbreviation());
        return "projects";
    }

    @RequestMapping("/projects/{division}")
    public String list(@PathVariable String division, Model model) {
        model.addAttribute("projects", projectRepository.findByDivisionAbbreviation(division));
        return "projects";
    }

    @RequestMapping("/project/{id}")
    public String getProjects(@PathVariable Long id, Model model) {
        model.addAttribute("projects", projectRepository.findOne(id));
        return "projectshow";
    }

    @RequestMapping("/project/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("managers", managerRepository.findByActiveTrue());
        model.addAttribute("divisions", divisionRepository.findAll());
        model.addAttribute("deploymentType", EnumDeploymentLifeCycle.values());
        return "projectForm";
    }


    @RequestMapping("/project/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Project project = projectRepository.findById(id);
        model.addAttribute("project", project);
        model.addAttribute("managers", managerRepository.findByActiveTrue());
        model.addAttribute("divisions", divisionRepository.findAll());
        model.addAttribute("deploymentType", EnumDeploymentLifeCycle.values());

        //Eager loading not working with Spring JPA yet... Tried @Query and @EntityGraph... no luck
        project.setDeployments(deploymentRepository.findByProjectId(project.getId()));
        return "projectform";
    }

    @RequestMapping(value="/project", method= RequestMethod.POST)
    public String saveProject(@Valid Project project,  BindingResult bindingResult, Model model) {

        //Check whether a Division was actually selected from the Dropdonw...
        //If not, add an error to be displayed!
        if (project.getDivision() == null || project.getDivision().getId() == null ||  project.getDivision().getId() <= 0)
            bindingResult.addError(new FieldError("project", "division", "Please Select a Division!"));

        //If errors were found, do not save.. reset the form and display the page again!
        if (bindingResult.hasErrors()) {
            model.addAttribute("project", project);
            model.addAttribute("managers", managerRepository.findAll());
            model.addAttribute("divisions", divisionRepository.findAll());
            model.addAttribute("deploymentType", EnumDeploymentLifeCycle.values());
            return "projectform";
        }
        projectRepository.save(project);
        return "redirect:/projects";
    }

    //Deployment Actions:
    @RequestMapping(value="/project/{id}/deployment/new")
    public String newDeployment(@PathVariable Long id, Model model) {
        Deployment deployment = new Deployment();
        Project project = new Project();
        project.setId(id);
        project.addDeployment(deployment);
        model.addAttribute("deployment", deployment);
        return "deploymentform";
    }

    @RequestMapping(value="/project/deployment", method=RequestMethod.POST)
    public String saveDeployment(Deployment deployment) {
        Project proj = projectRepository.findOne(deployment.getProject().getId());
        deployment.setProject(proj);
        deploymentRepository.save(deployment);
        return "redirect:/project/edit/" + proj.getId();
    }

    @RequestMapping(value="/project/{projID}/deployment/edit/{depID}")
    public String editDeployment(@PathVariable Long projID, @PathVariable Long depID, Model model) {
        Project project = projectRepository.findById(projID);
        Optional<Deployment> deployment = project.getDeployments().stream().filter(d -> d.getId().equals(depID)).findFirst();
        model.addAttribute("deployment", deployment);
        return "deploymentform";



    }


}
