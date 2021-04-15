package be.tempsdor.tempsdor.entities;
import be.tempsdor.tempsdor.configuration.ApplicationConfiguration;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = ApplicationConfiguration.DB_TABLE_PREFIX + "activity")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @Column()
    String description;

    @ManyToMany
    @JoinTable(
        name = ApplicationConfiguration.DB_TABLE_PREFIX + "room_activity",
        joinColumns = @JoinColumn(name = "activity_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id"))
    Set<Room> rooms;
}
