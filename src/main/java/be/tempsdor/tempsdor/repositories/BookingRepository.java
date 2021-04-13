package be.tempsdor.tempsdor.repositories;

import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.entities.Role;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM Booking b WHERE :user LIKE b.user")
    List<Booking> getAllByUser(@Param("user") User user);

    @Query("SELECT b FROM Booking b JOIN b.room r WHERE :user LIKE r.user")
    List<Booking> getAllByRoomManagedByUser(@Param("user") User user);
}
