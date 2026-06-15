package hotel_booking_app.payment.controllers;

import hotel_booking_app.payment.entities.Payment;
import hotel_booking_app.payment.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestParam("reservationId") UUID reservationId,
                                                  @RequestParam("amount") Double amount) {
        Payment savedPayment = paymentService.processPayment(reservationId, amount);
        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable UUID id) {
        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}