package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.RoleDTO;
import be.tempsdor.tempsdor.entities.Role;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.mappers.RoleMapper;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, EntityManager entityManager, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public RoleDTO add(RoleDTO toAdd) throws ElementAlreadyExistsException, ElementNotFoundException {
        if(toAdd == null)
            throw new IllegalArgumentException();

        if(this.roleRepository.existsById(toAdd.getId()))
            throw new ElementAlreadyExistsException("id", toAdd.getId().toString());

        if(this.roleRepository.existsByName(toAdd.getName()))
            throw new ElementAlreadyExistsException("name", toAdd.getName());

        Role role = this.roleMapper.toEntity(toAdd);

        return this.roleMapper.toDTO(
                this.roleRepository.saveAndFlush(role));

//        Role role = this.roleRepository.saveAndFlush(
//                this.roleMapper.toEntity(toAdd));
//
//        entityManager.refresh(role);
//
//        return this.roleMapper.toDTO(
//                this.roleRepository.findById(role.getId())
//                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public List<RoleDTO> getAll() throws ElementsNotFoundException {
        List<Role> all = this.roleRepository.findAll();

        if(all.isEmpty())
            throw new ElementsNotFoundException();

        return all.stream()
                .map(this.roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getOneById(Integer id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        return this.roleRepository
                .findById(id)
                .map(this.roleMapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    @Transactional
    public RoleDTO update(RoleDTO updatedDatas, Integer id) throws ElementNotFoundException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        Role role = this.roleMapper.toEntity(updatedDatas);

        return this.roleMapper.toDTO(this.roleRepository.saveAndFlush(role));

//        Role role = this.roleRepository.saveAndFlush(
//                this.roleMapper.toEntity(updatedDatas));
//
//        this.entityManager.refresh(
//                this.entityManager.merge(role));
//
//        return this.roleMapper.toDTO(
//                this.roleRepository.findById(role.getId())
//                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public void deleteById(Integer id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        if(!this.roleRepository.existsById(id))
            throw new ElementNotFoundException();

        this.roleRepository.deleteById(id);
    }

    @Override
    public RoleDTO getByName(String name) throws ElementNotFoundException {
        if(name == null)
            throw new IllegalArgumentException();

        if(!this.roleRepository.existsByName(name))
            throw new ElementNotFoundException();

        return this.roleMapper.toDTO(
                this.roleRepository.findByName(name));
    }
}
