package be.tempsdor.tempsdor.entities;

import be.tempsdor.tempsdor.configuration.ApplicationConfiguration;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = ApplicationConfiguration.DB_TABLE_PREFIX + "booking")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer numberOccupants;

    @Column(nullable = false)
    LocalDate arrival;

    @Column(nullable = false)
    LocalDate departure;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(optional = false)
    @JoinColumn(name= "room_id")
    Room room;
}
