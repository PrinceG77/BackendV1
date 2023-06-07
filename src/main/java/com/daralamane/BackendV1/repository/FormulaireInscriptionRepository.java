package com.daralamane.BackendV1.repository;


import com.daralamane.BackendV1.entity.FormulaireInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaireInscriptionRepository extends JpaRepository<FormulaireInscription, Long> {

    public List<FormulaireInscription> findByIsValidatedTrue();
    public List<FormulaireInscription> findByIsProcessedFalse();
    public List<FormulaireInscription> findByIsProcessedTrueAndIsValidatedFalse();

}
