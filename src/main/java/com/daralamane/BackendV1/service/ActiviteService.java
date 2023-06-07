package com.daralamane.BackendV1.service;


import com.daralamane.BackendV1.entity.Activite;
import com.daralamane.BackendV1.repository.ActiviteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteService {
    @Autowired
    private ActiviteDao activiteDao;

    public List<Activite> findAll() {
        return activiteDao.findAll();
    }

    public String save(Activite activite) {

             activiteDao.save(activite);
             return "service added successfully !";

    }

    public Activite update(Activite activite)
    {
        return activiteDao.save(activite);
    }

    public Optional<Activite> findById(Long aLong) {
        return activiteDao.findById(aLong);
    }

    public void deleteById(Long aLong) {
        activiteDao.deleteById(aLong);
    }
}
