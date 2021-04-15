package be.tempsdor.tempsdor.repositories;

import be.tempsdor.tempsdor.entities.Activity;
import be.tempsdor.tempsdor.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE :activity MEMBER OF r.activities")
    List<Room> getAllByActivity(@Param("activity") Activity activity);
}
