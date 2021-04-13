package be.tempsdor.tempsdor;

import be.tempsdor.tempsdor.entities.*;
import be.tempsdor.tempsdor.repositories.*;
import be.tempsdor.tempsdor.utils.Utils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;
    private final ActivityRepository activityRepository;
    private final BookingRepository bookingRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(EntityManager entityManager, UserRepository userRepository, RoleRepository roleRepository, RoomRepository roomRepository, ActivityRepository activityRepository, BookingRepository bookingRepository, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roomRepository = roomRepository;
        this.activityRepository = activityRepository;
        this.bookingRepository = bookingRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;

        Role role1 = createRoleIfNotFound("ROLE_ADMIN");
        Role role2 = createRoleIfNotFound("ROLE_USER");

        User user1 = createUserIfNotFound(
                "john_doe",
                new HashSet<>(Arrays.asList(role1, role2)));

        User user2 = createUserIfNotFound(
                "jane_doe",
                new HashSet<>(Arrays.asList(role1)));

        User user3 = createUserIfNotFound(
                "jonnie_doe",
                new HashSet<>(Arrays.asList(role2)));

        User user4 = createUserIfNotFound(
                "janie_doe",
                null);

        Room room1 = createRoomIfNotFound(
                1,
                4,
                "Rue de Moscou 34",
                "Saint-Gilles",
                user1);

        Room room2 = createRoomIfNotFound(
                2,
                3,
                "Avenue Louis Bertrand 23",
                "Schaerbeek",
                user3);

        Room room3 = createRoomIfNotFound(
                3,
                2,
                "Chaussée d'Alsemberg 172",
                "Forest",
                user1);

        Room room4 = createRoomIfNotFound(
                4,
                2,
                "Chaussée d’Ixelles 355",
                "Ixelles",
                user4);

        Activity activity1 = createActivityIfNotFound(
                "Cinema",
                "Aller voir un film sur grand écran.",
                new HashSet<>(Arrays.asList(room1, room4)));

        Activity activity2 = createActivityIfNotFound(
                "Musée",
                "Faire une sortie culturelle.",
                new HashSet<>(Arrays.asList(room3, room2)));

        Activity activity3 = createActivityIfNotFound(
                "Bar",
                "Boire quelques bonnes bières.",
                new HashSet<>(Collections.singletonList(room1)));

        Activity activity4 = createActivityIfNotFound(
                "Centre sportif",
                "Se défouler.",
                new HashSet<>(Collections.singletonList(room1)));

        Booking booking1 = createBookingIfNotFound(
                1,
                3,
                LocalDateTime.of(2021, 4, 10, 8, 0),
                LocalDateTime.of(2021, 4, 12, 8, 0),
                user1,
                room2);

        Booking booking2 = createBookingIfNotFound(
                2,
                1,
                LocalDateTime.of(2021, 4, 20, 8, 0),
                LocalDateTime.of(2021, 4, 23, 8, 0),
                user2,
                room2);

        Booking booking3 = createBookingIfNotFound(
                3,
                4,
                LocalDateTime.of(2021, 4, 10, 8, 0),
                LocalDateTime.of(2021, 4, 11, 8, 0),
                user1,
                room4);

        Booking booking4 = createBookingIfNotFound(
                4,
                2,
                LocalDateTime.of(2021, 4, 20, 8, 0),
                LocalDateTime.of(2021, 4, 24, 8, 0),
                user4,
                room1);

        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(String name) {
        if (!this.roleRepository.existsByName(name)) {
            this.roleRepository.saveAndFlush(Role.builder()
                    .id(0)
                    .name(name)
                    .build());
        }
        return this.roleRepository.findByName(name);
    }

    @Transactional
    private User createUserIfNotFound(String username, Set<Role> roles) {
        if(!this.userRepository.existsByUsername(username)) {
            this.userRepository.saveAndFlush(User.builder()
                    .id(0)
                    .username(username)
                    .firstname(username.split("_")[0].substring(0, 1).toUpperCase() + username.split("_")[0].substring(1))
                    .lastname(username.split("_")[1].substring(0, 1).toUpperCase() + username.split("_")[1].substring(1))
                    .password(passwordEncoder.encode("passwordByDefault"))
                    .email(username.split("_")[0] + "-" + username.split("_")[1] + "@email.com")
                    .roles(roles)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build());
        }
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    private Room createRoomIfNotFound(Integer id, int capacity, String address, String city, User user) {
        if (!this.roomRepository.existsById(id)) {
            this.roomRepository.saveAndFlush(Room.builder()
                    .id(id)
                    .capacity(capacity)
                    .address(address)
                    .city(city)
                    .user(user)
                    .build());
        }
        return this.roomRepository.findById(id).orElse(null);
    }

    @Transactional
    private Activity createActivityIfNotFound(String name, String description, Set<Room> rooms) {
        if (!this.activityRepository.existsByName(name)) {
            this.activityRepository.saveAndFlush(
                    Activity.builder()
                            .id(0L)
                            .name(name)
                            .description(description)
                            .rooms(rooms)
                            .build());
        }
        return this.activityRepository.findByName(name).orElse(null);
    }

    @Transactional
    private Booking createBookingIfNotFound(Integer id, int numberOccupants, LocalDateTime arrivalDatetime, LocalDateTime departureDatetime, User user, Room room) {
        if (!this.bookingRepository.existsById(id)) {
            this.bookingRepository.saveAndFlush(
                    Booking.builder()
                            .id(id)
                            .numberOccupants(numberOccupants)
                            .arrivalDatetime(arrivalDatetime)
                            .departureDatetime(departureDatetime)
                            .user(user)
                            .room(room)
                            .build());
        }
        return this.bookingRepository.findById(id).orElse(null);
    }
}
