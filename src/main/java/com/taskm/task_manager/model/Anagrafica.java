package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ANAGRAFICA", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_ANAGRAFICA_CF", columnNames = {"CF"})
})
public class Anagrafica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANAGRAFICA", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 150)
    private String nome;

    @Column(name = "COGNOME", nullable = false, length = 150)
    private String cognome;

    @Column(name = "GENERE", length = 1)
    private String genere;

    @Column(name = "NASCITA")
    private LocalDate nascita;

    @Column(name = "CF", length = 16)
    private String cf;

    //@ColumnDefault("0")
    //@Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")

    private Boolean indCanc;



}