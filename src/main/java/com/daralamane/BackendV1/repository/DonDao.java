package com.daralamane.BackendV1.repository;


import com.daralamane.BackendV1.entity.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonDao extends JpaRepository<Don, Long> {


}
