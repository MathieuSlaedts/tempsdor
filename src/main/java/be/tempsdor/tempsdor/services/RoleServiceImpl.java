package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.RoleDTO;
import be.tempsdor.tempsdor.entities.Role;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.mappers.RoleMapper;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO getByName(String name) throws ElementNotFoundException {
        if(name == null)
            throw new IllegalArgumentException();

        if(!this.roleRepository.existsByName(name))
            throw new ElementNotFoundException();

        return this.roleMapper.toDTO(this.roleRepository.findByName(name));
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
}
