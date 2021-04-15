package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.ActivityDTO;
import be.tempsdor.tempsdor.DTOs.ActivityPertinentDTO;
import be.tempsdor.tempsdor.entities.Activity;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.exceptions.MismatchingIdentifersException;
import be.tempsdor.tempsdor.mappers.ActivityMapper;
import be.tempsdor.tempsdor.mappers.ActivityPertinentMapper;
import be.tempsdor.tempsdor.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final EntityManager entityManager;
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final ActivityPertinentMapper activityPertinentMapper;

    public ActivityServiceImpl(EntityManager entityManager, ActivityRepository activityRepository, ActivityMapper activityMapper, ActivityPertinentMapper activityPertinentMapper) {
        this.entityManager = entityManager;
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
        this.activityPertinentMapper = activityPertinentMapper;
    }

    @Override
    @Transactional
    public ActivityPertinentDTO add(ActivityPertinentDTO toAdd) throws ElementAlreadyExistsException, ElementNotFoundException {
        if(toAdd == null)
            throw new IllegalArgumentException();

        if(this.activityRepository.existsById(toAdd.getId()))
            throw new ElementAlreadyExistsException("id", toAdd.getId().toString());

        if(this.activityRepository.existsByName(toAdd.getName()))
            throw new ElementAlreadyExistsException("name", toAdd.getName());

        Activity activity = this.activityPertinentMapper.toEntity(toAdd);

        return this.activityPertinentMapper.toDTO(
                this.activityRepository.saveAndFlush(activity));
    }

    @Override
    public List<ActivityPertinentDTO> getAll() throws ElementsNotFoundException {
        List<Activity> activities = this.activityRepository.findAll();

        if(activities.isEmpty())
            throw new ElementsNotFoundException();

        return activities
                .stream()
                .map(this.activityPertinentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityPertinentDTO getOneById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        return this.activityPertinentMapper.toDTO(
                this.activityRepository.findById(id)
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    @Transactional
    public ActivityPertinentDTO update(ActivityPertinentDTO updatedDatas, Long id) throws ElementNotFoundException, MismatchingIdentifersException {
        if(updatedDatas == null || id == null)
            throw new IllegalArgumentException();

        if(updatedDatas.getId() != id)
            throw new MismatchingIdentifersException();

        if(!this.activityRepository.existsById(id))
            throw new ElementNotFoundException();

        Activity activity = this.activityRepository.saveAndFlush(
                this.activityPertinentMapper.toEntity(updatedDatas));

        this.entityManager.refresh(
                this.entityManager.merge(activity));

        return this.activityPertinentMapper.toDTO(
                this.activityRepository.findById(activity.getId())
                        .orElseThrow(ElementNotFoundException::new));
    }

    @Override
    public void deleteById(Long id) throws ElementNotFoundException {
        if(id == null)
            throw new IllegalArgumentException();

        if(!this.activityRepository.existsById(id))
            throw new ElementNotFoundException();

        this.activityRepository.deleteById(id);
    }

    public List<ActivityDTO> getAllWithFullDatas() throws ElementsNotFoundException {
        List<Activity> activities = this.activityRepository.findAll();

        if(activities.isEmpty())
            throw new ElementsNotFoundException();

        return activities
                .stream()
                .map(this.activityMapper::toDTO)
                .collect(Collectors.toList());
    }
}
