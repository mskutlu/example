package com.example.tenancy;

import io.github.jhipster.config.JHipsterConstants;
import liquibase.integration.spring.MultiTenantSpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class TenantDatabaseConfiguration
{
    private final Logger log = LoggerFactory.getLogger(TenantDatabaseConfiguration.class);

    private final Environment env;

    public TenantDatabaseConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public MultiTenantSpringLiquibase liquibaseMt(DataSource dataSource) throws SQLException
    {
        MultiTenantSpringLiquibase multiTenantSpringLiquibase = new MultiTenantSpringLiquibase();
        multiTenantSpringLiquibase.setDataSource(dataSource);

        //get all existing tenantsID
        List<String> tenantsSchemas = new ArrayList<>();

        tenantsSchemas.add("example");

        multiTenantSpringLiquibase.setSchemas(tenantsSchemas);
        multiTenantSpringLiquibase.setChangeLog("classpath:config/liquibase/master.xml");
        multiTenantSpringLiquibase.setContexts("development, production");
        if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_NO_LIQUIBASE)) {
            multiTenantSpringLiquibase.setShouldRun(false);
        } else {
            multiTenantSpringLiquibase.setShouldRun(true);
            log.debug("Configuring Liquibase");
        }

        return multiTenantSpringLiquibase;
    }
}
