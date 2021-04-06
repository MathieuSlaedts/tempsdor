package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;

import java.util.List;

public interface RoleService<DTO> {
    List<DTO> getAll() throws ElementsNotFoundException;
    DTO getByName(String name) throws ElementNotFoundException;
}
