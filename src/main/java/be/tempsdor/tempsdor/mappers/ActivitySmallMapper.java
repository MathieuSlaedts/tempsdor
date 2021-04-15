package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.ActivitySmallDTO;
import be.tempsdor.tempsdor.entities.Activity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ActivitySmallMapper {
    public ActivitySmallDTO toDTO(Activity entity) {
        return ActivitySmallDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .uri(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/activities/"+entity.getId())
                .build();
    }
}
