package be.tempsdor.tempsdor.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "larnak_booking")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "number_occupants", nullable = false)
    Integer numberOccupants;

    @Column(name = "arrival_datetime", nullable = false)
    LocalDateTime arrivalDatetime;

    @Column(name = "departure_datetime", nullable = false)
    LocalDateTime departureDatetime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name= "room_id")
    Room room;
}
