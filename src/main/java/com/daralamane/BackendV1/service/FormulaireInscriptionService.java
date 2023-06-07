package com.daralamane.BackendV1.service;


import com.daralamane.BackendV1.entity.FormulaireInscription;
import com.daralamane.BackendV1.repository.FormulaireInscriptionRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FormulaireInscriptionService {
    private final FormulaireInscriptionRepository formulaireInscriptionRepository;

    public FormulaireInscriptionService(FormulaireInscriptionRepository formulaireInscriptionRepository) {
        this.formulaireInscriptionRepository = formulaireInscriptionRepository;
    }

    public List<FormulaireInscription> findAll() {
        return formulaireInscriptionRepository.findAll();
    }

    public List<FormulaireInscription> getValidatedForms() {
        return formulaireInscriptionRepository.findByIsValidatedTrue();
    }

    public List<FormulaireInscription> getNotProcessedForms() {
        return formulaireInscriptionRepository.findByIsProcessedFalse();
    }

    public List<FormulaireInscription> getFormsForValidation() {
        return formulaireInscriptionRepository.findByIsProcessedTrueAndIsValidatedFalse();
    }

    public Optional<FormulaireInscription> findById(Long id) {
        return formulaireInscriptionRepository.findById(id);
    }

    public void deleteById(Long id) {
        formulaireInscriptionRepository.deleteById(id);
    }

    public FormulaireInscription update(FormulaireInscription formulaireInscription) {
        return formulaireInscriptionRepository.save(formulaireInscription);
    }

    public String save(FormulaireInscription formulaireInscription) {
        formulaireInscriptionRepository.save(formulaireInscription);
        return "Form added successfully!";
    }

    public byte[] generateExcelFile(List<FormulaireInscription> formulaireInscriptions) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Formulaire Inscriptions");

        // Création de l'en-tête
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nom");
        headerRow.createCell(2).setCellValue("Date de naissance");
        headerRow.createCell(3).setCellValue("Adresse");
        headerRow.createCell(4).setCellValue("Contact");
        headerRow.createCell(5).setCellValue("Type de handicap");
        headerRow.createCell(6).setCellValue("Besoins");
        headerRow.createCell(7).setCellValue("Date de soumission");
        headerRow.createCell(8).setCellValue("Traitée");
        headerRow.createCell(9).setCellValue("Validée");

        // Remplissage des données
        int rowNum = 1;
        for (FormulaireInscription formulaire : formulaireInscriptions) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(formulaire.getId());
            dataRow.createCell(1).setCellValue(formulaire.getNom());
            dataRow.createCell(2).setCellValue(formulaire.getDateNaissance().toString());
            dataRow.createCell(3).setCellValue(formulaire.getAdresse());
            dataRow.createCell(4).setCellValue(formulaire.getContact());
            dataRow.createCell(5).setCellValue(formulaire.getTypeHandicap());
            dataRow.createCell(6).setCellValue(formulaire.getBesoins());
            // Obtention de la date actuelle
            LocalDate dateSoumission = LocalDate.now();
            dataRow.createCell(7).setCellValue(dateSoumission.toString());
            dataRow.createCell(8).setCellValue(formulaire.getIsProcessed());
            dataRow.createCell(9).setCellValue(formulaire.getIsValidated());
        }

        // Redimensionnement automatique des colonnes
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }

        // Conversion du classeur en tableau de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }
}

