package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TIPI_LOCALE", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_TIPI_LOCALE_ETICHETTA", columnNames = {"ETICHETTA"})
})
public class TipiLocale {
    @Id
    @Column(name = "ID_TIPO_LOCALE", nullable = false)
    private Long id;

    @Column(name = "ETICHETTA", length = 30)
    private String etichetta;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

}