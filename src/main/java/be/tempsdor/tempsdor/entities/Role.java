package be.tempsdor.tempsdor.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_role")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", unique = true, nullable = false)
    String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    Set<User> users;
}