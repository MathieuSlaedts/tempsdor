package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.mappers.UserMapper;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO insert(User toInsert) throws ElementAlreadyExistsException {
        if(toInsert == null)
            throw new IllegalArgumentException();

        if(this.userRepository.existsById(toInsert.getId()))
            throw new ElementAlreadyExistsException();

        return this.userMapper.toDTO(this.userRepository.save(toInsert));
    }

    public List<UserDTO> getAll() throws ElementsNotFoundException {
        List<User> all = this.userRepository.findAll();

        if(all.isEmpty())
            throw new ElementsNotFoundException();

        return all.stream()
                .map(this.userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
