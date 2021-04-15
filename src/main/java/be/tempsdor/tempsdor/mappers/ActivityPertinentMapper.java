package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.ActivityPertinentDTO;
import be.tempsdor.tempsdor.entities.Activity;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ActivityPertinentMapper implements Mapper<ActivityPertinentDTO, Activity> {
    private final RoomRepository roomRepository;

    public ActivityPertinentMapper(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public ActivityPertinentDTO toDTO(Activity entity) {
        return entity == null
                ? null
                : ActivityPertinentDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .rooms(entity.getRooms()
                        .stream()
                        .map(r -> r == null ? null : r.getId())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Activity toEntity(ActivityPertinentDTO dto) {
        return dto == null
                ? null
                : Activity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .rooms(dto.getRooms()
                        .stream()
                        .map(r -> r == null ? null : this.roomRepository.findById(r).orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .build();
    }
}
