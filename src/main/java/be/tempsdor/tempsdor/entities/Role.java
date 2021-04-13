package be.tempsdor.tempsdor.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "larnak_role")
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
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "larnak_user_role",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> users;
}