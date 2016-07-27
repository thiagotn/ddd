package ldevpp.webinar.ddd.billing;

import ldevpp.webinar.ddd.appointments.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReceivableRepository<E extends Receivable> extends JpaRepository<E, Long> {

    E findByAppointment(Appointment appointment);
}
