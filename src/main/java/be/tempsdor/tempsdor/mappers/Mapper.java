package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;

public interface Mapper<DTO, ENTITY> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
