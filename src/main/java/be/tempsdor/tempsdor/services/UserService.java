package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;

import java.util.List;

public interface UserService<DTO, ID> {
    DTO insert(DTO dto) throws ElementAlreadyExistsException;
    List<DTO> getAll() throws ElementsNotFoundException;
    DTO getOneById(ID id);
    DTO update(DTO dto, ID id);
    void delete(ID id);
}
