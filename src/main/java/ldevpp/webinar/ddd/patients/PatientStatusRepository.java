package ldevpp.webinar.ddd.patients;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientStatusRepository extends JpaRepository<PatientStatus, Long> {

}
