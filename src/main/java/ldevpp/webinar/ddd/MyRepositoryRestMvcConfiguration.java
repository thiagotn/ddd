package ldevpp.webinar.ddd;

import ldevpp.webinar.ddd.patients.Patient;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class MyRepositoryRestMvcConfiguration extends RepositoryRestMvcConfiguration {

    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Patient.class);
    }
}
