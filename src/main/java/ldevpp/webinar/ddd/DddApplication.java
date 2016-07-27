package ldevpp.webinar.ddd;

import java.util.GregorianCalendar;

import ldevpp.webinar.ddd.patients.Patient;
import ldevpp.webinar.ddd.patients.PatientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DddApplication {

    private static final Logger log = LoggerFactory.getLogger(DddApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DddApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(PatientRepository repository) {
        return (args) -> {
            log.info("Adding dummy patients...");
            repository.save(new Patient("Jack", "Bauer", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("Chloe", "O'Brian", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("Kim", "Bauer", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("David", "Palmer", new GregorianCalendar(1980, 11, 10).getTime()));
            repository.save(new Patient("Michelle", "Dessler", new GregorianCalendar(1980, 11, 10).getTime()));

            log.info("" + repository.findAll().size() + " patients added.");
        };
    }
}
