package ldevpp.webinar.ddd;

import static springfox.documentation.builders.PathSelectors.any;

import java.util.GregorianCalendar;

import ldevpp.webinar.ddd.patients.Patient;
import ldevpp.webinar.ddd.patients.PatientRepository;
import ldevpp.webinar.ddd.patients.PatientStatus;
import ldevpp.webinar.ddd.patients.PatientStatusRepository;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(MyRepositoryRestMvcConfiguration.class)
public class DddApplication {

    private static final Logger log = LoggerFactory.getLogger(DddApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DddApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(PatientRepository repository, PatientStatusRepository patientStatusRepository) {
        return (args) -> {
            log.info("Adding dummy patients...");
            repository.save(new Patient("jackbauer@gmail.com", "Jack", "Bauer", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("chloe@gmail.com", "Chloe", "O'Brian", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("kim@gmail.com", "Kim", "Bauer", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("david@gmail.com", "David", "Palmer", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("michelledressler@gmail.com", "Michelle", "Dessler", new GregorianCalendar(1980, 11, 10).getTime()));

            patientStatusRepository.save(new PatientStatus("Rehab", "These are long-term facilities (free-standing or a distinct unit within a hospital) that specialize in rehabilitative care for patients including, physical therapy, occupational therapy, and assistance with activities of daily living."));
            patientStatusRepository.save(new PatientStatus("Observation", "These hospital patients are neither inpatient nor outpatient."));

            log.info("" + repository.findAll().size() + " patients added.");
        };
    }

    @Bean
    public RoutesBuilder myRouter() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("timer://?fixedRate=true").
                    delay(10000).
                to("http://localhost:8080/patients/1/status").
                    unmarshal().
                    json(JsonLibrary.Jackson, PatientStatus.class).
                process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            PatientStatus patientStatus = exchange.getIn().getBody(PatientStatus.class);
                            log.info("[Async] Consulta de status de Paciente: {}", patientStatus);
                            // Faz alguma coisa, como:
                            // Chama um servico e atualiza o status...
                        }
                });
            }
        };
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("")
                .apiInfo(apiInfo())
                .select()
                .paths(any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API de Gerenciamento de Consultório Odontológico")
                .description("API de Gerenciamento de Consultório Odontológico, para consulta de pacientes, doutores, agendamentos e todo o ciclo de consultas de faturamento da clínica.")
                .termsOfServiceUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact("l-dev-pp")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0")
                .build();
    }
}
