package ldevpp.webinar.ddd.patients;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "patients")
@Controller
@RestController
@ExposesResourceFor(Patient.class)
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private EntityLinks entityLinks;

    @ApiOperation(value = "Lista todos os pacientes", response = Patient.class)
    @RequestMapping(path = "/patients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resources<Patient>> showPatients() {
        Resources<Patient> resources = new Resources<Patient>(this.repository.findAll());
        resources.add(this.entityLinks.linkToCollectionResource(Patient.class));
        return new ResponseEntity<Resources<Patient>>(resources, HttpStatus.OK);
    }

    @ApiOperation(value = "Consulta paciente por Id")
    @RequestMapping(path = "/patients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Patient>> showPatient(@PathVariable Long id) {
        Resource<Patient> resource = new Resource<Patient>(this.repository.findOne(id));
        resource.add(this.entityLinks.linkToSingleResource(Patient.class, id));
        return new ResponseEntity<Resource<Patient>>(resource, HttpStatus.OK);
    }
}
