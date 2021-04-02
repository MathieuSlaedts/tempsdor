package be.tempsdor.tempsdor.services;

import java.util.List;

public interface UserService<DTO, ID> {
    void insert(DTO dto);
    List<DTO> getAll();
    DTO getOneById(ID id);
    DTO update(DTO dto, ID id);
    void delete(ID id);
}
