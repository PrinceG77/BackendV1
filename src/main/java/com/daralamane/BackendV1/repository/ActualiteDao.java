package com.daralamane.BackendV1.repository;

import com.daralamane.BackendV1.entity.Actualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualiteDao extends JpaRepository<Actualite, Long> {


}
