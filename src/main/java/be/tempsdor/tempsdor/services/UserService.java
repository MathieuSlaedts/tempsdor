package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;

import java.util.List;

public interface UserService<DTO, PERTINENT_DTO, ID> {
    DTO add(DTO dto) throws ElementAlreadyExistsException;
    List<PERTINENT_DTO> getAll() throws ElementsNotFoundException;
    DTO getOneById(ID id);
    DTO update(DTO dto, ID id);
    void delete(ID id);
}
