package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.RoomDTO;
import be.tempsdor.tempsdor.DTOs.RoomPertinentDTO;
import be.tempsdor.tempsdor.DTOs.RoomManagerIdOnlyDTO;
import be.tempsdor.tempsdor.entities.Activity;
import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.exceptions.MismatchingIdentifersException;
import be.tempsdor.tempsdor.mappers.RoomMapper;
import be.tempsdor.tempsdor.mappers.RoomPertinentMapper;
import be.tempsdor.tempsdor.repositories.ActivityRepository;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomPertinentMapper roomPertinenMapper;
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper, RoomPertinentMapper roomPertinenMapper, EntityManager entityManager, UserRepository userRepository, ActivityRepository activityRepository) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
        this.roomPertinenMapper = roomPertinenMapper;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    @Transactional
    public RoomPertinentDTO add(RoomPertinentDTO toAdd) throws ElementAlreadyExistsException, ElementNotFoundException {
        if(toAdd == null)
            throw new IllegalArgumentException();

       if(this.roomRepository.existsById(toAdd.getId()))
           throw new ElementAlreadyExistsException("id", toAdd.getId().toString());

       Room room = this.roomRepository.saveAndFlush(
               this.roomPertinenMapper.toEntity(toAdd));

        this.entityManager.refresh(
                this.entityManager.merge(room));

        return this.roomPertinenMapper.toDTO(
                this.roomRepository.findById(room.getId())
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public List<RoomPertinentDTO> getAll() throws ElementsNotFoundException {
        List<Room> all = this.roomRepository.findAll();

        if(all.isEmpty())
            throw new ElementsNotFoundException();

        return all
                .stream()
                .map(this.roomPertinenMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomPertinentDTO getOneById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        return this.roomRepository
                .findById(id)
                .map(this.roomPertinenMapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    @Transactional
    public RoomPertinentDTO update(RoomPertinentDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(updatedDatas.getId() != id)
            throw new MismatchingIdentifersException();

        Room room = this.roomRepository.saveAndFlush(
                this.roomPertinenMapper.toEntity(updatedDatas));

        this.entityManager.refresh(
                this.entityManager.merge(room));

        return this.roomPertinenMapper.toDTO(
                this.roomRepository.findById(room.getId())
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public void deleteById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        if(!this.roomRepository.existsById(id))
            throw new ElementNotFoundException();

        this.roomRepository.deleteById(id);
    }

    @Override
    public List<RoomDTO> getAllWithFullDatas() throws ElementsNotFoundException {
        List<Room> all = this.roomRepository.findAll();

        if(all.isEmpty())
            throw new ElementsNotFoundException();

        return all
                .stream()
                .map(this.roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomPertinentDTO> getAllByActivity(Long activityId) throws ElementsNotFoundException, ElementNotFoundException {
        if(activityId == null)
            throw new IllegalArgumentException();

        Activity activity = this.activityRepository.findById(activityId)
                .orElseThrow(ElementNotFoundException::new);

        List<Room> all = this.roomRepository.getAllByActivity(activity);

        if(all.isEmpty())
            throw new ElementsNotFoundException();

        return all
                .stream()
                .map(this.roomPertinenMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean getAvailabilityByDateRange(Long id, LocalDate arrival, LocalDate departure) throws ElementNotFoundException {
        if(id == null || arrival == null || departure == null)
            throw new IllegalArgumentException();

        Room room = this.roomRepository
                .findById(id)
                .orElseThrow(ElementNotFoundException::new);

        return room.isAvailable(arrival, departure);
    }

    @Override
    public RoomPertinentDTO updateManagerById(RoomManagerIdOnlyDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(updatedDatas.getId() != id)
            throw new MismatchingIdentifersException();

        Room room = this.roomRepository
                .findById(id)
                .orElseThrow(ElementNotFoundException::new);
        room.setUser(this.userRepository.findById(updatedDatas.getManager()).orElse(null));

        return this.roomPertinenMapper.toDTO(
                this.roomRepository.save(room));
    }
}
