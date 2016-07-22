package gov.cdc.nccdphp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by MCQ1 on 07/14/2016.
 */
@Entity
@Data
@ToString(exclude = "deployments")
@EqualsAndHashCode(exclude="deployments")

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @NotNull
    @ManyToOne
    private Division division;

    @NotNull
    @Size(min=2, max=255)
    private String name;

    @Column(unique=true)
    @Size(min=2, max=6)
    private String abbreviation;

    @Column(unique=true)
    @Max(99999)
    private Integer escNumber;

    @ManyToOne
    private Manager manager;

    @OneToMany(mappedBy="project")
    private List<Deployment> deployments;

    //Adds a new Deployment to this project and sets the relationship.
    public void addDeployment(Deployment deployment) {
        if (deployments == null) {
            deployments = new ArrayList<Deployment>();
        }
        this.deployments.add(deployment);
        deployment.setProject(this);
    }
}
