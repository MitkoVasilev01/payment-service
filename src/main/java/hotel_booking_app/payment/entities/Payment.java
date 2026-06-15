package hotel_booking_app.payment.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID reservationId;

    private Double amount;

    private LocalDateTime paymentDate;
    public Payment() {}

    public Payment(UUID reservationId, Double amount) {
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentDate = LocalDateTime.now();
    }

}

