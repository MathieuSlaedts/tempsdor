package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.DTOs.BookingPertinentDTO;
import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.*;
import be.tempsdor.tempsdor.mappers.BookingMapper;
import be.tempsdor.tempsdor.mappers.BookingPertinentMapper;
import be.tempsdor.tempsdor.repositories.BookingRepository;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingMapper bookingMapper;
    private final BookingPertinentMapper bookingPertinentMapper;
    private final BookingRepository bookingRepository;
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingPertinentMapper bookingPertinentMapper, BookingRepository bookingRepository, EntityManager entityManager, UserRepository userRepository, RoomRepository roomRepository) {
        this.bookingMapper = bookingMapper;
        this.bookingPertinentMapper = bookingPertinentMapper;
        this.bookingRepository = bookingRepository;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public BookingPertinentDTO add(BookingPertinentDTO toAdd) throws ElementAlreadyExistsException, ElementNotFoundException, OwnRoomBookingException, RoomUnavailableException {
        if(toAdd == null)
            throw new IllegalArgumentException();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Room room = this.roomRepository.findById(toAdd.getRoom())
                .orElseThrow(ElementNotFoundException::new);

        if(room.getUser().getUsername().equals(authentication.getName()))
            throw new OwnRoomBookingException();

        if(!room.isAvailable(toAdd.getArrival(), toAdd.getDeparture()))
            throw new RoomUnavailableException();

        if(this.bookingRepository.existsById(toAdd.getId()))
            throw new ElementAlreadyExistsException("id", toAdd.getId().toString());

        Booking booking = this.bookingPertinentMapper.toEntity(toAdd);

        return this.bookingPertinentMapper.toDTO(
                this.bookingRepository.saveAndFlush(booking));
    }

    @Override
    public List<BookingPertinentDTO> getAll() throws ElementsNotFoundException {
        List<Booking> bookings = this.bookingRepository.findAll();

        if(bookings.isEmpty())
            throw new ElementsNotFoundException();

        return bookings
                .stream()
                .map(this.bookingPertinentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingPertinentDTO getOneById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        return this.bookingPertinentMapper.toDTO(
                this.bookingRepository.findById(id)
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    @Transactional
    public BookingPertinentDTO update(BookingPertinentDTO updatedDatas, Long id) throws ElementNotFoundException, OwnRoomBookingException, RoomUnavailableException, MismatchingIdentifersException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(updatedDatas.getId() != id)
            throw new MismatchingIdentifersException();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Room room = this.roomRepository.findById(updatedDatas.getRoom())
                .orElseThrow(ElementNotFoundException::new);

        if(room.getUser().getUsername().equals(authentication.getName()))
            throw new OwnRoomBookingException();

        if(!room.isAvailable(updatedDatas.getArrival(), updatedDatas.getDeparture()))
            throw new RoomUnavailableException();

        if(!bookingRepository.existsById(id))
            throw new ElementNotFoundException();

        Booking booking = this.bookingPertinentMapper.toEntity(updatedDatas);

        return this.bookingPertinentMapper.toDTO(
                this.bookingRepository.saveAndFlush(booking));
    }

    @Override
    public void deleteById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        if(!this.bookingRepository.existsById(id))
            throw new ElementNotFoundException();

        this.bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingPertinentDTO> getAllByUser(Long userId) throws ElementNotFoundException, ElementsNotFoundException {
        if(userId == null)
            throw new IllegalArgumentException();

        User user = this.userRepository.findById(userId)
                .orElseThrow(ElementNotFoundException::new);

        List<Booking> all = this.bookingRepository.getAllByUser(user);

        if(all == null)
            throw new ElementsNotFoundException();

        return all
                .stream()
                .map(this.bookingPertinentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingPertinentDTO> getAllByRoomManagedByUser(Long userId) throws ElementNotFoundException, ElementsNotFoundException {
        if(userId == null)
            throw new IllegalArgumentException();

        User user = this.userRepository.findById(userId)
                .orElseThrow(ElementNotFoundException::new);

        List<Booking> all = this.bookingRepository.getAllByRoomManagedByUser(user);

        if(all == null)
            throw new ElementsNotFoundException();

        return all
                .stream()
                .map(this.bookingPertinentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getAllWithFullDatas() throws ElementsNotFoundException {
        List<Booking> bookings = this.bookingRepository.findAll();

        if(bookings.isEmpty())
            throw new ElementsNotFoundException();

        return bookings
                .stream()
                .map(this.bookingMapper::toDTO)
                .collect(Collectors.toList());
    }
}
