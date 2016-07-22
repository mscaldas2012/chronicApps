package gov.cdc.nccdphp.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by MCQ1 on 07/14/2016.
 */
@Entity
@Data
public class Manager {
    public static final String REGEXP_PHONE_NUMBER = "(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp=REGEXP_PHONE_NUMBER, message="{phoneNumber.invalid}")
    private String phoneNumber;

    private Boolean active = true;

}
