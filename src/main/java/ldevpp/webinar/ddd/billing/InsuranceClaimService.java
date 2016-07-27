package ldevpp.webinar.ddd.billing;

import java.time.LocalDate;

import ldevpp.webinar.ddd.appointments.Appointment;
import ldevpp.webinar.ddd.doctors.License;
import ldevpp.webinar.ddd.doctors.LicenseService;
import ldevpp.webinar.ddd.patients.Insurance;
import ldevpp.webinar.ddd.patients.InsuranceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InsuranceClaimService {

    @Autowired
    LicenseService licenseService;

    @Autowired
    InsuranceClaimRepository insuranceClaimRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    public InsuranceClaim createInsuranceClaim(Appointment appointment) {
        if (insuranceClaimRepository.findByAppointment(appointment) != null) {
            throw new IllegalArgumentException("The specified appointment already has an insurance claim");
        }

        License license = licenseService.selectBestLicense(appointment.getProcedures(), appointment.getDoctor())
            .orElseThrow(
                () -> new IllegalArgumentException("The doctor is not licensed to perform all the procedures"));

        Insurance insurance = insuranceRepository.findByPatient(appointment.getPatient());
        if (insurance == null) {
            throw new IllegalArgumentException("The patient has no insurance");
        }

        InsuranceClaim claim = new InsuranceClaim(LocalDate.now(), appointment.getTotalFee(), appointment, license,
            insurance);
        return insuranceClaimRepository.saveAndFlush(claim);
    }
}
