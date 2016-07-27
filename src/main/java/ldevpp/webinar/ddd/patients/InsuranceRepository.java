package ldevpp.webinar.ddd.patients;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    Insurance findByPatient(Patient patient);

}
