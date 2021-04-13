package be.tempsdor.tempsdor.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "larnak_room")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "capacity", nullable = false)
    Integer capacity;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "city", nullable = false)
    String city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToMany
    @JoinTable(
            name = "larnak_room_activity",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    Set<Activity> activities;

    @OneToMany(mappedBy = "room")
    Set<Booking> bookings;
}
