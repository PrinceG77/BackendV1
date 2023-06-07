package com.daralamane.BackendV1.controller;


import com.daralamane.BackendV1.entity.Evenement;
import com.daralamane.BackendV1.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/evenements")
public class EvenementRest {
    @Autowired
    private EvenementService evenementService;

    @GetMapping("/all")
    public List<Evenement> findAll() {
        return evenementService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String save(Evenement evenement) {
        return evenementService.save(evenement);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Evenement update(@RequestBody Evenement evenement) {
        return evenementService.update(evenement);
    }

    @GetMapping("/{id}")
    public Optional<Evenement> findById(@PathVariable Long aLong) {
        return evenementService.findById(aLong);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long aLong) {
        evenementService.deleteById(aLong);
    }
}
