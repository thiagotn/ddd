package ldevpp.webinar.ddd.patients;


import java.util.Date;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Patient extends AbstractPersistable<Long> {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Patient() {
    }

    public Patient(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
