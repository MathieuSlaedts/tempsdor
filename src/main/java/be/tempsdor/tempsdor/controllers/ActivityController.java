package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.ActivityDTO;
import be.tempsdor.tempsdor.DTOs.ActivityPertinentDTO;
import be.tempsdor.tempsdor.DTOs.RoomDTO;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.services.ActivityServiceImpl;
import be.tempsdor.tempsdor.services.RoomServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/activities")
public class ActivityController extends AbstractCrudController<ActivityPertinentDTO, ActivityPertinentDTO, Long> {
    public ActivityController(ActivityServiceImpl service) {
        super(service);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/full-datas")
    public List<ActivityDTO> getAllWithFullDatas() throws ElementsNotFoundException {
        return ((ActivityServiceImpl)service).getAllWithFullDatas();
    }
}
