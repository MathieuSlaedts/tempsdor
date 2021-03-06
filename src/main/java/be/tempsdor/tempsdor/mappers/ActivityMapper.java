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
    public ActivityDTO toDTO(Activity entity) {
        return entity == null
                ? null
                : ActivityDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .rooms(Optional.ofNullable(entity.getRooms())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(this.roomSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Activity toEntity(ActivityDTO dto) {
        return dto == null
                ? null
                : Activity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .rooms(Optional.ofNullable(dto.getRooms())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(a->this.roomRepository.findById(a.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
