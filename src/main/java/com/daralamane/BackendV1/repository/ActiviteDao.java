package com.daralamane.BackendV1.repository;


import com.daralamane.BackendV1.entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteDao extends JpaRepository<Activite, Long> {


}
