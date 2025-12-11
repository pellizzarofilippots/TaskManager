package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TIPI_CELLULARE", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_TIPI_CELLULARE_ETICHETTA", columnNames = {"ETICHETTA"}),
        @UniqueConstraint(name = "UNQ_TIPI_CELLULARE_RILEVANZA", columnNames = {"RILEVANZA"})
})
public class TipiCellulare {
    @Id
    @Column(name = "ID_TIPO_CELLULARE", nullable = false)
    private Long id;

    @Column(name = "ETICHETTA", nullable = false, length = 30)
    private String etichetta;

    @Column(name = "RILEVANZA")
    private Long rilevanza;

    //@ColumnDefault("0")
    //@Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")

    private Boolean indCanc;

}