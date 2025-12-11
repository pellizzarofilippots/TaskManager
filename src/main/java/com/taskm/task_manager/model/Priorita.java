package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "PRIORITA", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_PRIORITA_ORDINE", columnNames = {"ORDINE"})
})
public class Priorita {
    @Id
    @Column(name = "ID_PRIORITA", nullable = false)
    private Long id;

    @Column(name = "ICONA", length = 30)
    private String icona;

    @Column(name = "ETICHETTA", length = 30)
    private String etichetta;

    @ColumnDefault("'u-color-14-bg'")
    @Column(name = "COLOR", nullable = false, length = 30)
    private String color;

    @Column(name = "ORDINE")
    private Long ordine;

    //@ColumnDefault("0")
    @Column(name = "IS_INATTIVA")
    private Boolean isInattiva;

}