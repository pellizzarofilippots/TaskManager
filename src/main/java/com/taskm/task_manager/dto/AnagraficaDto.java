package com.taskm.task_manager.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnagraficaDto {


    Long id;
    String nome;
    String cognome;
    String genere;
    LocalDate nascita;
    String cf;
    Boolean indCanc;
}

