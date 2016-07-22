package gov.cdc.nccdphp.configuration;

/**
 * Created by MCQ1 on 07/14/2016.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();

        //This is needed for the H2 Console application. Not to be used in Production.
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

}