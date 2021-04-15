package be.tempsdor.tempsdor.entities;

import be.tempsdor.tempsdor.configuration.ApplicationConfiguration;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = ApplicationConfiguration.DB_TABLE_PREFIX + "role")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "larnak_user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> users;
}