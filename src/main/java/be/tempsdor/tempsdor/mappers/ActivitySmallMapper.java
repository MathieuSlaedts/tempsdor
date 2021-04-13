package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.ActivitySmallDTO;
import be.tempsdor.tempsdor.entities.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivitySmallMapper {
    public ActivitySmallDTO toDTO(Activity activity) {
        return ActivitySmallDTO.builder()
                .id(activity.getId())
                .name(activity.getName())
                .build();
    }
}
