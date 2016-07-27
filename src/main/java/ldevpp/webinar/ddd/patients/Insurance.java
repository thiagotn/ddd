package ldevpp.webinar.ddd.patients;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ldevpp.webinar.ddd.insurance.InsuranceCompany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Insurance extends AbstractPersistable<Long> {

    @ManyToOne
    private InsuranceCompany insuranceCompany;
    @OneToOne
    private Patient patient;
    private String number;

    public Insurance() {
    }

    public Insurance(InsuranceCompany insuranceCompany, Patient patient, String number) {
        this.insuranceCompany = insuranceCompany;
        this.patient = patient;
        this.number = number;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getNumber() {
        return number;
    }

    public String getDisplayName() {
        return String.format("%s (%s)", number, insuranceCompany.getName());
    }
}
