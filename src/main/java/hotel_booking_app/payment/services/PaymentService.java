package hotel_booking_app.payment.services;

import hotel_booking_app.payment.entities.Payment;
import hotel_booking_app.payment.repositories.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment processPayment(UUID reservationId, Double amount) {
        log.info("Processing payment request for reservation ID: {} with amount: {}", reservationId, amount);
        Payment payment = new Payment(reservationId, amount);

        return paymentRepository.save(payment);
    }

    @Transactional(readOnly = true)
    public Optional<Payment> getPaymentById(UUID id) {
        log.info("Retrieving payment details for transaction ID: {}", id);
        return paymentRepository.findById(id);
    }
}