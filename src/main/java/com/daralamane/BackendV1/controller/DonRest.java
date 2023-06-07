package com.daralamane.BackendV1.controller;


import com.daralamane.BackendV1.entity.Don;
import com.daralamane.BackendV1.service.DonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dons")
public class DonRest {
    @Autowired
    private DonService donService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_SECRETARIAT', 'ROLE_DIRECTEUR', 'ROLE_ADMIN', 'ROLE_CHEFSUIVI')")
    public List<Don> findAll() {
        return donService.findAll();
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SECRETARIAT', 'ROLE_DIRECTEUR', 'ROLE_ADMIN', 'ROLE_CHEFSUIVI')")
    public Optional<Don> findById(@PathVariable Long aLong) {
        return donService.findById(aLong);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SECRETARIAT', 'ROLE_DIRECTEUR', 'ROLE_ADMIN', 'ROLE_CHEFSUIVI')")
    public void deleteById(@PathVariable Long aLong) {
        donService.deleteById(aLong);
    }

    @PostMapping("/add-donation")
    public String save(Don don) {
        return donService.save(don);
    }
    @PostMapping("/update-donation")
    public Don update(Don don) {
        return donService.update(don);
    }
}
