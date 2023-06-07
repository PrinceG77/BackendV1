package com.daralamane.BackendV1.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomDonateur;
    private  Long montant;
    private  String villeDonateur;
    private  String paysDonateur;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;

}
