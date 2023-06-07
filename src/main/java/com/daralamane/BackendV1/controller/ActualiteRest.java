package com.daralamane.BackendV1.controller;


import com.daralamane.BackendV1.entity.Actualite;
import com.daralamane.BackendV1.service.ActualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/actualites")
public class ActualiteRest {
    @Autowired
    private ActualiteService actualiteService;

    @GetMapping("/all")
    public List<Actualite> findAll() {
        return actualiteService.findAll();
    }

    @PostMapping("/add")
    public String save(Actualite actualite) {
        return actualiteService.save(actualite);
    }

    @PutMapping("/update")
    public Actualite update(Actualite actualite) {
        return actualiteService.update(actualite);
    }

    @GetMapping("/{id}")
    public Optional<Actualite> findById(@PathVariable Long aLong) {
        return actualiteService.findById(aLong);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long aLong) {
        actualiteService.deleteById(aLong);
    }
}
