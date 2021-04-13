package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.RoomDTO;
import be.tempsdor.tempsdor.DTOs.RoomPertinentDTO;
import be.tempsdor.tempsdor.DTOs.RoomManagerIdOnlyDTO;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.exceptions.MismatchingIdentifersException;

import java.time.LocalDate;
import java.util.List;
public interface RoomService extends CrudService<RoomDTO, RoomPertinentDTO, Integer> {
    List<RoomDTO> getAllWithFullDatas() throws ElementsNotFoundException;
    List<RoomPertinentDTO> getAllByActivity(Long activityId) throws ElementsNotFoundException, ElementNotFoundException;
    Boolean getAvailabilityByDateRange(Integer id, LocalDate arrival, LocalDate departure) throws ElementNotFoundException;
    RoomPertinentDTO updateManagerById(RoomManagerIdOnlyDTO updatedDatas, Integer id) throws ElementNotFoundException, MismatchingIdentifersException;
}