package com.daralamane.BackendV1.service;


import com.daralamane.BackendV1.entity.Actualite;
import com.daralamane.BackendV1.repository.ActualiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActualiteService {
    @Autowired
    private ActualiteDao actualiteDao;

    public List<Actualite> findAll() {
        return actualiteDao.findAll();
    }

    public String save(Actualite actualite) {

        actualiteDao.save(actualite);
             return "actu added successfully !";

    }

    public Actualite update(Actualite actualite)
    {
        return actualiteDao.save(actualite);
    }

    public Optional<Actualite> findById(Long aLong) {
        return actualiteDao.findById(aLong);
    }

    public void deleteById(Long aLong) {
        actualiteDao.deleteById(aLong);
    }
}
