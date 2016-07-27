package ldevpp.webinar.ddd.doctors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ldevpp.webinar.ddd.licensing.LicenseType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class License extends AbstractPersistable<Long> {

    @ManyToOne
    private LicenseType type;
    @ManyToOne
    private Doctor doctor;
    private String number;

    public License() {
    }

    public License(LicenseType type, Doctor doctor, String number) {
        this.type = type;
        this.doctor = doctor;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public LicenseType getType() {
        return type;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDisplayName() {
        return String.format("%s (%s)", number, type.getName());
    }
}
