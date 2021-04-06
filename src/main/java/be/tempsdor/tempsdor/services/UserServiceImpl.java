package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.mappers.UserPertinentMapper;
import be.tempsdor.tempsdor.mappers.UserMapper;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<UserDTO, UserPertinentDTO, Integer>, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPertinentMapper pertinentUserMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserPertinentMapper pertinentUserMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.pertinentUserMapper = pertinentUserMapper;
    }

    @Override
    public UserDTO add(UserDTO toInsert) throws ElementAlreadyExistsException {
        if(toInsert == null)
            throw new IllegalArgumentException();

        if(this.userRepository.existsById(toInsert.getId()))
            throw new ElementAlreadyExistsException();

        return this.userMapper.toDTO(this.userRepository.save(this.userMapper.toEntity(toInsert)));
    }

    public List<UserPertinentDTO> getAll() throws ElementsNotFoundException {
        List<User> all = this.userRepository.findAll();

        if(all.isEmpty())
            throw new ElementsNotFoundException();

        return all.stream()
                .map(this.pertinentUserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getOneById(Integer integer) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User \"" + s + "\" not found"));
    }
}
