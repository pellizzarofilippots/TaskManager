package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CATEGORIE_MIME", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_CATEGORIE_MIME_ETICHETTA", columnNames = {"ETICHETTA"})
})
public class CategorieMime {
    @Id
    @Column(name = "ID_CATEGORIA_MIME", nullable = false)
    private Long id;

    @Column(name = "ETICHETTA", nullable = false, length = 20)
    private String etichetta;

}