package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.IdentifiedDTO;
import be.tempsdor.tempsdor.exceptions.*;
import be.tempsdor.tempsdor.reports.Report;
import be.tempsdor.tempsdor.services.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("isAuthenticated()")
public abstract class AbstractCrudController<DTO_IN extends IdentifiedDTO<ID>,DTO_OUT, ID> implements CrudController<DTO_IN, DTO_OUT, ID> {
    protected final CrudService<DTO_IN, DTO_OUT, ID> service;

    public AbstractCrudController(CrudService<DTO_IN, DTO_OUT, ID> service) {
        this.service = service;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public DTO_OUT add(@Valid @RequestBody DTO_IN dto) throws ElementAlreadyExistsException, ElementNotFoundException, OwnRoomBookingException, RoomUnavailableException {
        return this.service.add(dto);
    }

    @Override
    @GetMapping
    public List<DTO_OUT> getAll() throws ElementsNotFoundException {
        return this.service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public DTO_OUT getOneById(@PathVariable("id") ID id) throws ElementNotFoundException {
        return this.service.getOneById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/update")
    public DTO_OUT update(@Valid @RequestBody DTO_IN dto,@PathVariable("id") ID id) throws ElementNotFoundException, RoomUnavailableException, MismatchingIdentifersException, OwnRoomBookingException {
        return this.service.update(dto, id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public Report deleteById(@PathVariable("id") ID id) throws ElementNotFoundException {
        this.service.deleteById(id);

        return Report.builder()
                .status(200)
                .path("/")
                .message("L'élément a été supprimé.")
                .build();
    }
}