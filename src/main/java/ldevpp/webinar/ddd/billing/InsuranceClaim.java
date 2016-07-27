package ldevpp.webinar.ddd.billing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ldevpp.webinar.ddd.appointments.Appointment;
import ldevpp.webinar.ddd.doctors.Doctor;
import ldevpp.webinar.ddd.doctors.License;
import ldevpp.webinar.ddd.patients.Insurance;
import ldevpp.webinar.ddd.patients.Patient;
import ldevpp.webinar.ddd.procedures.Procedure;

@Entity
public class InsuranceClaim extends Receivable {

    @ManyToOne
    private License license;

    @ManyToOne
    private Insurance insurance;

    public InsuranceClaim() {
    }

    public InsuranceClaim(LocalDate issueDate, BigDecimal amount, Appointment appointment, License license,
        Insurance insurance) {
        super(issueDate, amount, appointment);
        this.license = license;
        this.insurance = insurance;
    }

    public License getLicense() {
        return license;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public Patient getPatient() {
        return getAppointment().getPatient();
    }

    public Doctor getDoctor() {
        return getAppointment().getDoctor();
    }

    public List<Procedure> getProcedures() {
        return getAppointment().getProcedures();
    }

    @Override
    public String getEntryDescription() {
        return "Insurance claim";
    }
}
