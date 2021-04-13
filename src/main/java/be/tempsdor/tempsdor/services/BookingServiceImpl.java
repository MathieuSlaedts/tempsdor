package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.mappers.BookingMapper;
import be.tempsdor.tempsdor.repositories.BookingRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingRepository bookingRepository, EntityManager entityManager, UserRepository userRepository) {
        this.bookingMapper = bookingMapper;
        this.bookingRepository = bookingRepository;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public BookingDTO add(BookingDTO toAdd) throws ElementAlreadyExistsException, ElementNotFoundException {
        if(toAdd == null)
            throw new IllegalArgumentException();

        if(this.bookingRepository.existsById(toAdd.getId()))
            throw new ElementAlreadyExistsException("id", toAdd.getId().toString());

        Booking booking = this.bookingRepository.saveAndFlush(
                this.bookingMapper.toEntity(toAdd));

        this.entityManager.refresh(
                this.entityManager.merge(booking));

        return this.bookingMapper.toDTO(
                this.bookingRepository.findById(booking.getId())
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public List<BookingDTO> getAll() throws ElementsNotFoundException {
        List<Booking> bookings = this.bookingRepository.findAll();

        if(bookings.isEmpty())
            throw new ElementsNotFoundException();

        return bookings
                .stream()
                .map(this.bookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getOneById(Integer id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        return this.bookingMapper.toDTO(
                this.bookingRepository.findById(id)
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    @Transactional
    public BookingDTO update(BookingDTO updatedDatas, Integer id) throws ElementNotFoundException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(!bookingRepository.existsById(id))
            throw new ElementNotFoundException();

        Booking booking = this.bookingRepository.saveAndFlush(
                this.bookingMapper.toEntity(updatedDatas));

        this.entityManager.refresh(
                this.entityManager.merge(booking));

        return this.bookingMapper.toDTO(
                this.bookingRepository.findById(booking.getId())
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public void deleteById(Integer id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        if(!this.bookingRepository.existsById(id))
            throw new ElementNotFoundException();

        this.bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingDTO> getAllByUser(Integer userId) throws ElementNotFoundException, ElementsNotFoundException {
        if(userId == null)
            throw new IllegalArgumentException();

        User user = this.userRepository.findById(userId)
                .orElseThrow(ElementNotFoundException::new);

        List<Booking> all = this.bookingRepository.getAllByUser(user);

        if(all == null)
            throw new ElementsNotFoundException();

        return all
                .stream()
                .map(this.bookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getAllByRoomManagedByUser(Integer userId) throws ElementNotFoundException, ElementsNotFoundException {
        if(userId == null)
            throw new IllegalArgumentException();

        User user = this.userRepository.findById(userId)
                .orElseThrow(ElementNotFoundException::new);

        List<Booking> all = this.bookingRepository.getAllByRoomManagedByUser(user);

        if(all == null)
            throw new ElementsNotFoundException();

        return all
                .stream()
                .map(this.bookingMapper::toDTO)
                .collect(Collectors.toList());
    }
}
