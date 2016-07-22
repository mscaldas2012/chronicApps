package gov.cdc.nccdphp.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by MCQ1 on 07/14/2016.
 */
@Entity
@Data
public class Deployment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name="PROJECT_ID", nullable=false)
    private Project project = new Project();

    @NotNull
    private EnumEnvironmentName environment; //the Type of environment - DEV, QA, PROD, LAB, etc
    private String envOtherDescription;

    private EnumDeploymentLifeCycle lifecycle; //Permanent/Official or Temporary /
    private Date dueDate; // If lifecycle is temporary, when is this supposed to be cleaned up?
    @NotNull
    private String url;

    //what version of the project is
    private String projectVersion;

}

