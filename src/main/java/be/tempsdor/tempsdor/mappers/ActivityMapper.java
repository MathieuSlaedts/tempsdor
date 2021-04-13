package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.ActivityDTO;
import be.tempsdor.tempsdor.entities.Activity;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ActivityMapper implements Mapper<ActivityDTO, Activity> {
    private final RoomSmallMapper roomSmallMapper;
    private final RoomRepository roomRepository;

    public ActivityMapper(RoomSmallMapper roomSmallMapper, RoomRepository roomRepository) {
        this.roomSmallMapper = roomSmallMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public ActivityDTO toDTO(Activity activity) {
        return activity == null
                ? null
                : ActivityDTO.builder()
                .id(activity.getId())
                .name(activity.getName())
                .description(activity.getDescription())
                .rooms(Optional.ofNullable(activity.getRooms())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(this.roomSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Activity toEntity(ActivityDTO activityDTO) {
        return activityDTO == null
                ? null
                : Activity.builder()
                .id(activityDTO.getId())
                .name(activityDTO.getName())
                .description(activityDTO.getDescription())
                .rooms(Optional.ofNullable(activityDTO.getRooms())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(a->this.roomRepository.findById(a.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
