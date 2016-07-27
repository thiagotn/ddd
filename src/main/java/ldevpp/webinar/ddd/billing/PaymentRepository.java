package ldevpp.webinar.ddd.billing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByReceivable(Receivable receivable);
}
