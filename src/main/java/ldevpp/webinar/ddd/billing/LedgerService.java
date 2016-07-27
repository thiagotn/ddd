package ldevpp.webinar.ddd.billing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ldevpp.webinar.ddd.appointments.Appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LedgerService {

    @Autowired
    InsuranceClaimRepository insuranceClaimRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public Ledger getLedgerForAppointment(Appointment appointment) {
        List<LedgerEntry> entries = new ArrayList<>();

        Optional<InsuranceClaim> insuranceClaim = Optional
            .ofNullable(insuranceClaimRepository.findByAppointment(appointment));
        insuranceClaim.ifPresent(entries::add);
        insuranceClaim.map(paymentRepository::findByReceivable).ifPresent(entries::add);

        Optional<Invoice> invoice = Optional.ofNullable(invoiceRepository.findByAppointment(appointment));
        invoice.ifPresent(entries::add);
        invoice.map(paymentRepository::findByReceivable).ifPresent(entries::add);

        Collections.sort(entries);
        return new Ledger(appointment.getTotalFee(), entries);
    }
}
