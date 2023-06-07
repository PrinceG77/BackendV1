package com.daralamane.BackendV1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormulaireInscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateNaissance;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "Le contact est obligatoire")
    private String contact;


    private String typeHandicap;

    @NotBlank(message = "Les besoins sont obligatoires")
    private String besoins;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate dateSoumission;

    private Boolean isProcessed = false;
    private Boolean isValidated = false;
}