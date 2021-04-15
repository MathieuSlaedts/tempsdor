package be.tempsdor.tempsdor.entities;

import be.tempsdor.tempsdor.configuration.ApplicationConfiguration;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = ApplicationConfiguration.DB_TABLE_PREFIX + "room")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer capacity;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToMany
    @JoinTable(
            name = ApplicationConfiguration.DB_TABLE_PREFIX + "room_activity",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    Set<Activity> activities;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    Set<Booking> bookings;

    public boolean isAvailable(LocalDate arrival, LocalDate departure) {
        for (Booking booking : this.bookings) {
            LocalDate bookingArrival = booking.getArrival();
            LocalDate bookingDeparture = booking.getDeparture();

            if(arrival.isEqual(bookingArrival))
                return false;

            if(arrival.isAfter(bookingArrival) && arrival.isBefore(bookingDeparture))
                return false;

            if(departure.isAfter(bookingArrival) && departure.isBefore(bookingDeparture))
                return false;

            if(departure.isEqual(bookingDeparture))
                return false;
        }
        return true;
    }
}
