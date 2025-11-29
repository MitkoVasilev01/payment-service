package hotel_booking_app.payment.controllers;

import hotel_booking_app.payment.entities.Payment;
import hotel_booking_app.payment.repositories.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Endpoint 1: Създаване на плащане (ще се вика от Main App)
    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestParam("reservationId") UUID reservationId,
                                                  @RequestParam("amount") Double amount) {

        System.out.println("Получена заявка за плащане за резервация: " + reservationId);

        Payment payment = new Payment(reservationId, amount);
        Payment savedPayment = paymentRepository.save(payment);

        return ResponseEntity.ok(savedPayment);
    }

    // Endpoint 2: Взимане на детайли (Изискване за GET endpoint)
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable UUID id) {
        return paymentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}