package be.tempsdor.tempsdor.mappers;

public interface Mapper<DTO, ENTITY> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
