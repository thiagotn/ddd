package ldevpp.webinar.ddd.patients;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class PatientResourceAssembler extends ResourceAssemblerSupport<Patient, Resource> {

    public PatientResourceAssembler() {
        super(PatientController.class, Resource.class);
    }

    @Override
    public Resource toResource(Patient patient) {
        return new Resource<Patient>(patient, linkTo(PatientController.class).slash(patient.getId()).withSelfRel());
    }
}
