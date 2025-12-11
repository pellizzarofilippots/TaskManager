package com.taskm.task_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TIPI_ATTIVITA", schema = "WKSP_TASKMAN")
public class TipiAttivita {
    @Id
    @Column(name = "ID_TIPO_ATTIVITA", nullable = false)
    private Long id;

    @Column(name = "ICONA", length = 30)
    private String icona;

    @Column(name = "ETICHETTA", nullable = false, length = 30)
    private String etichetta;

    //@ColumnDefault("0")
   // @Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")

    private Boolean indCanc;

}