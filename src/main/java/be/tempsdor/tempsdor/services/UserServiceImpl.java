package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.UserEmailOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPasswordOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.Role;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.exceptions.MismatchingIdentifersException;
import be.tempsdor.tempsdor.mappers.UserPertinentMapper;
import be.tempsdor.tempsdor.mappers.UserMapper;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPertinentMapper userPertinentMapper;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserPertinentMapper userPertinentMapper, PasswordEncoder passwordEncoder, EntityManager entityManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userPertinentMapper = userPertinentMapper;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserPertinentDTO add(UserDTO toAdd) throws ElementAlreadyExistsException, ElementNotFoundException {
        if(toAdd == null)
            throw new IllegalArgumentException();

        if(this.userRepository.existsById(toAdd.getId()))
            throw new ElementAlreadyExistsException("id", toAdd.getId().toString());

        if(this.userRepository.existsByUsername(toAdd.getUsername()))
            throw new ElementAlreadyExistsException("username", toAdd.getUsername());

        if(this.userRepository.existsByEmail(toAdd.getEmail()))
            throw new ElementAlreadyExistsException("email", toAdd.getEmail());

        User user = this.userMapper.toEntity(toAdd);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(toAdd.getPassword()));

//        this.userRepository.saveAndFlush(user);
//        this.entityManager.clear();
        return this.userPertinentMapper.toDTO(this.userRepository.saveAndFlush(user));
//        return this.userPertinentMapper.toDTO(
//                this.userRepository.findById(user.getId())
//                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public List<UserPertinentDTO> getAll() throws ElementsNotFoundException {

        List<User> users = this.userRepository.findAll();

        if(users.isEmpty())
            throw new ElementsNotFoundException();

        return users.stream()
                .map(this.userPertinentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserPertinentDTO getOneById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        return this.userRepository
                .findById(id)
                .map(this.userPertinentMapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    @Transactional
    public UserPertinentDTO update(UserDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(updatedDatas.getId() != id)
            throw new MismatchingIdentifersException();

        if(!this.userRepository.existsById(id))
            throw new ElementNotFoundException();

        User user = this.userRepository.saveAndFlush(
                this.userMapper.toEntity(updatedDatas));

        this.entityManager.refresh(
                this.entityManager.merge(user));

        return this.userPertinentMapper.toDTO(
                this.userRepository.findById(user.getId())
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public UserPertinentDTO updateEmailById(UserEmailOnlyDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(updatedDatas.getId() != id)
            throw new MismatchingIdentifersException();

        User user = this.userRepository
                .findById(id)
                .orElseThrow(ElementNotFoundException::new);
        user.setEmail(updatedDatas.getEmail());

        return this.userPertinentMapper.toDTO(
                this.userRepository.save(user));
    }

    @Override
    public UserPertinentDTO updatePasswordById(UserPasswordOnlyDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(updatedDatas.getId() != id)
            throw new MismatchingIdentifersException();

        User user = this.userRepository.findById(id)
                .orElseThrow(ElementNotFoundException::new);
        user.setPassword(passwordEncoder.encode(updatedDatas.getPassword()));

        return this.userPertinentMapper.toDTO(
                this.userRepository.save(user));
    }

    @Override
    public void deleteById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        if(!this.userRepository.existsById(id))
            throw new ElementNotFoundException();

        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User \"" + s + "\" not found"));
    }
}
