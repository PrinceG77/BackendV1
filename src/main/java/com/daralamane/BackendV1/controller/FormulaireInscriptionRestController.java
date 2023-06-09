package com.daralamane.BackendV1.controller;


import com.daralamane.BackendV1.entity.ApiResponse;
import com.daralamane.BackendV1.entity.FormulaireInscription;
import com.daralamane.BackendV1.service.FormulaireInscriptionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inscription-forms")
public class FormulaireInscriptionRestController {
    private final FormulaireInscriptionService formulaireInscriptionService;

    public FormulaireInscriptionRestController(FormulaireInscriptionService formulaireInscriptionService) {
        this.formulaireInscriptionService = formulaireInscriptionService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'DIRECTEUR')")
    public List<FormulaireInscription> getAllForms() {
        return formulaireInscriptionService.findAll();
    }

    @GetMapping("/validated")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'DIRECTEUR')")
    public List<FormulaireInscription> getValidatedForms() {
        return formulaireInscriptionService.getValidatedForms();
    }

    @GetMapping("/unprocessed")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE')")
    public List<FormulaireInscription> getUnprocessedForms() {
        return formulaireInscriptionService.getNotProcessedForms();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SECRETAIRE', 'DIRECTEUR')")
    public ResponseEntity<FormulaireInscription> getFormById(@PathVariable Long id) {
        Optional<FormulaireInscription> formulaireInscription = formulaireInscriptionService.findById(id);
        return formulaireInscription.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/validation")
    @PreAuthorize("hasAnyAuthority('SECRETAIRE', 'DIRECTEUR')")
    public List<FormulaireInscription> getFormsForValidation() {
        return formulaireInscriptionService.getFormsForValidation();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETAIRE', 'DIRECTEUR')")
    public ResponseEntity<Void> deleteFormById(@PathVariable Long id) {
        formulaireInscriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('SECRETAIRE', 'DIRECTEUR')")
    public FormulaireInscription updateForm(@RequestBody FormulaireInscription formulaireInscription) {
        return formulaireInscriptionService.update(formulaireInscription);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveForm(@RequestBody FormulaireInscription formulaireInscription) {
        formulaireInscriptionService.save(formulaireInscription);
        ApiResponse response = new ApiResponse("Form added successfully!");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/export-excel")
    @PreAuthorize("hasAnyAuthority('SECRETAIRE', 'DIRECTEUR')")
    public ResponseEntity<byte[]> exportToExcel() {
        try {
            List<FormulaireInscription> formulaireInscriptions = formulaireInscriptionService.findAll();
            byte[] excelFile = formulaireInscriptionService.generateExcelFile(formulaireInscriptions);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "inscription-forms.xlsx");

            return ResponseEntity.ok().headers(headers).body(excelFile);
        } catch (IOException e) {
            // Gérer l'erreur et renvoyer une réponse appropriée
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


