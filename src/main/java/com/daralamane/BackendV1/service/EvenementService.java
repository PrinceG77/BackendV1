package com.daralamane.BackendV1.service;


import com.daralamane.BackendV1.entity.Evenement;
import com.daralamane.BackendV1.repository.EvenementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {
    @Autowired
    private EvenementDao evenementDao;

    public List<Evenement> findAll() {
        return evenementDao.findAll();
    }

    public String save(Evenement evenement) {

        evenementDao.save(evenement);
             return "event added successfully !";

    }

    public Evenement update(Evenement evenement)
    {
        return evenementDao.save(evenement);
    }

    public Optional<Evenement> findById(Long aLong) {
        return evenementDao.findById(aLong);
    }

    public void deleteById(Long aLong) {
        evenementDao.deleteById(aLong);
    }
}
