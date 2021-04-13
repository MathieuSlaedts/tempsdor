package be.tempsdor.tempsdor.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "larnak_user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User implements UserDetails {
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "larnak_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    @OneToMany(mappedBy = "user")
    Set<Room> rooms;

    @OneToMany(mappedBy = "user")
    Set<Booking> bookings;

    Boolean accountNonExpired;
    Boolean accountNonLocked;
    Boolean credentialsNonExpired;
    Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roles == null)
            return null;
        return this.roles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
