package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TIPI_EMAIL", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_TM_TIPI_EMAIL_ETICHETTA", columnNames = {"ETICHETTA"}),
        @UniqueConstraint(name = "UNQ_TIPI_EMAIL_RILEVANZA", columnNames = {"RILEVANZA"})
})
public class TipiEmail {
    @Id
    @Column(name = "ID_TIPO_EMAIL", nullable = false)
    private Long id;

    @Column(name = "ETICHETTA", nullable = false, length = 30)
    private String etichetta;

    @Column(name = "RILEVANZA")
    private Long rilevanza;

    //@ColumnDefault("0")
   // @Column(name = "IND_CANC")
    @Column(name = "ind_canc", columnDefinition = "boolean default false")

    private Boolean indCanc;

}