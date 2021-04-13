package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.IdentifiedDTO;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.reports.Report;
import be.tempsdor.tempsdor.services.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("isAuthenticated()")
public abstract class AbstractCrudController<FORM extends IdentifiedDTO<ID>, DTO, ID> implements CrudController<FORM, DTO, Report, ID> {
    protected final CrudService<FORM, DTO, ID> service;

    public AbstractCrudController(CrudService<FORM, DTO, ID> service) {
        this.service = service;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public DTO add(@Valid @RequestBody FORM form) throws ElementAlreadyExistsException, ElementNotFoundException {
        return this.service.add(form);
    }

    @Override
    @GetMapping
    public List<DTO> getAll() throws ElementsNotFoundException {
        return this.service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public DTO getOneById(@PathVariable("id") ID id) throws ElementNotFoundException {
        return this.service.getOneById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/update")
    public DTO update(@Valid @RequestBody FORM form,@PathVariable("id") ID id) throws ElementNotFoundException {
        return this.service.update(form, id);
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