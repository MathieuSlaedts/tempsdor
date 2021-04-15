package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.exceptions.*;

import java.util.List;

public interface CrudService<DTO_IN, DTO_OUT, ID> {
    DTO_OUT add(DTO_IN dto) throws ElementAlreadyExistsException, ElementNotFoundException, OwnRoomBookingException, RoomUnavailableException;
    List<DTO_OUT> getAll() throws ElementsNotFoundException;
    DTO_OUT getOneById(ID id) throws ElementNotFoundException;
    DTO_OUT update(DTO_IN dto, ID id) throws ElementNotFoundException, OwnRoomBookingException, RoomUnavailableException, MismatchingIdentifersException;
    void deleteById(ID id) throws ElementNotFoundException;
}