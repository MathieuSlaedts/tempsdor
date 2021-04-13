package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;

import java.util.List;

public interface CrudService<FORM, DTO, ID> {
    DTO add(FORM form) throws ElementAlreadyExistsException, ElementNotFoundException;
    List<DTO> getAll() throws ElementsNotFoundException;
    DTO getOneById(ID id) throws ElementNotFoundException;
    DTO update(FORM form, ID id) throws ElementNotFoundException;
    void deleteById(ID id) throws ElementNotFoundException;
}