package com.daralamane.BackendV1.repository;


import com.daralamane.BackendV1.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementDao extends JpaRepository<Evenement, Long> {


}
