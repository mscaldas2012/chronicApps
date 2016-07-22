package gov.cdc.nccdphp.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by MCQ1 on 07/14/2016.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"gov.cdc.nccdphp.domain"})
@EnableJpaRepositories(basePackages = {"gov.cdc.nccdphp.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
