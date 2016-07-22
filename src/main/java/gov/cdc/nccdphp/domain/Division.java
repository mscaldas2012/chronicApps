package gov.cdc.nccdphp.domain;

import lombok.Data;

import javax.persistence.*;


/**
 * Created by MCQ1 on 07/14/2016.
 */
@Entity
@Data
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @Column(unique=true)
    private String name;
    @Column(unique=true)
    private String abbreviation;


}
