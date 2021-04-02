package be.tempsdor.tempsdor.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "lastname", nullable = false)
    String lastname;

    @Column(name = "firstname", nullable = false)
    String firstname;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
