package be.tempsdor.tempsdor.entities;

import be.tempsdor.tempsdor.configuration.ApplicationConfiguration;
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
@Table(name = ApplicationConfiguration.DB_TABLE_PREFIX + "user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String lastname;

    @Column(nullable = false)
    String firstname;

    @Column(unique = true, nullable = false)
    String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = ApplicationConfiguration.DB_TABLE_PREFIX + "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    Set<Room> rooms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
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
