package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TIPI_SEDE", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_TIPI_SEDE_ETICHETTA", columnNames = {"ETICHETTA"}),
        @UniqueConstraint(name = "UNQ_TIPI_SEDE_RILEVANZA", columnNames = {"RILEVANZA"})
})
public class TipiSede {
    @Id
    @Column(name = "ID_TIPO_SEDE", nullable = false)
    private Long id;

    @Column(name = "ETICHETTA", length = 30)
    private String etichetta;

    @Column(name = "RILEVANZA")
    private Long rilevanza;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

}