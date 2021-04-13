package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.ActivityDTO;
import be.tempsdor.tempsdor.services.ActivityServiceImpl;
import be.tempsdor.tempsdor.services.CrudService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/activities")
public class ActivityController extends AbstractCrudController<ActivityDTO, ActivityDTO, Long> {
    public ActivityController(ActivityServiceImpl service) {
        super(service);
    }
}
