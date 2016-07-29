package ldevpp.webinar.ddd.patients;


import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Patient extends AbstractPersistable<Long> {

    @Email(message = "{email.error.message}")
    @NotBlank(message = "{notblank.error.message}") 
    private String email;

    @NotBlank(message = "{notblank.error.message}") @Size(max = 100)
    private String firstName;

    @NotBlank(message = "{notblank.error.message}") @Size(max = 100)
    private String lastName;

    @Past(message = "{past.error.message}")
    @NotNull(message = "{notblank.error.message}") 
    private Date dateOfBirth;

    public Patient() {
    }

    public Patient(String email, String firstName, String lastName, Date dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
